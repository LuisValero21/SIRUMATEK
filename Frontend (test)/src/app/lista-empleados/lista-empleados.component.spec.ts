import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ListaEmpleadosComponent } from './lista-empleados.component';
import { IEmpleado } from '../models/empleado.model';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';

describe('ListaEmpleadosComponent', () => {
  let component: ListaEmpleadosComponent;
  let fixture: ComponentFixture<ListaEmpleadosComponent>;
  let httpMock: HttpTestingController;
  let debugElement: DebugElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaEmpleadosComponent],
      imports: [HttpClientTestingModule, RouterTestingModule],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaEmpleadosComponent);
    component = fixture.componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
    debugElement = fixture.debugElement;
    fixture.detectChanges();
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('debería crear el componente', () => {
    expect(component).toBeTruthy();
  });

  it('debería mostrar "Cargando empleados..." si no hay empleados cargados y no hay error', () => {
    const loadingText = debugElement.query(By.css('div.text-center p')).nativeElement;
    expect(loadingText.textContent).toContain('Cargando empleados...');
  });

  it('debería cargar empleados correctamente desde el API', () => {
    const empleadosMock: IEmpleado[] = [];

    const req = httpMock.expectOne('http://localhost:3000/api/empleados/listar');
    expect(req.request.method).toBe('GET');
    req.flush(empleadosMock);

    fixture.detectChanges();

    expect(component.empleados.length).toBe(empleadosMock.length);
  });

  it('debería manejar el error si la solicitud de empleados falla', () => {
    const errorMessage = 'No se pudieron cargar los empleados. Intente nuevamente más tarde.';

    const req = httpMock.expectOne('http://localhost:3000/api/empleados/listar');
    req.flush('Error de carga', { status: 500, statusText: 'Server Error' });

    fixture.detectChanges();

    expect(component.error).toBe(errorMessage);
    const errorElement = debugElement.query(By.css('.alert.alert-danger')).nativeElement;
    expect(errorElement.textContent).toContain(errorMessage);
  });

  it('debería navegar hacia atrás cuando se presiona el botón "Volver al Menú"', () => {
    spyOn(window.history, 'back');
    const backButton = debugElement.query(By.css('button.btn-back')).nativeElement;

    backButton.click();
    expect(window.history.back).toHaveBeenCalled();
  });
});
