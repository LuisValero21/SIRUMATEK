import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from 'express';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly loginUrl = 'http://localhost:8080/api/auth/login';
  router: any;

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(this.loginUrl, { email: email, password: password });
  }

  logout(): void {
    // Elimina el token de autenticación del localStorage o sessionStorage
    localStorage.removeItem('authToken'); // Si usas localStorage
    sessionStorage.removeItem('authToken'); // Si usas sessionStorage (opcional)

    // Elimina cualquier otro dato relevante del almacenamiento
    localStorage.removeItem('refreshToken'); // Opcional
    sessionStorage.removeItem('refreshToken'); // Opcional

    // Redirige al usuario a la página de login
    this.router.navigate(['/login']);

    console.log('Usuario desconectado');
  }
}
