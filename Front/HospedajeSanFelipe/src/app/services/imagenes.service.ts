import { Injectable, inject } from '@angular/core';
import { AlertsService } from './alerts.service';
import { PeticionesService } from './peticiones/peticiones.service';
import { HttpClient, HttpEvent, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImagenesService {

  currentFile: File;
  message = '';
  fileInfos: any;

  private _alerta = inject(AlertsService);
  private httpClient = inject(HttpClient);
  private _peticiones = inject(PeticionesService);

  public selectFile(event: any): void {
    this.currentFile = event.target.files.item(0);
  }

  public uploadImage(url: string): void {
    if (this.currentFile) {
      const formData: FormData = new FormData();

      formData.append('file', this.currentFile);

      const headers = new HttpHeaders({
        'Authorization': `Bearer ${sessionStorage.getItem('token')}`
      });

      const req = new HttpRequest('POST', url, formData, {
        headers: headers,
        responseType: 'json',
      });

      this.httpClient.request(req).subscribe({
        next: (event: any) => {
          if (event instanceof HttpResponse) {
            this.message = event.body.message;
          }
        },
        error: (err: any) => {
          console.log(err);

          if (err.error && err.error.message) {
            this.message = err.error.message;
          } else {
            this.message = 'Could not upload the file!';
          }
        },
        complete: () => {
          this.currentFile = undefined;
        },
      });
    }
  }

  public getImagenes(url: string): void {
    this._peticiones.getPeticion(url).subscribe({
      next: (response: any) => {
        this.fileInfos = response;
      },
      error: (err: any) => {
        this._alerta.error(err);
      },
    });
  }

  public getImagenByName(url: string): Observable<Blob> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${sessionStorage.getItem('token')}`
    });

    return this.httpClient.get(url, {
      headers: headers,
      responseType: 'blob'
    });
  }
}
