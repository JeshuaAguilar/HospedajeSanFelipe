import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormsModule, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { LoginResponse } from '../../../model/login-response';
import { Router } from '@angular/router';
import { UtilsService } from '../../../services/utils.service';
import { AlertsService } from '../../../services/alerts.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  private _peticiones = inject(PeticionesService);
  private formBuilder = inject(FormBuilder);
  private _alertas = inject(AlertsService);
  private _util = inject(UtilsService);
  private router = inject(Router);

  loginForm!: FormGroup;

  constructor() {
    if (this._util.isLogged) {
      this.redirectHome();
    }
  }

  private defineForm(): void {
    this.loginForm = this.formBuilder.group({
      usr: ['', [Validators.required, Validators.minLength(3)]],
      psw: ['', [Validators.required, Validators.minLength(3)]],
    });
  }

  public ngOnInit(): void {
    this.defineForm();
  }

  public validarLogin(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
    } else {
      this._alertas.iniciaLoading();
      const loginRequest: any = {
        userName: this.loginForm.get('usr')?.value,
        password: this.loginForm.get('psw')?.value,
      };
      this.login(loginRequest);
    }
  }

  private login(request: any): void {
    this._peticiones.login(request).subscribe({
      next: (response: LoginResponse) => {
        console.log(response);
        sessionStorage.setItem('token', response.token);
        sessionStorage.setItem('user', JSON.stringify(response));
        this._alertas.toastExito('¡Ha iniciado sesión correctamente!');
        this.redirectHome();
      },
      error: (err: any) => {
        console.error(err);
        this._alertas.error(err);
      },
    });
  }

  public isFieldInvalid(control: string): boolean {
    const formControl = this.loginForm.get(control);
    return formControl!.invalid && (formControl!.dirty || formControl!.touched);
  }

  private redirectHome(): void {
    this.router.navigate(["/inicio"]);
  }
}
