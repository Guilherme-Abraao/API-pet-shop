import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  btnText = 'Confirmar';
  titulo = 'Criar Conta'; 

  title = 'Front_End';

  constructor(){ }
  ngOnInit(): void {
    
  }

}
