import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer/footer.component';
import { MensagensSistemaComponent } from './components/singular-components/mensagens-sistema/mensagens-sistema.component';
import { LoginComponent } from './components/pages/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HomeComponent } from './components/pages/home/home.component';
import { AuthService } from './services/auth.service';
import { AuthModule } from './auth/auth.module';

import { ClientFormComponent } from './components/client-form/client-form.component';
import { NewClientComponent } from './components/singular-components/new-client/new-client.component';
import { PerfilComponent } from './components/pages/perfil/perfil.component';
import { ClientFormColumnComponent } from './components/client-form-column/client-form-column.component';

import { AuthGuardService } from './guards/auth-guard.service';
import { NewFuncionarioComponent } from './components/singular-components/new-funcionario/new-funcionario.component';
import { FuncionarioFormComponent } from './components/funcionario-form/funcionario-form.component';
import { NewAnimalComponent } from './components/singular-components/new-animal/new-animal.component';
import { AnimalFormComponent } from './components/animal-form/animal-form.component';
import { AgendamentoComponent } from './components/pages/agendamento/agendamento.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import { ClientFormUpdateComponent } from './components/client-form-update/client-form-update.component';
import { ScheduleModule, RecurrenceEditorModule, DayService, WeekService, WorkWeekService, MonthService, MonthAgendaService, AgendaService, TimelineViewsService, TimelineMonthService } from '@syncfusion/ej2-angular-schedule';
import { CalendarioComponent } from './components/calendario/calendario.component';
import { UpdateClientComponent } from './components/singular-components/update-client/update-client.component';
import { VisualizarAgendamentoComponent } from './components/visualizar-agendamento/visualizar-agendamento.component'

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    MensagensSistemaComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    ClientFormComponent,
    NewClientComponent,
    PerfilComponent,
    ClientFormColumnComponent,
    NewFuncionarioComponent,
    FuncionarioFormComponent,
    NewAnimalComponent,
    AnimalFormComponent,
    AgendamentoComponent,
    ClientFormUpdateComponent,
    CalendarioComponent,
    UpdateClientComponent,
    VisualizarAgendamentoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    ScheduleModule, 
    RecurrenceEditorModule,
    AuthModule
  ],
  providers: [AuthService, AuthGuardService, DayService, WeekService, WorkWeekService, MonthService, AgendaService, MonthAgendaService, TimelineViewsService, TimelineMonthService],
  bootstrap: [AppComponent],
})
export class AppModule {}
