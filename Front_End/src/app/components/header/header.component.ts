import { Component, EventEmitter, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  /* Esconder o menu quando fizer logout*/
  mostrarMenuEmitter = new EventEmitter<boolean>(); 

  constructor(
    private messagemService: MensagemService,
    private usuarioService: UsuarioService,
    private router: Router
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

  logout(){
    this.mostrarMenuEmitter.emit(false);
    this.usuarioService.setUserId(0);
  }

  
}
