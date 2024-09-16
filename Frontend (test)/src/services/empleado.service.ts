import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IEmpleado } from '../app/models/empleado.model';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
  private apiUrl = 'http://localhost:8080/api/empleados';

  constructor(private http: HttpClient) {}

  getAllEmpleados(): Observable<IEmpleado[]> {
    return this.http.get<IEmpleado[]>(this.apiUrl);
  }

  getEmpleadoById(id: number): Observable<IEmpleado> {
    return this.http.get<IEmpleado>(`${this.apiUrl}/${id}`);
  }

  createEmpleado(empleado: IEmpleado): Observable<IEmpleado> {
    return this.http.post<IEmpleado>(this.apiUrl, empleado);
  }

  updateEmpleado(id: number, empleado: IEmpleado): Observable<IEmpleado> {
    return this.http.put<IEmpleado>(`${this.apiUrl}/${id}`, empleado);
  }

  deleteEmpleado(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
