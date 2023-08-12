import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Cliente } from '../interfaces/Cliente';
import { MensagemService } from 'src/app/services/mensagem.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {

  /* Para armazenar dados do usuário ao iniciar */
  id: number = 0;
  jsonData: any;
  cliente!: Cliente;

  /* Campo para validar */
  verificaCliente: boolean = false; 

  constructor(
    private messagemService: MensagemService,
    private usuarioService: UsuarioService
  ) {}

  ngOnInit(): void {
    this.id = this.usuarioService.getUserId();

    /* Requisição GET para buscar os dados do Usuario, 
    this.cliente possui os dados do cliente que fez o login*/

    this.usuarioService.getCliente(this.id).subscribe((item) => {
      this.jsonData = item;
      this.cliente = this.jsonData;
    });
  }

  validarCliente(): boolean{
    if(this.cliente.role == "USER"){
       return true; 
    } return false; 
  }

  
}
