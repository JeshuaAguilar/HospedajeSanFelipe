import { Component, OnInit, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { PeticionesService } from '../../../../services/peticiones/peticiones.service';
import { HttpResponse } from '@angular/common/http';
import { AlertsService } from '../../../../services/alerts.service';
import { environment } from '../../../../../environments/environment.development';
import { Imagenes } from '../../../../model/constantes';

@Component({
  selector: 'app-imagenes',
  standalone: true,
  imports: [],
  templateUrl: './imagenes.component.html',
  styleUrl: './imagenes.component.css'
})
export class ImagenesComponent implements OnInit {

  // private readonly URL_IMAGENES = `${environment.apiHost}${Imagenes.IMAGENES}`

  // currentFile: File;
  // message = '';
  // fileInfos: any;

  // private _alerta = inject(AlertsService);
  // private _peticiones = inject(PeticionesService);

  // selectFile(event: any): void {
  //   this.currentFile = event.target.files.item(0);
  // }

  // public uploadImage(): void {
  //   if (this.currentFile) {
  //     this._peticiones.uploadImage(this.currentFile).subscribe({
  //       next: (event: any) => {
  //         if (event instanceof HttpResponse) {
  //           this.message = event.body.message;
  //           this.getImagenes();
  //         }
  //       },
  //       error: (err: any) => {
  //         console.log(err);

  //         if (err.error && err.error.message) {
  //           this.message = err.error.message;
  //         } else {
  //           this.message = 'Could not upload the file!';
  //         }
  //       },
  //       complete: () => {
  //         this.currentFile = undefined;
  //       },
  //     });
  //   }
  // }

  // private getImagenes(): void {
  //   this._peticiones.getPeticion(this.URL_IMAGENES).subscribe({
  //     next: (response: any) => {
  //       this.fileInfos = response;
  //     },
  //     error: (err: any) => {
  //       this._alerta.error(err);
  //     },
  //   });
  // }

  ngOnInit(): void {

  }
}
