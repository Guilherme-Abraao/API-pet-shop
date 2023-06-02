import { AuthService } from './../../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  userForm!: FormGroup;
  faTimes = faTimes;

  constructor(private authService: AuthService) {}

  /* Inicialização do formulário */
  ngOnInit(): void {
    this.userForm = new FormGroup({
      cpf: new FormControl('', [Validators.required]),
      senha: new FormControl('', [Validators.required]),
    });
  }

  get cpf() {
    return this.userForm.get('cpf')!;
  }
  get senha() {
    return this.userForm.get('senha')!;
  }

  submit() {
    // console.log(this.userForm.value);

    this.authService.login(this.userForm.value)
}
}