import { Component, OnInit } from '@angular/core';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { MensagemService } from 'src/app/services/mensagem.service';

@Component({
  selector: 'app-mensagens-sistema',
  templateUrl: './mensagens-sistema.component.html',
  styleUrls: ['./mensagens-sistema.component.css']
})
export class MensagensSistemaComponent implements OnInit{

  faTimes = faTimes; 

  constructor(public mensagemService: MensagemService){
  }

  ngOnInit(): void {
    
  }

  

}
