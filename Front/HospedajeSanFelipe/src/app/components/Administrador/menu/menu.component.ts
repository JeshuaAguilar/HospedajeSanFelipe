import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { UtilsService } from '../../../services/utils.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLinkActive, RouterLink],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css',
})
export class MenuComponent {
  _utils = inject(UtilsService);
}
