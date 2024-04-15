import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { CatRol, EmpleadoRequest, EmpleadoResponse } from '../../../model/empleados';
import { AlertsService } from '../../../services/alerts.service';
import { environment } from '../../../../environments/environment.development';
import { Empleados, Roles } from '../../../model/constantes';
import { LoadingComponent } from '../shared/loading/loading.component';

declare const bootstrap: any;

@Component({
  selector: 'app-empleados',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, LoadingComponent],
  templateUrl: './empleados.component.html',
  styleUrl: './empleados.component.css',
})
export class EmpleadosComponent implements OnInit {

  private readonly URL_EMPLEADOS = `${environment.apiHost}${Empleados.EMPLEADOS}`;
  private readonly URL_ROLES = `${environment.apiHost}${Roles.ROLES}`;

  private fomrBuilder = inject(FormBuilder);
  private _peticiones = inject(PeticionesService);
  private _alerta = inject(AlertsService);

  public isLoadedEmpleados = false;
  public empleados?: EmpleadoResponse[];
  public empleadoForm!: FormGroup;
  public roles?: CatRol[];
  public isEditing = false;

  public defineForm(): void {
    this.isEditing = false;
    this.empleadoForm = this .fomrBuilder.group({
      usr:      ['' , [Validators.required, Validators.minLength(3)]],
      psw:      ['' , [Validators.required, Validators.minLength(3)]],
      nombre:   ['' , [Validators.required, Validators.minLength(3)]],
      primerA:  ['' , [Validators.required, Validators.minLength(3)]],
      segundoA: ['' , [Validators.required, Validators.minLength(3)]],
      noTel:    ['' , [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern(/^-?\d+$/)]],
      rol:      ['' , [Validators.required]],
      foto:     ['' , [Validators.required, Validators.minLength(3)]]
    })
  }

  private defineEditForm(empleado: EmpleadoRequest): void {
    this.isEditing = true;
    const { idEmpleado, userName, contrasenia, nombre, primerApellido, segundoApellido, noTelefono, rol, urlFoto } = empleado;

    this.empleadoForm = this .fomrBuilder.group({
      idEmpleado: idEmpleado,
      usr:      [userName       , [Validators.required, Validators.minLength(3)]],
      psw:      [contrasenia    , [Validators.minLength(3)]],
      nombre:   [nombre         , [Validators.required, Validators.minLength(3)]],
      primerA:  [primerApellido , [Validators.required, Validators.minLength(3)]],
      segundoA: [segundoApellido, [Validators.required, Validators.minLength(3)]],
      noTel:    [noTelefono     , [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern(/^-?\d+$/)]],
      rol:      [rol            , [Validators.required]],
      foto:     [urlFoto        , [Validators.minLength(3)]]
    })
  }

  public resetForm(): void {
    this.empleadoForm.reset({
      usr:     '',
      psw:     '',
      nombre:  '',
      primerA: '',
      segundoA:'',
      noTel:   '',
      rol:     '',
      foto:    '',
    })
  }

  public ngOnInit(): void {
    this.getAllEmpleados(true);
    this.getRolCatalog();
  }

  private getAllEmpleados(isCloseLoading?: boolean): void {
    this.isLoadedEmpleados = false;
    this._peticiones.getPeticion(this.URL_EMPLEADOS).subscribe({
      next: (response: EmpleadoResponse[]) => {
        this.empleados = response;
        this.isLoadedEmpleados = true;
        if (isCloseLoading) {
          this._alerta.cierraLoading();
        }
      },
      error: (err: any) => {
        this._alerta.error(err);
      },
    });
  }

  public validaFormEmpleado(): void {
    if (this.empleadoForm.invalid) {
      this.empleadoForm.markAllAsTouched();
    } else {
      this._alerta.iniciaLoading();

      const empleadoRequest: EmpleadoRequest = {
        idEmpleado     : this.isEditing ? this.empleadoForm.get('idEmpleado')?.value : 0,
        userName       : this.empleadoForm.get('usr')?.value,
        contrasenia    : this.empleadoForm.get('psw')?.value,
        nombre         : this.empleadoForm.get('nombre')?.value,
        primerApellido : this.empleadoForm.get('primerA')?.value,
        segundoApellido: this.empleadoForm.get('segundoA')?.value,
        noTelefono     : this.empleadoForm.get('noTel')?.value,
        rol            : this.empleadoForm.get('rol')?.value,
        urlFoto        : this.empleadoForm.get('foto')?.value,
      };

      if (!this.isEditing) {
        this.agregaEmpleado(empleadoRequest);
      } else {
        this.editaEmpleado(empleadoRequest);
      }
    }
  }

  private agregaEmpleado(request: EmpleadoRequest): void {

    this._peticiones.postPeticion(this.URL_EMPLEADOS, request, false).subscribe({
      next: (response: string) => {
        this.resetForm();

        const miModal = bootstrap.Modal.getInstance(document.getElementById('staticBackdrop'));

        if (miModal) {
          miModal.hide();
        }

        this.getAllEmpleados();

        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  private editaEmpleado(request: EmpleadoRequest): void {

    this._peticiones.putPeticion(this.URL_EMPLEADOS, request).subscribe({
      next: (response: string) => {
        this.resetForm();

        const miModal = bootstrap.Modal.getInstance(document.getElementById('staticBackdrop'));

        if (miModal) {
          miModal.hide();
        }

        this.getAllEmpleados();

        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  public modificarEmpleado(idEmpleado: number): void {
    const empleadoFinded = this.empleados?.find((empleado: EmpleadoResponse) => idEmpleado === empleado.idEmpleado);

    const empleadoRequest: EmpleadoRequest = {
      idEmpleado     : empleadoFinded!.idEmpleado,
      userName       : empleadoFinded!.userName,
      contrasenia    : '',
      nombre         : empleadoFinded!.nombre,
      primerApellido : empleadoFinded!.primerApellido,
      segundoApellido: empleadoFinded!.segundoApellido,
      noTelefono     : empleadoFinded!.noTelefono,
      rol            : empleadoFinded!.rol.idRol,
      urlFoto        : '',
    }

    this.defineEditForm(empleadoRequest);
  }

  public eliminarEmpleado(idEmpleado: number): void {
    this._alerta.iniciaLoading();

    const url = `${this.URL_EMPLEADOS}/${idEmpleado}`

    this._peticiones.deletePeticion(url).subscribe({
      next: (response: string) => {
        this.getAllEmpleados();
        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  public getRolCatalog(): void {
    this._peticiones.getPeticion(this.URL_ROLES).subscribe({
      next: (response: CatRol[]) => {
        this.roles = response;
        this.defineForm();
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  public isValid(control: string): boolean {
    return this.empleadoForm.get(control)!.valid;
  }

  public isFieldInvalid(control: string): boolean {
    const formControl = this.empleadoForm.get(control);
    return formControl!.invalid && (formControl!.dirty || formControl!.touched);
  }
}
