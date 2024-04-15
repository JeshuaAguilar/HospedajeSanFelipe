import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';

@Component({
  selector: 'app-comentarios',
  standalone: true,
  imports: [],
  templateUrl: './comentarios.component.html',
  styleUrl: './comentarios.component.css'
})
export class ComentariosComponent {

  private fomrBuilder = inject(FormBuilder)
  private _peticiones = inject(PeticionesService)

  comentarioForm!: FormGroup;

  private defineForm(): void {
    this.comentarioForm = this .fomrBuilder.group({
      comentario: ['', [Validators.required, Validators.minLength(3)]],

    })
  }

  public ngOnInit(): void {
    this.defineForm();
}

  public agregarComentario(): void {
    if (this.comentarioForm.invalid) {
      this.comentarioForm.markAllAsTouched();
    } else {
      // this._alerts.loading();
      const comentarioRequest: any = {
        comentario: this.comentarioForm.get('comentario')?.value,
      };
      this.comentario(comentarioRequest);
    }
  }
  private comentario(request: any): void {
    this._peticiones.comentario(request).subscribe({
      next: (response: any) => {
        console.log(response);
        // sessionStorage.setItem('token', response.token);
        // sessionStorage.setItem('rol', response.rol);
        // this._alerts.toastLogin('¡Ha iniciado sesión correctamente!');
        // this.redirectHome();
      },
      error: (err: any) => {
        console.error(err);
        // this._alerts.error(err);
      },
    });
  }
  public isFieldInvalid(control: string): boolean {
    const formControl = this.comentarioForm.get(control);
    return formControl!.invalid && (formControl!.dirty || formControl!.touched);
  }
}
