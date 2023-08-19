import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Funcionario } from '../../interfaces/Funcionario';
import { MensagemService } from 'src/app/services/mensagem.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { catchError } from 'rxjs';

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
     this.funcionarioService.createFuncionario(funcionario)
    .pipe(
      catchError((error) => {
        this.messagemService.add('Erro ao criar o funcionário: ' + error.error.message);
        throw error;
      })
    )
    .subscribe(() => {
      this.messagemService.add('Cadastro realizado com sucesso!');
      this.router.navigate(['/perfil']);
    });
   }
 
}
