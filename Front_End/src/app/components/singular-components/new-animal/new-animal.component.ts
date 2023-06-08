import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnimalService } from 'src/app/services/animal.service';
import { MensagemService } from 'src/app/services/mensagem.service';

@Component({
  selector: 'app-new-animal',
  templateUrl: './new-animal.component.html',
  styleUrls: ['./new-animal.component.css']
})
export class NewAnimalComponent implements OnInit {

  /* Textos dinâmicos para o formulário */
  btnText = 'Confirmar';
  titulo = 'Cadastrar Animal';

  constructor(private animalService: AnimalService,
              private messagemService: MensagemService,
              private router: Router){ }
              
  ngOnInit(): void {
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
