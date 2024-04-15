import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';

@Component({
  selector: 'app-habitaciones',
  standalone: true,
  imports: [],
  templateUrl: './habitaciones.component.html',
  styleUrl: './habitaciones.component.css'
})
export class HabitacionesComponent {

  private fomrBuilder = inject(FormBuilder)
  private _peticiones = inject(PeticionesService)

  habitacionForm!: FormGroup;

  private defineForm(): void {
    this.habitacionForm = this .fomrBuilder.group({
      noHabitacion: ['', [Validators.required, Validators.minLength(3)]],
      noOcupantes: ['', [Validators.required, Validators.minLength(3)]],
      noMaxOcupante: ['', [Validators.required, Validators.minLength(3)]],
      piso: ['', [Validators.required, Validators.minLength(3)]],
      estado: ['', [Validators.required, Validators.minLength(3)]],
      foto: ['', [Validators.required]],

    })
  }

  public ngOnInit(): void {
    this.defineForm();
}

  public agregarhabitacion(): void {
    if (this.habitacionForm.invalid) {
      this.habitacionForm.markAllAsTouched();
    } else {
      // this._alerts.loading();
      const habitacionRequest: any = {
        noHabitacion: this.habitacionForm.get('noHa')?.value,
        noOcupantes: this.habitacionForm.get('noOc')?.value,
        noMaxOcupante: this.habitacionForm.get('noMax')?.value,
        piso: this.habitacionForm.get('piso')?.value,
        estado: this.habitacionForm.get('estado')?.value,
        foto: this.habitacionForm.get('foto')?.value,

      };
      this.habitacion(habitacionRequest);
    }
  }
  private habitacion(request: any): void {
    this._peticiones.habitacion(request).subscribe({
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
    const formControl = this.habitacionForm.get(control);
    return formControl!.invalid && (formControl!.dirty || formControl!.touched);
  }
}
