import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from 'src/app/components/interfaces/Cliente';
import { Funcionario } from 'src/app/components/interfaces/Funcionario';
import { Login } from 'src/app/components/interfaces/Login';

const LS_CHAVE: string="usuarioLogado"
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() { }

  public get clienteLogado(): Cliente{
    let usu = localStorage[LS_CHAVE];
    return (usu ? JSON.parse(localStorage[LS_CHAVE]): null);
  }

  public set clienteLogado(cliente: Cliente){
    localStorage[LS_CHAVE] = JSON.stringify(cliente); 
  }

  public get funcionarioLogado(): Funcionario{
    let usu = localStorage[LS_CHAVE];
    return (usu ? JSON.parse(localStorage[LS_CHAVE]): null);
  }

  public set funcionarioLogado(funcionario: Funcionario){
    localStorage[LS_CHAVE] = JSON.stringify(funcionario); 
  }

  logout(){
    delete localStorage[LS_CHAVE];
  }

}
