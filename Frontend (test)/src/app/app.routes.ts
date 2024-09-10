import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MenuAdminComponent } from './menu-admin/menu-admin.component';
import { RegistrarEmpleadoComponent } from './registrar-empleado/registrar-empleado.component';
import { ListaEmpleadosComponent } from './lista-empleados/lista-empleados.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'menu-admin', component: MenuAdminComponent },
  { path: 'registrar-empleado', component: RegistrarEmpleadoComponent },
  { path: 'lista-empleados', component: ListaEmpleadosComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];
