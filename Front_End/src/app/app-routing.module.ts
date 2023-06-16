import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/pages/login/login.component';
import { NewClientComponent } from './components/singular-components/new-client/new-client.component';
import { PerfilComponent } from './components/pages/perfil/perfil.component';

import { AuthGuardService } from './guards/auth-guard.service';
import { NewFuncionarioComponent } from './components/singular-components/new-funcionario/new-funcionario.component';
import { NewAnimalComponent } from './components/singular-components/new-animal/new-animal.component';
import { AgendamentoComponent } from './components/pages/agendamento/agendamento.component';

const routes: Routes = [
  { path: '', canActivate: [AuthGuardService], component: HomeComponent },
  { path: 'cliente', component: NewClientComponent },
  { path: 'agendamento', component: AgendamentoComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'perfil',
      canActivate: [AuthGuardService], /* Impedir de acessar se não tiver cadastrado */
      component: PerfilComponent,
  },
  { path: 'funcionario', 
      canActivate: [AuthGuardService], 
      component: NewFuncionarioComponent },
  { path: 'animal', 
      canActivate: [AuthGuardService], 
      component: NewAnimalComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
