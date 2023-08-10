import { EventEmitter, Injectable } from '@angular/core';
import { Cliente } from '../components/interfaces/Cliente';
import { Router } from '@angular/router';
import { UsuarioService } from './usuario.service';
import { Login } from '../components/interfaces/Login';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  mostrarMenuEmitter = new EventEmitter<boolean>(); 

  private usuarioAutenticado: boolean = false;

  constructor(private router: Router,
              private usuarioService: UsuarioService 
    ) { }

  jsonData: any;
  cliente!: Cliente;

  /* Fazer login no Sistema*/
  login(login: Login){
    
    this.usuarioService.getClienteLogin(login.email, login.senha).subscribe((item) => {
      // Precisa transformar em JSON para funcionar 
      this.jsonData = item;
      this.cliente = this.jsonData;
    });

    if(this.cliente){

      this.usuarioAutenticado = true;

      this.mostrarMenuEmitter.emit(true);

      this.router.navigate(['/cliente']);

    } else {

      this.usuarioAutenticado = false;
      this.mostrarMenuEmitter.emit(false);
      this.router.navigate(['/login']);
      
    }
  }

  usuarioEstaAutenticado(){
    return this.usuarioAutenticado;
  }

}
