import { Component, Input } from '@angular/core';
import { Habitacion } from '../../body/body.model';

@Component({
  selector: 'app-card-cuarto',
  templateUrl: './card-cuarto.component.html',
  styleUrls: ['./card-cuarto.component.css']
})
export class CardCuartoComponent {
  @Input() habitacion: Habitacion;
}
