

import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent implements OnInit{

  btnText = 'Confirmar';
  titulo = 'Criar Conta'; 

  constructor(private usuarioService: UsuarioService){ }
  ngOnInit(): void {
  }

  /* Metodo assincrono para enviar para API */
  async createdHandler(cliente: Cliente){
    const formData = new FormData();

    formData.append("nome", cliente.nome); 
    formData.append("cpf", cliente.cpf); 
    formData.append("dataNascimento", cliente.dataNascimento); 
    formData.append("email", cliente.email); 
    formData.append("telefone", cliente.telefone); 
    formData.append("senha", cliente.senha); 
    formData.append("confirmacaoSenha", cliente.confirmacaoSenha); 
  
  /* ENVIAR para o SERVICE */

  await this.usuarioService.createCliente(formData).subscribe();
  

  /* Mensagem */

  /* Redirect */

  }

}

