import { UsuarioService } from 'src/app/services/usuario.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnimalService } from 'src/app/services/animal.service';
import { MensagemService } from 'src/app/services/mensagem.service';
import { Cliente } from '../../interfaces/Cliente';

@Component({
  selector: 'app-new-animal',
  templateUrl: './new-animal.component.html',
  styleUrls: ['./new-animal.component.css']
})
export class NewAnimalComponent implements OnInit {

  /* Textos dinâmicos para o formulário */
  btnText = 'Confirmar';
  titulo = 'Cadastrar Animal';

  /* Para armazenar dados do usuário ao iniciar */
  id: number=0; 
  jsonData: any;
  cliente!: Cliente;

  constructor(private animalService: AnimalService,
              private messagemService: MensagemService,
              private usuarioService: UsuarioService,
              private router: Router){ }
              
  ngOnInit(): void {
    this.id = this.usuarioService.getUserId();

    /* Requisição GET para buscar os dados do Usuario, 
    this.cliente possui os dados do cliente que fez o login*/
    this.usuarioService.getCliente(this.id).subscribe((item) => {

      this.jsonData = item;
      this.cliente = this.jsonData;
    });
  }

  /* Metodo assincrono para enviar para API */
  async createdHandler(animal: any){
    
    /* Mudando tipo de dado para JSON */
    const jsonData = JSON.stringify(animal);

    /* Enviando animal para o Service */
    await this.animalService.createAnimal(animal).subscribe();
  
    /* Mensagem de retorno do sistema */
    this.messagemService.add('Cadastro realizado com sucesso!');
  }

  
}
