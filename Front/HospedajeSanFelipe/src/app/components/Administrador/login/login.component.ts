import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormsModule, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { LoginResponse } from '../../../model/login-response';
import { Router } from '@angular/router';
import { UtilsService } from '../../../services/utils.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);
  private _peticiones = inject(PeticionesService);
  private _util = inject(UtilsService);

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
      // this._alerts.loading();
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
        // this._alerts.toastLogin('¡Ha iniciado sesión correctamente!');
        this.redirectHome();
      },
      error: (err: any) => {
        console.error(err);
        // this._alerts.error(err);
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
