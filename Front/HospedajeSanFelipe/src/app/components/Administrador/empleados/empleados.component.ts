import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';

@Component({
  selector: 'app-empleados',
  standalone: true,
  imports: [],
  templateUrl: './empleados.component.html',
  styleUrl: './empleados.component.css',
})
export class EmpleadosComponent implements OnInit {

  private fomrBuilder = inject(FormBuilder)
  private _peticiones = inject(PeticionesService)

  empleadoForm!: FormGroup;

  private defineForm(): void {
    this.empleadoForm = this .fomrBuilder.group({
      usr:      ['', [Validators.required, Validators.minLength(3)]],
      psw:      ['', [Validators.required, Validators.minLength(3)]],
      nombre:   ['', [Validators.required, Validators.minLength(3)]],
      primerA:  ['', [Validators.required, Validators.minLength(3)]],
      segundoA: ['', [Validators.required, Validators.minLength(3)]],
      noTel:    ['', [Validators.required, Validators.minLength(3)]],
      rol:      ['', [Validators.required]],
      foto:     ['', [Validators.required, Validators.minLength(3)]],
    })
  }

  public ngOnInit(): void {
    this.defineForm();
    this.getAllEmpleados();
  }

  private getAllEmpleados(): void {
    const url = 'http://localhost:8080/hospedaje/api/empleados'
    this._peticiones.getPetition().subscribe({
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

  public agregarEmpleado(): void {
    if (this.empleadoForm.invalid) {
      this.empleadoForm.markAllAsTouched();
    } else {
      // this._alerts.loading();
      const empleadoRequest: any = {
        userName: this.empleadoForm.get('usr')?.value,
        password: this.empleadoForm.get('psw')?.value,
        nombre: this.empleadoForm.get('nombre')?.value,
        primerA: this.empleadoForm.get('primerA')?.value,
        segundoA: this.empleadoForm.get('segundoA')?.value,
        noTel: this.empleadoForm.get('noTel')?.value,
        rol: this.empleadoForm.get('rol')?.value,
        foto: this.empleadoForm.get('foto')?.value,
      };
      this.empleado(empleadoRequest);
    }

  }
  private empleado(request: any): void {
    this._peticiones.empleado(request).subscribe({
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
    const formControl = this.empleadoForm.get(control);
    return formControl!.invalid && (formControl!.dirty || formControl!.touched);
  }
}
