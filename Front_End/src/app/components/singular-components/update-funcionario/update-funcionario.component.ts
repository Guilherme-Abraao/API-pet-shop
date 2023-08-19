import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { MensagemService } from 'src/app/services/mensagem.service';
import { catchError } from 'rxjs';

@Component({
  selector: 'app-update-funcionario',
  templateUrl: './update-funcionario.component.html',
  styleUrls: ['./update-funcionario.component.css']
})
export class UpdateFuncionarioComponent {

  ngOnInit(): void {
  }
  
  constructor(private funcionarioService: FuncionarioService,
    private messagemService: MensagemService,
    private router: Router){ }
  
  async createdHandler(cliente: any){
  
    /* Mudando tipo de dado para JSON */
    const jsonData = JSON.stringify(cliente);
  
    /* Enviando funcionario para o Service */
      this.funcionarioService.updateFuncionario(cliente)
      .pipe(
        catchError((error) => {
          this.messagemService.add('Erro ao atualizar o funcionário: ' + error.error.message);
          throw error;
        })
    )
      .subscribe(() => {
        this.messagemService.add('Atualização realizada com sucesso!');
        this.router.navigate(['/perfil']);
      });
  
  }
}
