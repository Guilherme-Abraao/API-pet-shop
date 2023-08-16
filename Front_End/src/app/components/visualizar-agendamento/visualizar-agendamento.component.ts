import { Funcionario } from './../interfaces/Funcionario';
import { Component, Input } from '@angular/core';
import { Cliente } from '../interfaces/Cliente';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { MensagemService } from 'src/app/services/mensagem.service';
import { AgendamentoService } from 'src/app/services/agendamento.service';
import { Agendamento } from '../interfaces/Agendamento';

@Component({
  selector: 'app-visualizar-agendamento',
  templateUrl: './visualizar-agendamento.component.html',
  styleUrls: ['./visualizar-agendamento.component.css'],
})
export class VisualizarAgendamentoComponent {

  cliente!: Cliente;
  funcionario!: Funcionario;
  jsonData: any;

  constructor(private usuarioService: UsuarioService, private route: ActivatedRoute, private funcionarioService: FuncionarioService, private agendamentoService: AgendamentoService, private mensagemService: MensagemService){}


  ngOnInit(): void {
    /* Requisição GET para buscar os dados do Usuario e mostrar no Perfil dele*/
    const UserId = this.usuarioService.getUserId();

    if (UserId != 0) {
      this.usuarioService.getCliente(UserId).subscribe((item) => {
        this.jsonData = item;
        this.cliente = this.jsonData;
      });
    }
  }

  excluirAgendamento(idAgendamento: number) {
    this.agendamentoService.deleteAgendamento(idAgendamento).subscribe(
      () => {
       
        this.cliente.animais.forEach((animal) => {
          animal.agendamentos = animal.agendamentos.filter(
            (agendamento) => agendamento.id !== idAgendamento
          );
        });
  
        this.mensagemService.add('Agendamento excluído com sucesso.');
      },
      (error) => {
        this.mensagemService.add('Erro ao tentar excluir o agendamento.');
      }
    );
  }
}
