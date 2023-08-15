import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Cliente } from '../interfaces/Cliente';
import { MensagemService } from 'src/app/services/mensagem.service';
import { Funcionario } from '../interfaces/Funcionario';
import { FuncionarioService } from 'src/app/services/funcionario.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {

  /* Para armazenar dados do usuário ao iniciar */
  id: number = 0;
  jsonData: any;
  cliente!: Cliente;
  funcionario!: Funcionario;

  /* Campo para validar */
  verificaCliente: boolean = false;

  constructor(
    private messagemService: MensagemService,
    private usuarioService: UsuarioService,
    private funcionarioService: FuncionarioService
  ) {}

  ngOnInit(): void {

    /* Requisição GET para buscar os dados do Usuario,
    this.cliente possui os dados do cliente que fez o login*/

    this.usuarioService.getCliente(this.usuarioService.getUserId()).subscribe((item) => {
      this.jsonData = item;
      this.cliente = this.jsonData;
    }, (error) => {
      // Se não for cliente, tente buscar como funcionário
    this.funcionarioService.getFuncionario(this.funcionarioService.getFuncionarioId()).subscribe((item) => {
      this.jsonData = item;
      this.funcionario = this.jsonData;
    })
    });
  }

  validarCliente(): boolean{
    if(this.cliente.role == "USER"){
       return true;
    } return false;
  }

  validarFuncionario(): boolean{
    if(this.funcionario.role == "FUNC"){
       return true;
    } return false;
  }

  
}