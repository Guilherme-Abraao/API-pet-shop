import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  momentForm!: FormGroup;
  faTimes = faTimes;
  cpfValido: boolean = false;

  constructor() {}

  /* Inicialização do formulário */
  ngOnInit(): void {
    this.momentForm = new FormGroup({
      id: new FormControl(''),
      cpf: new FormControl('', [Validators.required]),
      senha: new FormControl('', [Validators.required]),
    });
  }

  get cpf() {
    return this.momentForm.get('cpf')!;
  }
  get senha() {
    return this.momentForm.get('senha')!;
  }

  submit() {
    if (this.momentForm.invalid) {
      return;
    }
    return 1;
}
}