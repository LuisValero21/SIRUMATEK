import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private router: Router, private AuthService: AuthService) {}

  login() {

    this.AuthService.login(this.email, this.password).subscribe(
      (response) => {
        console.log(response)
        this.router.navigate(['/menu-admin']);
      },
      (error) => {
        console.log(error)
        alert('Credenciales incorrectas');
      }
    )
  }
}
