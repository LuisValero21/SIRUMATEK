import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  correo: string = ''; // Asegúrate de que esta propiedad esté definida

  constructor(private http: HttpClient, private router: Router) {}

  sendResetLink() {
    this.http.post('/api/auth/forgot-password', { email: this.correo })
      .subscribe(
        response => {
          alert('Enlace de recuperación enviado');
          this.router.navigate(['/login']);
        },
        error => {
          alert('Error al enviar el enlace de recuperación');
        }
      );
  }
}
