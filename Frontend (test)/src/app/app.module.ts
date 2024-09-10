import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule, DatePipe } from '@angular/common';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MenuAdminComponent } from '././menu-admin/menu-admin.component';
import { RegistrarEmpleadoComponent } from './registrar-empleado/registrar-empleado.component';
import { ListaEmpleadosComponent } from './lista-empleados/lista-empleados.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from '../services/auth.service';
/* import { DatePipe } from './date.pipe'; */

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'menu-admin', component: MenuAdminComponent },
  { path: 'registrar-empleado', component: RegistrarEmpleadoComponent },
  { path: 'lista-empleados', component: ListaEmpleadosComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    LoginComponent,
    MenuAdminComponent,
    RegistrarEmpleadoComponent,
    ListaEmpleadosComponent,
    // otros componentes, pipes, directivas
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    DatePipe,
    CommonModule,
    HttpClientModule,
    RouterOutlet
  ],
  providers: [AuthService]
})
export class AppModule { }
