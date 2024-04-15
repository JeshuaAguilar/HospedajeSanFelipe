import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor() { }

  public get isLogged() : boolean {
    if (sessionStorage.getItem("token")) {
      return true;
    } else {
      return false;
    }
  }

}
