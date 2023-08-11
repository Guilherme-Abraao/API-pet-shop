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
import { ScheduleComponent } from '@syncfusion/ej2-angular-schedule';
import { CalendarioComponent } from './components/calendario/calendario.component';
import { UpdateClientComponent } from './components/singular-components/update-client/update-client.component';

const routes: Routes = [
  { path: '', canActivate: [AuthGuardService], component: HomeComponent },
  { path: 'cliente', component: NewClientComponent },
  { path: 'agendamento', component: AgendamentoComponent },
  { path: 'login', component: LoginComponent },
  
  {
    path: 'perfil/:id',
      component: PerfilComponent,
  },
  {
    path: 'editar-perfil',
      canActivate: [AuthGuardService],// Proteger rotas
      component: UpdateClientComponent,
  },
  { path: 'funcionario/:id',
      canActivate: [AuthGuardService],
      component: NewFuncionarioComponent },

  { path: 'animal/:id',
      component: NewAnimalComponent },
  
  { path: 'tabela-agendamentos',  /* A tabela de agendamentos só pode ser acessada por funcionários */
      canActivate: [AuthGuardService],
      component: CalendarioComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
