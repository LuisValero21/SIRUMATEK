import { Component, OnInit } from '@angular/core';
import { Empleado } from '../models/empleado.model';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-lista-empleados',
  templateUrl: './lista-empleados.component.html',
  styleUrls: ['./lista-empleados.component.css'],
})
export class ListaEmpleadosComponent implements OnInit {

  empleados: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('/api/empleados/listar')
      .subscribe(data => {
        this.empleados = data;
      });
  }

  onBack() {
    window.history.back();
  }
}
