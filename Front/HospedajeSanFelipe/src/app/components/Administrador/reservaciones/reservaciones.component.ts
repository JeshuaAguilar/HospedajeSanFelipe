import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';

@Component({
  selector: 'app-reservaciones',
  standalone: true,
  imports: [],
  templateUrl: './reservaciones.component.html',
  styleUrl: './reservaciones.component.css'
})
export class ReservacionesComponent {

  private fomrBuilder = inject(FormBuilder)
  private _peticiones = inject(PeticionesService)

  reservacionForm!: FormGroup;

  private defineForm(): void {
    this.reservacionForm = this .fomrBuilder.group({
      fechaEntrada: ['', [Validators.required, Validators.minLength(3)]],
      fechaSalida: ['', [Validators.required, Validators.minLength(3)]],
      noPersonas: ['', [Validators.required, Validators.minLength(3)]],
      noPersonaExtra: ['', [Validators.required, Validators.minLength(3)]],
      total: ['', [Validators.required, Validators.minLength(3)]],
      estado: ['', [Validators.required, Validators.minLength(3)]],
      cliente: ['', [Validators.required, Validators.minLength(3)]],
      empleado: ['', [Validators.required, Validators.minLength(3)]],
      comentario: ['', [Validators.required, Validators.minLength(3)]],
      precioEspecial: ['', [Validators.required, Validators.minLength(3)]],
    })
  }

  public ngOnInit(): void {
    this.defineForm();
}

  public agregarreservacion(): void {
    if (this.reservacionForm.invalid) {
      this.reservacionForm.markAllAsTouched();
    } else {
      // this._alerts.loading();
      const reservacionRequest: any = {
        fechaEntrada: this.reservacionForm.get('fechaE')?.value,
        fechaSalida: this.reservacionForm.get('fechaS')?.value,
        noPersonas: this.reservacionForm.get('noPe')?.value,
        noPersonaExtra: this.reservacionForm.get('noPeEx')?.value,
        total: this.reservacionForm.get('total')?.value,
        estado: this.reservacionForm.get('estado')?.value,
        cliente: this.reservacionForm.get('cliente')?.value,
        empleado: this.reservacionForm.get('empleado')?.value,
        comentario: this.reservacionForm.get('comentario')?.value,
        precioEspecial: this.reservacionForm.get('precioEs')?.value,

      };
      this.reservacion(reservacionRequest);
    }
  }
  private reservacion(request: any): void {
    this._peticiones.reservacion(request).subscribe({
      next: (response: any) => {
        console.log(response);
        // sessionStorage.setItem('token', response.token);
        // sessionStorage.setItem('rol', response.rol);
        // this._alerts.toastLogin('¡Ha iniciado sesión correctamente!');
        // this.redirectHome();
      },
      error: (err: any) => {
        console.error(err);
        // this._alerts.error(err);
      },
    });
  }
  public isFieldInvalid(control: string): boolean {
    const formControl = this.reservacionForm.get(control);
    return formControl!.invalid && (formControl!.dirty || formControl!.touched);
  }
}
