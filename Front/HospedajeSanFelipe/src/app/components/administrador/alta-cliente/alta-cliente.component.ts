import { Component, EventEmitter, Input, OnInit, Output, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ClienteRequest, ClienteResponse } from '../../../model/cliente.model';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { AlertsService } from '../../../services/alerts.service';
import { environment } from '../../../../environments/environment.development';
import { Clientes } from '../../../model/constantes';

declare const bootstrap: any;

@Component({
  selector: 'app-alta-cliente',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './alta-cliente.component.html',
  styleUrl: './alta-cliente.component.css'
})
export class AltaClienteComponent implements OnInit {

  private readonly URL_CLIENTES = `${environment.apiHost}${Clientes.CLIENTES}`;

  @Output() exitoCliente = new EventEmitter();

  private _peticiones = inject(PeticionesService);
  private fomrBuilder = inject(FormBuilder);
  private _alerta = inject(AlertsService);
  public isLoadedClientes = false;
  public clientes?: ClienteResponse[];

  clienteForm!: FormGroup;

  public isEditing = signal(false);

  public defineForm(): void {
    this.isEditing.set(false);

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

    this.isEditing.set(true);
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

  public validaFormCliente(): void {
    if (this.clienteForm.invalid) {
      this.clienteForm.markAllAsTouched();
    } else {
      this._alerta.iniciaLoading();

      const clienteRequest: ClienteRequest = {
        idCliente         : this.isEditing() ? this.clienteForm.get('idCliente').value : 0,
        nombre            : this.clienteForm.get('nombre').value,
        primerApellido    : this.clienteForm.get('primerAp').value,
        segundoApellido   : this.clienteForm.get('segundoAp').value,
        telefono          : this.clienteForm.get('telefono').value,
        estado            : this.clienteForm.get('estado').value,
        municipio         : this.clienteForm.get('municipio').value,
      };

      if (!this.isEditing()) {
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

        // this.getAllClientes();
        this.exitoCliente.emit();
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

        // this.getAllClientes();
        this.exitoCliente.emit();
        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

 public modificarCliente(cliente: ClienteResponse): void {

  //  const clienteFinded = this.clientes.find((cliente: ClienteResponse) => idCliente === cliente.idCliente);
   const clienteRequest: ClienteRequest = {
    idCliente : cliente.idCliente,
    nombre    : cliente.nombre,
    primerApellido  : cliente.primerApellido,
    segundoApellido : cliente.segundoApellido,
    telefono    : cliente.telefono,
    estado    : cliente.estado,
    municipio : cliente.municipio,
   }

   this.defineEditForm(clienteRequest);
 }

  public isValid(control: string): boolean {
    return this.clienteForm.get(control).valid;
  }

  public isFieldInvalid(control: string): boolean {
    const formControl = this.clienteForm.get(control);
    return formControl.invalid && (formControl.dirty || formControl.touched);
  }

  ngOnInit(): void {
    this.defineForm();
  }
}
