import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    console.log(this.email, this.password)
    this.http.post('http://localhost:8080/api/auth/login', { correo: this.email, contraseña: this.password })
      .subscribe(
        response => {
          alert('Login exitoso');
          this.router.navigate(['/menu-admin']);
        },
        error => {
          alert('Credenciales incorrectas');
        }
      );
  }
}
