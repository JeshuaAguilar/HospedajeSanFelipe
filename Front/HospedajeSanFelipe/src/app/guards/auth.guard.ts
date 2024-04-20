import { CanActivateFn, Router } from '@angular/router';
import { UtilsService } from '../services/utils.service';
import { inject } from '@angular/core';
import { AlertsService } from '../services/alerts.service';

export const ADMIN = '/inicio|/habitaciones|/reservaciones|/clientes|/empleados|/comentarios';
export const VENDEDOR = '/inicio|/habitaciones|/reservaciones';


export const authGuard: CanActivateFn = (route, state) => {

  const _alertas = inject(AlertsService);
  const _util = inject(UtilsService);
  const router = inject(Router);

  _alertas.iniciaLoading();

  if (!_util.isLogged) {
    router.navigateByUrl('/login');
    return false;
  } else {
    const userRol = _util.getRolUser();

    if (userRol === 'ADMIN') {
      if (ADMIN.includes(state.url)) {
        return true;
      } else {
        router.navigateByUrl('/login');
        return false;
      }
    } else if ('VENDEDOR') {
      if (VENDEDOR.includes(state.url)) {
        return true;
      } else {
        router.navigateByUrl('/login');
        return false;
      }
    } else {
      router.navigateByUrl('/login');
      return false;
    }
  }
};

