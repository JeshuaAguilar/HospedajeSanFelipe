import { Component, OnInit, inject } from '@angular/core';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { AlertsService } from '../../../services/alerts.service';
import { LoadingComponent } from '../shared/loading/loading.component';
import { environment } from '../../../../environments/environment.development';
import { Clientes, Pdf } from '../../../model/constantes';
import { ClienteResponse } from '../../../model/cliente.model';
import { AltaClienteComponent } from '../alta-cliente/alta-cliente.component';



declare const bootstrap: any;

@Component({
  selector: 'app-clientes',
  standalone: true,
  imports: [LoadingComponent, AltaClienteComponent],
  templateUrl: './clientes.component.html',
  styleUrl: './clientes.component.css'
})
export class ClientesComponent implements OnInit {

  private readonly URL_CLIENTES = `${environment.apiHost}${Clientes.CLIENTES}`;
  private readonly URL_PDF_CLIENTES = `${environment.apiHost}${Pdf.PDF}${Pdf.CLIENTES}`;

  private _peticiones = inject(PeticionesService);
  private _alerta = inject(AlertsService);
  public isLoadedClientes = false;
  public clientes?: ClienteResponse[];

  public ngOnInit(): void {
    this.getAllClientes(true);
  }

  public getAllClientes(isCloseLoading?: boolean): void {
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


  public descargarPdf(): void {
    const url = `${this.URL_PDF_CLIENTES}`;
    this._peticiones.generaPdf(url).subscribe({
      next: (data: any) => {
        const blob = new Blob([data], { type: 'application/pdf' });

        const downloadURL = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = downloadURL;
        link.download = "reporte_clientes.pdf";
        link.click();

        window.URL.revokeObjectURL(downloadURL);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }
}
