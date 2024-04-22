import { HttpClient, HttpErrorResponse, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PeticionesService {
  private httpClient = inject(HttpClient);

  constructor() {}

  private headerConfigText(): any {
    return {
      headers: new HttpHeaders({
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      }),
      withCredentials: true,
      responseType: 'text',
    };
  }

  private headerConfigJson(): any {
    return {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
        'Content-Type': 'application/json'
      }),
      withCredentials: true,
    };
  }

  private handlePostError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Error del lado del cliente
      console.error('Error del lado del cliente:', error.error.message);
      errorMessage = error.error.message;
    } else if (error.status == 404) {
      console.error('Error del lado del servidor:', error.message);
      errorMessage =
        'problemas de conectividad con el servidor, intente más tarde.';
    } else {
      // El servidor devolvió un código de error
      console.error(`Código de error ${error.status}, cuerpo: `, error.error);
      errorMessage = error.error;
    }

    // Devolver un observable con un mensaje de error legible
    return throwError(errorMessage);
  }

  private convertResponse(response: any): any {
    try {
      return JSON.parse(response);
    } catch {
      return response;
    }
  }

  public getPeticion(url: string): Observable<any> {

    const nuevoHeader = this.headerConfigJson();

    return this.httpClient.get<any>(url, nuevoHeader).pipe(
      map((response: any) => {
        return this.convertResponse(response);
      }),
      catchError(this.handlePostError)
    );
  }

  public async getSyncPeticion(url: string): Promise<any> {
    const nuevoHeader = this.headerConfigJson();
    return await this.httpClient.get<any>(url, nuevoHeader).toPromise();
  }

  public postPeticion(url: string, parametros: any, isJsonResponse: boolean): Observable<any> {
    const nuevoHeader = isJsonResponse ? this.headerConfigJson() : this.headerConfigText();

    return this.httpClient.post(url, parametros, nuevoHeader).pipe(
      map((response: any) => {
        return this.convertResponse(response);
      }),
      catchError(this.handlePostError)
    );
  }

  public putPeticion(url: string, params: any ): Observable<any> {

    const nuevoHeader = this.headerConfigText();

    return this.httpClient.put(url, params, nuevoHeader).pipe(
      map((response: any) => {
        return this.convertResponse(response);
      }),
      catchError(this.handlePostError)
    );
  }

  public deletePeticion(url: string): Observable<any> {

    return this.httpClient.delete(url, this.headerConfigText()).pipe(
      map((response: any) => {
        return this.convertResponse(response);
      }),
      catchError(this.handlePostError)
    );
  }

  public login(params: any): Observable<any> {
    const url = `http://localhost:8080/hospedaje/api/empleados/login`;

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      withCredentials: true
    };

    return this.httpClient.post<any>(url, params, httpOptions).pipe(catchError(this.handlePostError));
  }

}
