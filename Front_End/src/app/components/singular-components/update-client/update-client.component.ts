import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MensagemService } from 'src/app/services/mensagem.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { catchError } from 'rxjs';

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.css']
})
export class UpdateClientComponent implements OnInit{

ngOnInit(): void {
}

constructor(private usuarioService: UsuarioService,
  private messagemService: MensagemService,
  private router: Router){ }

  
async createdHandler(cliente: any){

  /* Mudando tipo de dado para JSON */
  const jsonData = JSON.stringify(cliente);

  /* Enviando cliente para o Service */
    this.usuarioService.updateCliente(cliente)
    .pipe(
      catchError((error) => {
        this.messagemService.add('Erro ao atualizar o cliente: ' + error.error.message);
        throw error;
      })
  )
    .subscribe(() => {
      this.messagemService.add('Atualização realizada com sucesso!');
      this.router.navigate(['/perfil']);
    });

}
}
