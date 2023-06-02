import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MensagemService } from 'src/app/services/mensagem.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';

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
    this.usuarioService.createCliente(cliente)
    .pipe(
      catchError((error) => {
        this.messagemService.add('Erro ao criar o cliente: ' + error.error.message);
        throw error;
      })
    )
    .subscribe(() => {
      this.messagemService.add('Cadastro realizado com sucesso!');
      this.router.navigate(['/login']);
    });
  }

}

