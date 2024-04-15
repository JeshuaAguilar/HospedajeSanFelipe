import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';

@Component({
  selector: 'app-clientes',
  standalone: true,
  imports: [],
  templateUrl: './clientes.component.html',
  styleUrl: './clientes.component.css'
})
export class ClientesComponent {

  private fomrBuilder = inject(FormBuilder)
  private _peticiones = inject(PeticionesService)

  clienteForm!: FormGroup;

  private defineForm(): void {
    this.clienteForm = this .fomrBuilder.group({
      nombre: ['', [Validators.required, Validators.minLength(3)]],
      primerAp: ['', [Validators.required, Validators.minLength(3)]],
      segundoAp: ['', [Validators.required, Validators.minLength(3)]],
      numTel: ['', [Validators.required, Validators.minLength(3)]],
      estado: ['', [Validators.required]],
      municipio: ['', [Validators.required, Validators.minLength(3)]],
    })
  }

  public ngOnInit(): void {
    this.defineForm();
}

  public agregarCliente(): void {
    if (this.clienteForm.invalid) {
      this.clienteForm.markAllAsTouched();
    } else {
      // this._alerts.loading();
      const clienteRequest: any = {
        nombre: this.clienteForm.get('nombre')?.value,
        primerAp: this.clienteForm.get('primerAp')?.value,
        segundoAp: this.clienteForm.get('segundoAp')?.value,
        numTel: this.clienteForm.get('numTel')?.value,
        estado: this.clienteForm.get('estado')?.value,
        municipio: this.clienteForm.get('municipio')?.value,
      };
      this.cliente(clienteRequest);
    }
  }
  private cliente(request: any): void {
    this._peticiones.cliente(request).subscribe({
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
    const formControl = this.clienteForm.get(control);
    return formControl!.invalid && (formControl!.dirty || formControl!.touched);
  }
}
