import { EventEmitter, Injectable } from '@angular/core';
import { Cliente } from '../components/interfaces/Cliente';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  mostrarMenuEmitter = new EventEmitter<boolean>(); 

  private usuarioAutenticado: boolean = false;

  constructor(private router: Router) { }


  login(cliente: Cliente){
    if(cliente.cpf == "123" && cliente.senha == "123"){

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
    return this.usuarioAutenticado
  }

}
