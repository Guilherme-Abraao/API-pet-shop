import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/pages/login/login.component';
import { ClientFormComponent } from './components/client-form/client-form.component';
import { NewClientComponent } from './components/singular-components/new-client/new-client.component';
import { PerfilComponent } from './components/pages/perfil/perfil.component';
import { AuthGuardService } from './guards/auth-guard.service';

const routes: Routes = [
  { path: '', 
          canActivate: [AuthGuardService],
          component: HomeComponent },
  { path: 'cliente', component: NewClientComponent },
  { path: 'login', component: LoginComponent },
  { path: 'perfil', 
          canActivate: [AuthGuardService], /* Impedir de acessar se não tiver cadastrado */
          component: PerfilComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
