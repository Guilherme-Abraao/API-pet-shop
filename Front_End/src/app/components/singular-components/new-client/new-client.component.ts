

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
    
    // const jsonData = JSON.parse(JSON.stringify(cliente));

    // console.log(JSON.parse(JSON.stringify(cliente)));

    await this.usuarioService.createCliente(cliente).subscribe();
    

    /* Transformando em JSON */
    // const jsonData: object = JSON.parse(JSON.stringify(clienteJSON));

    // console.log(jsonData);
  
  /* ENVIAR para o SERVICE um JSON*/
  
  
  /* Mensagem de retorno do sistema */
  this.messagemService.add('Cadastro realizado com sucesso!'); 

  /* Redirect */

  }

}

