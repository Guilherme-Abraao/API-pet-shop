import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Funcionario } from '../../interfaces/Funcionario';
import { MensagemService } from 'src/app/services/mensagem.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { Cliente } from '../../interfaces/Cliente';

@Component({
  selector: 'app-new-funcionario',
  templateUrl: './new-funcionario.component.html',
  styleUrls: ['./new-funcionario.component.css']
})
export class NewFuncionarioComponent implements OnInit {

   /* Textos dinâmicos para o formulário */
   btnText = 'Confirmar';
   titulo = 'Criar Conta';
 
   //CRIAR O FUNCIONARIOSERVICE
   constructor(private funcionarioService: FuncionarioService,
               private messagemService: MensagemService,
               private router: Router){ }
               
   ngOnInit(): void {
   }
   /* Metodo assincrono para enviar para API */
   async createdHandler(funcionario: any){
     
     /* Mudando tipo de dado para JSON */
     const jsonData = JSON.stringify(funcionario);
 
     /* Enviando funcionario para o Service */
     await this.funcionarioService.createFuncionario(funcionario).subscribe();
   
     /* Mensagem de retorno do sistema */
     this.messagemService.add('Cadastro realizado com sucesso!');
 
     /* Redirect */
     this.router.navigate(['/login']);
   }
 
}
