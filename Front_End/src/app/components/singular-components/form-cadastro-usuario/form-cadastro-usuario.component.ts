import { Component, OnInit } from '@angular/core';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-form-cadastro-usuario',
  templateUrl: './form-cadastro-usuario.component.html',
  styleUrls: ['./form-cadastro-usuario.component.css']
})
export class FormCadastroUsuarioComponent implements OnInit{
  faTimes = faTimes;

  constructor(){ }
  ngOnInit(): void {
    
  }

}
