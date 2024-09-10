import { Component } from '@angular/core';
import { EmpleadoService } from '../../services/empleado.service';
import { Router } from '@angular/router';
import { Empleado } from '../models/empleado.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registrar-empleado',
  templateUrl: './registrar-empleado.component.html',
  styleUrls: ['./registrar-empleado.component.css']
})
export class RegistrarEmpleadoComponent {

  empleado: any = {};

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post('/api/empleados/registrar', this.empleado)
      .subscribe(response => {
        alert('Empleado registrado con éxito');
        this.router.navigate(['/lista-empleados']);
      });
  }

  onBack() {
    this.router.navigate(['/menu-admin']);
  }
}
