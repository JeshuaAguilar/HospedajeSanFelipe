import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatCalendarCellClassFunction } from '@angular/material/datepicker';
import EstadosMunicipiosJson from '../../../../assets/EstadosMunicipios.json';
import { EstadosMunicipios } from '../body.model';

@Component({
  selector: 'app-datos-cliente',
  templateUrl: './datos-cliente.component.html',
  styleUrls: ['./datos-cliente.component.css'],
})
export class DatosClienteComponent {
  readonly ESTADOS_MUNICIPIOS: EstadosMunicipios[] = EstadosMunicipiosJson;
  municipios: string[];
  datosClienteForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  get isValidAlgo() {
    return this.datosClienteForm.get('apellido').invalid && this.datosClienteForm.get('apellido').touched;
  }

  ngOnInit() {
    this.inicializaFormulario();
  }

  private inicializaFormulario(): void {
    this.datosClienteForm = this.formBuilder.group({
      nombre      : ['', [Validators.required]],
      apellido    : ['', [Validators.required]],
      noCelular   : ['', [Validators.required]],
      estado      : ['', [Validators.required]],
      municipio   : ['', [Validators.required]],
      noHabitacion: ['', [Validators.required]],
      fechaES     : ['', [Validators.required]]
    });
  }

  public obtieneMunicipio(estado: string): void {
    const estadoSelect = this.ESTADOS_MUNICIPIOS.find((estados: EstadosMunicipios) => estados.estado === estado);
    this.municipios = [];
    this.municipios = estadoSelect.municipios;
  }
}
