import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MensagemService } from 'src/app/services/mensagem.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent implements OnInit{

  /* Textos dinâmicos para o formulário */
  btnText = 'Confirmar';
  titulo = 'Criar Conta';

  constructor(private usuarioService: UsuarioService,
              private messagemService: MensagemService,
              private router: Router){ }
              
  ngOnInit(): void {
  }

  /* Metodo assincrono para enviar para API */
  async createdHandler(cliente: any){
    
    /* Mudando tipo de dado para JSON */
    const jsonData = JSON.stringify(cliente);

    /* Enviando cliente para o Service */
    await this.usuarioService.createCliente(cliente).subscribe();
  
    /* Mensagem de retorno do sistema */
    this.messagemService.add('Cadastro realizado com sucesso!');

    /* Redirect */
    this.router.navigate(['/login']);
  }

}

