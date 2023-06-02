import { Component, Input, OnInit } from '@angular/core';
import { Cliente } from '../interfaces/Cliente';

@Component({
  selector: 'app-client-form-column',
  templateUrl: './client-form-column.component.html',
  styleUrls: ['./client-form-column.component.css']
})
export class ClientFormColumnComponent implements OnInit{

  /* Receber do componente pai */
  @Input() btnText!: string;
  @Input() titulo!: string;
  @Input() cliente!: Cliente;

  constructor(){ }

  ngOnInit(): void {
    
  }

  

}
