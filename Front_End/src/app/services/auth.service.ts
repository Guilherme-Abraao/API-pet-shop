import { MensagemService } from './mensagem.service';
import { EventEmitter, Injectable } from '@angular/core';
import { Cliente } from '../components/interfaces/Cliente';
import { Router } from '@angular/router';
import { UsuarioService } from './usuario.service';
import { Login } from '../components/interfaces/Login';
import { FuncionarioService } from './funcionario.service';
import { Funcionario } from '../components/interfaces/Funcionario';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  mostrarMenuEmitter = new EventEmitter<boolean>();

  private usuarioAutenticado: boolean = false;

  constructor(
    private router: Router,
    private usuarioService: UsuarioService,
    private mensagemService: MensagemService,
    private funcionarioService: FuncionarioService
  ) {}

  jsonData: any;
  cliente!: Cliente;
  funcionario!: Funcionario;
  /* Fazer login no Sistema*/
  login(login: Login) {
    this.usuarioService
      .getClienteLogin(login.email, login.senha)
      .subscribe((item) => {
        // Precisa transformar em JSON para funcionar
        this.jsonData = item;
        this.cliente = this.jsonData;

        if (this.cliente) {
          this.mostrarMenuEmitter.emit(true);
          this.usuarioAutenticado = true;
          this.usuarioService.setUserId(this.cliente.id);
          this.router.navigate(['/perfil']);
        } else {
          this.usuarioAutenticado = false;
          this.mostrarMenuEmitter.emit(false);
        }
      });

    this.funcionarioService
      .getFuncionarioLogin(login.email, login.senha)
      .subscribe((item) => {
        this.jsonData = item;
        this.funcionario = this.jsonData;

        if (this.funcionario) {
          this.usuarioAutenticado = true;
          this.mostrarMenuEmitter.emit(true);
          this.funcionarioService.setFuncionarioId(this.funcionario.id);
          this.router.navigate(['/home']);
        } else {
          this.usuarioAutenticado = false;
          this.mostrarMenuEmitter.emit(false);
        }
      });
  }

  setUsuarioAutenticado(valor: boolean) {
    this.usuarioAutenticado = valor;
  }

  usuarioEstaAutenticado() {
    return this.usuarioAutenticado;
  }
}
