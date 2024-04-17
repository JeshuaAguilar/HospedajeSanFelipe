import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { AlertsService } from '../../../services/alerts.service';
import { LoadingComponent } from '../shared/loading/loading.component';
import { environment } from '../../../../environments/environment.development';
import { Clientes } from '../../../model/constantes';
import { ClienteRequest, ClienteResponse } from '../../../model/cliente.model';

declare const bootstrap: any;

@Component({
  selector: 'app-clientes',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, LoadingComponent],
  templateUrl: './clientes.component.html',
  styleUrl: './clientes.component.css'
})
export class ClientesComponent implements OnInit {

  private readonly URL_CLIENTES = `${environment.apiHost}${Clientes.CLIENTES}`;
  private fomrBuilder = inject(FormBuilder)
  private _peticiones = inject(PeticionesService)
  private _alerta = inject(AlertsService);
  public isLoadedClientes = false;
  public clientes?: ClienteResponse[];
  clienteForm!: FormGroup;
  public isEditing = false;

  public defineForm(): void {

    this.isEditing = false;
    this.clienteForm = this .fomrBuilder.group({
      idCliente: [''],
      nombre:    ['', [Validators.required, Validators.minLength(3)]],
      primerAp:  ['', [Validators.required, Validators.minLength(3)]],
      segundoAp: ['', [Validators.required, Validators.minLength(3)]],
      telefono:  ['', [Validators.required, Validators.minLength(3), Validators.maxLength(10), Validators.pattern(/^-?\d+$/)]],
      estado:    ['', [Validators.required, Validators.minLength(3)]],
      municipio: ['', [Validators.required, Validators.minLength(3)]],
    })
  }

  private defineEditForm(cliente: ClienteRequest): void {

    this.isEditing = true;
    const { idCliente, nombre, primerApellido, segundoApellido, telefono, estado, municipio} = cliente;

    this.clienteForm = this .fomrBuilder.group({
      idCliente: idCliente,
      nombre:      [nombre,         [Validators.required, Validators.minLength(3)]],
      primerAp:    [primerApellido, [Validators.required, Validators.minLength(3)]],
      segundoAp:   [segundoApellido,[Validators.required, Validators.minLength(3)]],
      telefono:    [telefono,       [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern(/^-?\d+$/)]],
      estado:      [estado,         [Validators.required, Validators.minLength(3)]],
      municipio:   [municipio,      [Validators.required, Validators.minLength(3)]]
    })
  }

  public resetForm(): void {
    this.clienteForm.reset({
      idCliente:    '',
      nombre:       '',
      primerAp:     '',
      segundoAp:    '',
      telefono:     '',
      estado:       '',
      municipio:    '',
    })
  }

  public ngOnInit(): void {
    this.defineForm();
    this.getAllClientes(true);
  }

  private getAllClientes(isCloseLoading?: boolean): void {
    this.isLoadedClientes = false;

    this._peticiones.getPeticion(this.URL_CLIENTES).subscribe({
      next: (response: ClienteResponse[]) => {
        this.clientes = response;
        this.isLoadedClientes = true;
        if (isCloseLoading) {
          this._alerta.cierraLoading();
        }
      },
      error: (err: any) => {
        this._alerta.error(err);
      },
    });
  }

  public validaFormCliente(): void {
    if (this.clienteForm.invalid) {
      this.clienteForm.markAllAsTouched();
    } else {
      this._alerta.iniciaLoading();

      const clienteRequest: ClienteRequest = {
        idCliente         : this.isEditing ? this.clienteForm.get('idCliente').value : 0,
        nombre            : this.clienteForm.get('nombre').value,
        primerApellido    : this.clienteForm.get('primerAp').value,
        segundoApellido   : this.clienteForm.get('segundoAp').value,
        telefono          : this.clienteForm.get('telefono').value,
        estado            : this.clienteForm.get('estado').value,
        municipio         : this.clienteForm.get('municipio').value,
      };

      if (!this.isEditing) {
        this.agregarCliente(clienteRequest);
      } else {
        this.editaCliente(clienteRequest);
      }
    }
  }

  private agregarCliente(request: ClienteRequest): void {
    this._peticiones.postPeticion(this.URL_CLIENTES, request, false).subscribe({
      next: (response: string) => {
        this.resetForm();

        const miModal = bootstrap.Modal.getInstance(document.getElementById('staticBackdrop'));
        if (miModal) {
          miModal.hide();
        }

        this.getAllClientes();
        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  private editaCliente(request: ClienteRequest): void {
    this._peticiones.putPeticion(this.URL_CLIENTES, request).subscribe({
      next: (response: string) => {
        this.resetForm();
        const miModal = bootstrap.Modal.getInstance(document.getElementById('staticBackdrop'));
        if (miModal) {
          miModal.hide();
        }
        this.getAllClientes();
        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

 public modificarCliente(idCliente: number): void {

   const clienteFinded = this.clientes.find((cliente: ClienteResponse) => idCliente === cliente.idCliente);
   const clienteRequest: ClienteRequest = {
    idCliente : clienteFinded.idCliente,
    nombre    : clienteFinded.nombre,
    primerApellido  : clienteFinded.primerApellido,
    segundoApellido : clienteFinded.segundoApellido,
    telefono    : clienteFinded.telefono,
    estado    : clienteFinded.estado,
    municipio : clienteFinded.municipio,
   }
   this.defineEditForm(clienteRequest);
 }

  public eliminarCliente(idCliente: number): void {
    this._alerta.iniciaLoading();
    const url = `${this.URL_CLIENTES}/${idCliente}`
    this._peticiones.deletePeticion(url).subscribe({
      next: (response: string) => {
        this.getAllClientes();
        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

 public isValid(control: string): boolean {
   return this.clienteForm.get(control).valid;
 }

 public isFieldInvalid(control: string): boolean {
   const formControl = this.clienteForm.get(control);
   return formControl.invalid && (formControl.dirty || formControl.touched);
 }
}
