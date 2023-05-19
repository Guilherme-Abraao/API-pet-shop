

import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MensagemService } from 'src/app/services/mensagem.service';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent implements OnInit{

  /* Textos dinâmicos para o formulário */
  btnText = 'Confirmar';
  titulo = 'Criar Conta'; 

  constructor(private usuarioService: UsuarioService, private messagemService: MensagemService){ }
  ngOnInit(): void {
  }

  /* Metodo assincrono para enviar para API */
  async createdHandler(cliente: any){
    const clienteJSON = {
      nome: cliente.nome,
      cpf: cliente.cpf,
      dataNascimento: cliente.dataNascimento,
      email: cliente.email,
      telefone: cliente.telefone,
      senha: cliente.senha,
      confirmacaoSenha: cliente.confirmacaoSenha
    };
  
  /* ENVIAR para o SERVICE um JSON*/
  await this.usuarioService.createCliente(clienteJSON).subscribe();
  
  /* Mensagem de retorno do sistema */
  this.messagemService.add('Cadastro realizado com sucesso!'); 

  /* Redirect */

  }

}

