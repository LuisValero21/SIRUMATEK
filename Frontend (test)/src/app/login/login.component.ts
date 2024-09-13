import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
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
    this.http.post('http://localhost:8080/api/auth/login', { email: this.email, password: this.password },
      { headers: { 'Content-Type': 'application/json' } }
    )
      .subscribe(
        response => {
          alert('Login exitoso');
          this.router.navigate(['/menu-admin']);
        },
        error => {
          console.log(error)
          alert('Credenciales incorrectas');
        }
      );
  }
}
