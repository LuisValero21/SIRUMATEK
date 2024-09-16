import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/api/auth/login';

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(this.loginUrl, { email: email, password: password });
  }

  logout(): void {
    // Lógica de cierre de sesión
    console.log('Usuario desconectado');
  }
}
