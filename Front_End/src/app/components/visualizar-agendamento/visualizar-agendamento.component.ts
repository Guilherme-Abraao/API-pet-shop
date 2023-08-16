import { Funcionario } from './../interfaces/Funcionario';
import { Component } from '@angular/core';
import { Cliente } from '../interfaces/Cliente';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';

@Component({
  selector: 'app-visualizar-agendamento',
  templateUrl: './visualizar-agendamento.component.html',
  styleUrls: ['./visualizar-agendamento.component.css'],
})
export class VisualizarAgendamentoComponent {
  
  cliente!: Cliente;
  funcionario!: Funcionario; 
  jsonData: any;

  constructor(private usuarioService: UsuarioService, private route: ActivatedRoute, private funcionarioService: FuncionarioService){}


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
}
