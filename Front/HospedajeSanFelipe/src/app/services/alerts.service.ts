import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertsService {

  constructor() { }

  toast = Swal.mixin({
    toast: true,
    position: 'bottom-right',
    showConfirmButton: false,
    timer: 5000,
    timerProgressBar: true,
    didOpen: (toast) => {
      toast.addEventListener('mouseenter', Swal.stopTimer);
      toast.addEventListener('mouseleave', Swal.resumeTimer);
    }
  });

  public iniciaLoading(): void {
    Swal.fire({
      title: 'Procesando información',
      text : 'Espere un momento...',
      icon : 'info',
      allowOutsideClick: false,
      footer: 'No cerrar ni actualizar esta ventana mientras está abierta'
    });

    Swal.showLoading();
  }

  public cierraLoading(): void {
    Swal.close();
  }

  public exito(mensaje: string): void {
    Swal.fire({
      title: '¡Éxito!',
      html : mensaje,
      icon : 'success',
      showCloseButton: true
    });
  }

  public advertencia(mensaje: string): void {
    Swal.fire({
      title: '¡Advertencia!',
      html : mensaje,
      icon : 'warning',
      showCloseButton: true
    });
  }

  public error(mensaje: string): void {
    Swal.fire({
      title: '¡Error!',
      html : mensaje,
      icon : 'error',
      showCloseButton: true
    });
  }

  public informacion(mensaje: string): void {
    Swal.fire({
      title: '¡Información!',
      html : mensaje,
      icon : 'info',
      showCloseButton: true
    });
  }

  public toastExito(mensaje: string): void {
    this.toast.fire({
       icon: 'success',
       html: mensaje
    });
  }

  public toastAdvertencia(mensaje: string): void {
    this.toast.fire({
       icon: 'warning',
       html: mensaje
    });
  }

  public toastError(mensaje: string): void {
    this.toast.fire({
       icon: 'error',
       html: mensaje
    });
  }
}
