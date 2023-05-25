import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/pages/login/login.component';
import { ClientFormComponent } from './components/client-form/client-form.component';
import { NewClientComponent } from './components/singular-components/new-client/new-client.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'cliente', component: NewClientComponent},
  {path: 'login', component: LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
