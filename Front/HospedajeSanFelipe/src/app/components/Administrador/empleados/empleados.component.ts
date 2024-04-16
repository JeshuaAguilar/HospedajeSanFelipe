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

/*
  Esta clase tiene un implemenmts de onInit, este método es lo primero que
  se ejecuta cuando se carga la aplicación
*/
export class EmpleadosComponent implements OnInit {

  /*
  Se arma la url de empleados, básicamente concatena el host + urlEmpleados
  http://localhost:8080/hospedaje/api/empleados
  */
  private readonly URL_EMPLEADOS = `${environment.apiHost}${Empleados.EMPLEADOS}`;
  private readonly URL_ROLES = `${environment.apiHost}${Roles.ROLES}`;

  /**
   * Básicamente, todos estos inject, se tienen que utilizar
   */
  private fomrBuilder = inject(FormBuilder);
  private _peticiones = inject(PeticionesService);
  private _alerta = inject(AlertsService);

  /**
   * Estás son las variables globales, básicamente, las variables globales, se crearn
   * Para poder usarlas en el html y muestren en pantalla la información que queremos
   */
  public isLoadedEmpleados = false;
  public empleados?: EmpleadoResponse[];
  public empleadoForm!: FormGroup;
  public roles?: CatRol[];
  public isEditing = false;

  public defineForm(): void {
    /**
     * Cuando entra en este formulario, seteo la variable golbal de isEditing en false, porque
     * Este formulario es para cuando se crea un nuevo empleado y al crearlo, aplica todas las validaciones
     * necesarias al formulario
     */
    this.isEditing = false;
    this.empleadoForm = this .fomrBuilder.group({
      /**
       * Los atributos de la izquierda, son los que tienenen que ir en los inputs, con el formControlName
       * las comillas simples '', están así, porque no tienen un valor inicial, las inicializamos como vacías
       * Porque es un nuevo empleado, así que todos los inputs, tienen que estar vacíos para que puedan escribir
       */
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

  /**
   *
   * @param empleado Recibe este parámetro, porque son los datos que se usan para llenar el formulario, con la información del empleado que se quiere editar
   * Es de tipo EmpleadoRequest, porque es lo que se recibe en el api para poder modificar el empleado, que básicamente es lo mismo que crear un empleado.
   * La diferencia aquí, es que lleva el id de empelado
   */
  private defineEditForm(empleado: EmpleadoRequest): void {
    /**
     * Cuando entra en este formulario, seteo la variable golbal de isEditing en true, porque
     * Este formulario es para cuando se edita un empleado y al crearlo, aplica todas las validaciones
     * necesarias al formulario
     */
    this.isEditing = true;

    /**
     * A esto se le llama destructuración de un objeto.
     * Básicamente es sacar todos los atributos de un objeto, para así poder utulizarlos más fácilmente
     */
    const { idEmpleado, userName, contrasenia, nombre, primerApellido, segundoApellido, noTelefono, rol, urlFoto } = empleado;

    this.empleadoForm = this .fomrBuilder.group({
      /**
       * Al crear este formulario, sí agrego el id del empelado
       * Los atributos de la izquierda, son los que tienenen que ir en los inputs, con el formControlName
       * En este caso, no se ponen comillas vacías '', porque como es para editar, sí tienen que tener información
       * los input, y esa información es la del empleado actual
       */
      idEmpleado: idEmpleado,
      usr:      [userName       , [Validators.required, Validators.minLength(3)]],
      /**
       * La constraseña si la pongo acá, pero está vacía, porque esa no la lleno en el formulario de editar,
       * y le quité el validador dew required, para que aunque esté vacía, no sea oblogatorio agregarla
       */
      psw:      [contrasenia    , [Validators.minLength(3)]],
      nombre:   [nombre         , [Validators.required, Validators.minLength(3)]],
      primerA:  [primerApellido , [Validators.required, Validators.minLength(3)]],
      segundoA: [segundoApellido, [Validators.required, Validators.minLength(3)]],
      noTel:    [noTelefono     , [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern(/^-?\d+$/)]],
      rol:      [rol            , [Validators.required]],
      //Con urlFoyo es lo mismo que la contraseña
      foto:     [urlFoto        , [Validators.minLength(3)]]
    })
  }

  /**
   * Esto también es casi lo mismo que lo de los otros formularios
   * lo que hacer este reset, básicamente es resetear el formulario
   * y limpiarlo para que se vacíen todos sus inputs, no hace otra cosa
   * Aquí no agrega validaciones ni nada
   */
  public resetForm(): void {
    this.empleadoForm.reset({
      idEmpleado:'',
      usr:       '',
      psw:       '',
      nombre:    '',
      primerA:   '',
      segundoA:  '',
      noTel:     '',
      rol:       '',
      foto:      '',
    })
  }

  public ngOnInit(): void {
    /*
      Primero se manda llamar el método para obtener todos los empleados
     */
    this.getAllEmpleados(true);
    /*
      Después se manda llamar el método de obtener catálogo de roles
      Esto para llenar el select de roles en el modal de registro y actualización
      de empelados
    */
    this.getRolCatalog();
  }

  /**
   *
   * @param isCloseLoading Esta variable, es para saber si queremos cerrar el loading o no
   * Tiene un ?, porque eso significa, que podemos mandar un valor, ya sea true/false, o no mandar nada.
   * Así que lo podemos invocar de esta manera this.getAllEmpleados(true);
   * o también de esta manera this.getAllEmpleados(), obviamente si lo mandamos así, sería como enviarle un false
   */
  private getAllEmpleados(isCloseLoading?: boolean): void {
    /*
      creamos una varaible global isLoadedEmpleados para ocultar el tbody del table
       lo mostramos hasta que tengamos la lista de empleados, eso simula un refresh a la tabla
       para mostrar a los empleados, ya sean agregados, modificados o elimnados
    */
    this.isLoadedEmpleados = false;
    /*
      Hacemos el llamado al back, por medio de una petición get y le mandamos la url de empleados
      que ya habíamos armado anteriormente
    */
    this._peticiones.getPeticion(this.URL_EMPLEADOS).subscribe({
      /**
       * El método recibe una lista de tipo EmpleadoResponse, este objeto
       * Tiene que ser igual al EmpleadoResponse que regresa el api de getAllEmpleados
      */
      next: (response: EmpleadoResponse[]) => {
        /**
         * Creamos también una variable global llamada empleados, quue es la que se va a encargar
         * de mostrar los datos en pantalla, pero primero le asignamos los valores que nos regresó el api
         * Y esos valores están en la variable response
         */
        this.empleados = response;
        /**
         * Ponenos la variable isLoadedEmpleados en true, esto porque ya tenemos lleno la variable empleados
         * Con los datos que nos regresó el api, entonces ya los podemos mostrar en pantall, esto se hace con
         * un @if(empleados) que está en el html, pero esto únicamente muestra la tabla en pantalla
         * en el HTML está la llógica para mostrar los datos
        */
        this.isLoadedEmpleados = true;

        /**
         * Esta variable isCloseLoading únicamente es para cerrar el loading, una vez
         * el api haya respondido, y únicamente se cierra el loading, cuando es la primera vez que
         * se carga la lista de empleados
        */
        if (isCloseLoading) {
          this._alerta.cierraLoading();
        }
      },
      error: (err: any) => {
        /**
         * Si hay un error al momento de cargar los empleados, acá caerá, y habrá
         * que revisar en el api, qué fue el error
        */
        this._alerta.error(err);
      },
    });
  }

  /**
   * Este método se manda llamar desde el html con el botón de Modificar/Agregar, por eso tiene el modificador de acceso como public
   * Para que el hml pueda verlo e invocarlo, y se invoca cuando ya quieres enviar a crear o modificar el empleado
   * básicamente funciona como un submit
   */
  public validaFormEmpleado(): void {
    /**
     * Lo primero que hacermos es validar el formulario de esta manera this.empleadoForm.invalid, si el invalid
     * nos regresa un true, quiere decir que el formulario es inválido, porque seguramente, alguna de las validaciones
     * que tenemos no se está cumpliendo, o algun input no se ha llenado y es requerido
     */
    if (this.empleadoForm.invalid) {
      /**
       * Si el formulario es inválido, mandamos llamar el método empleadoForm.markAllAsTouched(), esto hace que
       * muestre el error en pantalla de los input que no sean válidos
       */
      this.empleadoForm.markAllAsTouched();
    } else {
      /**
       * Si el formulario es válido, quiere decir que todas las validaciones de los inputs, son correctas
       * Lo que vamos hacer aquí, es crear un nuevo objeto de tipo EmpleadoRequest, recordando, que este objeto es el que
       * Vamos a enviar al back y tiene que ser igualito, al que recibe el api
       * Inciamos el loading, para que el usuario no pueda hacer nada durante este proceso
       */
      this._alerta.iniciaLoading();

      const empleadoRequest: EmpleadoRequest = {
        /**
         * Aquí estoy preguntando si this.isEditing es igual a true, obtengo el valor de idEmpledo, porque si está editando
         * Tiene que llevar el id, si no está editando, le mandamos 0. y eso quiere decir que es un empleado nuevo, esa lógica
         * también está en el api, donde pregunta si viene el idEmpleado, para saber si está modificando o creando nuevo empleado
         */
        idEmpleado     : this.isEditing ? this.empleadoForm.get('idEmpleado').value : 0,
        userName       : this.empleadoForm.get('usr').value,
        contrasenia    : this.empleadoForm.get('psw').value,
        nombre         : this.empleadoForm.get('nombre').value,
        primerApellido : this.empleadoForm.get('primerA').value,
        segundoApellido: this.empleadoForm.get('segundoA').value,
        noTelefono     : this.empleadoForm.get('noTel').value,
        rol            : this.empleadoForm.get('rol').value,
        urlFoto        : this.empleadoForm.get('foto').value,
      };

      /**
       * Si this.isEditing es false, quiere decir que tienen que mandar a crear un nuevo usuario
       */
      if (!this.isEditing) {
        this.agregaEmpleado(empleadoRequest);
      } else {
        /**
         * De lo contrario, mandamos llamar el de editar empleado
         */
        this.editaEmpleado(empleadoRequest);
      }
    }
  }

  private agregaEmpleado(request: EmpleadoRequest): void {

    /**
     * Le enviarmos la url de empleados que crearmos al inicio, pero como es nuevo usuario, se manda una petición
     * de tipo post, para que así el api sepa que es un post esta petición
     */

    this._peticiones.postPeticion(this.URL_EMPLEADOS, request, false).subscribe({
      next: (response: string) => {
        /**
         * Una vez que el api nos responda que el empleado ya fue creado, procederemos a resetear el formulario
         * Para que los datos que ya habíuamos escrito, se borren y el formulario quede listo para agregar o modificar algún otro empleado
         */
        this.resetForm();

        /**
         * Este código, lo único que hace es cerrar el modal, después de haber limpiado el formulario
         */

        const miModal = bootstrap.Modal.getInstance(document.getElementById('staticBackdrop'));

        if (miModal) {
          miModal.hide();
        }

        /**
         * Una vez limpiado el formulario, y cerrado el modal, procedemos a llamar nuevamente el método de getAllEmpleados, al api
         * para que nos regrese nuevamente todos los empleados, y en este caso, nos va a regresar también a nuestro nuevo empleado
         * que acabamnos de crear, aquí no le mandamos el valor de true o false, porque si le mandamos el valor de true, va a cerrar
         * el loading, y eso es correcto, pero en este caso, después del llamado de este método, llamamos un alert.
         */
        this.getAllEmpleados();

        /**
         * Depués de llamar a la lista de empleados, mostramos este alert, que muestra un mensaje diciendo, que se ha creado exitosamente
         * el empleado, y al de getAllEmpleados(), no le mandamos true, porque eso cerraría el loading y este aler también, y ya no
         * podríamos ver el mensaje de éxito, el alert, cierra automáticamente el loading, y muestra este alert
         */
        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  private editaEmpleado(request: EmpleadoRequest): void {
    /**
     * Este método de editar empleado, es exactamente lo mismo que el de crear empleado
     * únicamente lo que varía aquí, es que la petición al api, es de tipo PUT, y en api
     * con esto sabe que es una edición y no un crer un nuevo empleado
     */

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

  /**
   *
   * @param idEmpleado Aquí mandamos el idEmpledo del que queremos modificar
   * Este método se manda llamar desde el front, con el botón de editar
   */
  public modificarEmpleado(idEmpleado: number): void {
    /**
     * Como inicialmente en el onInit, cargamos la lista de epleados, lo qe hace esta función
     * Es buscar el idEmpleado en la lista de empleados, y si lo encientra, guarda ese empleado en empleadoFinded
     */
    const empleadoFinded = this.empleados.find((empleado: EmpleadoResponse) => idEmpleado === empleado.idEmpleado);


    /**
     * Aquí lo que estamos haciendo es crear un objeto de tipo empleadoRequest, porque si te das cuenta, la lista que cargamos
     * de empleados en el obinit, es de tipo empleadoResponse, que son muy similares, pero lo que necesitamos enviar para modificar
     * el usuario es un objeto de tipo EmpleadoRequest, y lo creamos de esta manera.
     *
     * Y si te das cuenta, la contraseña y lo de la foto, no la seteamos porque el objeto EmpleadoResponse, no los tiene, osea
     * Desde el api, no lo mandamos al front
     */
    const empleadoRequest: EmpleadoRequest = {
      idEmpleado     : empleadoFinded.idEmpleado,
      userName       : empleadoFinded.userName,
      contrasenia    : '',
      nombre         : empleadoFinded.nombre,
      primerApellido : empleadoFinded.primerApellido,
      segundoApellido: empleadoFinded.segundoApellido,
      noTelefono     : empleadoFinded.noTelefono,
      rol            : empleadoFinded.rol.idRol,
      urlFoto        : '',
    }

    /**
     * Una vez creado nuestro obneto de tipo EmpleadoRequest, mandamos a crear el formulario para editar
     */
    this.defineEditForm(empleadoRequest);
  }

  /**
   *
   * @param idEmpleado Este método recibe como parámetro el idEmpleado que queremos eliminar
   */
  public eliminarEmpleado(idEmpleado: number): void {
    /**
     * Mandamos llamar el inicia loading, para no permitir que el usuario haga algo más durante este proceso de eliminación
     */
    this._alerta.iniciaLoading();

    /**
     * Aquí creamos la url de diferente manera, llamamos la url de empleados y le concatenamos el id del empleado al final
     * quedaría más o menos así http://localhost:8080/hospedaje/api/empleados/5
     */
    const url = `${this.URL_EMPLEADOS}/${idEmpleado}`

    /**
     * En este caso, mandamos llamar al api con el método delete, porque este es el que el api configuramos para eliminar.
     */
    this._peticiones.deletePeticion(url).subscribe({
      next: (response: string) => {
        /**
         * Hacemos casi lo mismo que crear y modificar empleado.
         * Sólo que aquí únicamente mandamos llamar al método de getAllEmpleados(), par que nos actualice la tabla
         * Con los empleados actuales que contiene la BD, obviamente ya no debería mostra el empleado eliminado
         */
        this.getAllEmpleados();

        /**
         * Y aquí únicamente mandamos llamar el alert de éxito, para informar que el empleado ha sido eliminado
         */
        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  public getRolCatalog(): void {
    /**
     * Mandamos llamar el controller de Roles, por medio de una petición get, para obtener los roles
     * del catálogo de roles
     */
    this._peticiones.getPeticion(this.URL_ROLES).subscribe({
      /*Recibe un objeto de tipo CatRol, en este caso, es el mismo que el del CatRolEntity del api */
      next: (response: CatRol[]) => {
        /**
         * También se creó una variable global de roles, par poderlos mostrar en pantalla
         * Y se le pasan los valores que vienen del api, que aquí están en el response
         */
        this.roles = response;
        /**
         * Mandamos llamar el método que define el formulario, para que podamos usarlo
         */
        this.defineForm();
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  /**
   * Este método pregunta si el input es válido, o sea, si sus validadores, han pasado exitosamente o no
   * @param control Es el valor del control o atributo/input del formulario que queremos validar
   * @returns regresa un true o un false dependiendo la validación
   */
  public isValid(control: string): boolean {
    return this.empleadoForm.get(control).valid;
  }

  /**
   * Este método, va a preguntar ciertas validaciones extras
   * @param control Es el valor del control o atributo/input del formulario que queremos validar
   * @returns regresa un true o un false dependiendo la validación
   */
  public isFieldInvalid(control: string): boolean {
    //Primero obtenemos el atributo/input del formulario
    const formControl = this.empleadoForm.get(control);
    /**
     * hace estas preguntas, refiriéndose al atributo/input del formulario
     * Es válido?, su valor ha cambiado o el usuario ha dado clic en el input?
     * Si es true en cualquiera de estas preguntas, regresa un true
     * y si es flase, regresa también un false
     */
    return formControl.invalid && (formControl.dirty || formControl.touched);
  }

  /**
   * Estos 2 últimos métodos isValid y isFieldInvalid, los usamos para colorear los input de verde o rojo.
   * si isValid regresa un true marca el input en verde y si es false, lo marca en rojo
   * si isFieldInvalid regresa un true, marca el input en rojo y también muestra las letras chiquitas en rojo
   * Si isFieldInvalid regresa false, marca en input en verde
   */
}
