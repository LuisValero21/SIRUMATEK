import { Component, OnInit } from '@angular/core';
import { IEmpleado } from '../models/empleado.model';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-lista-empleados',
  templateUrl: './lista-empleados.component.html',
  styleUrls: ['./lista-empleados.component.css'],
})
export class ListaEmpleadosComponent implements OnInit {

  empleados: IEmpleado[] = [];
  error: string = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('/api/empleados/listar')
      .subscribe(data => {
        console.log('Datos recibidos:', data);
        this.empleados = data;
      }, error => {
        console.error("Error al obtener empleados", error);
        this.error = 'No se pudieron cargar los empleados. Intente nuevamente más tarde.';
      });
  }

  onBack() {
    window.history.back();
  }
}
