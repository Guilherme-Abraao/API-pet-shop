import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { CpfService } from 'src/app/services/validacao-cpf.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Cliente } from '../interfaces/Cliente';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MensagemService } from 'src/app/services/mensagem.service';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.css'],
})
export class ClientFormComponent implements OnInit {
  /* Enviar para o componente pai - New-Cliente */
  @Output() onSubmit = new EventEmitter<String>();

  /* Receber do componente pai - New-Cliente */
  @Input() btnText!: string;
  @Input() titulo!: string;

  /* FormGroup do formulario e fatimes é um ícone*/
  userForm!: FormGroup;
  faTimes = faTimes;

  constructor(private usuarioService: UsuarioService, 
              private http: HttpClient, 
              private messagemService: MensagemService, 
              private cpfService: CpfService) {}

  /* Inicialização do formulário */
  ngOnInit(): void {
    this.userForm = new FormGroup({
      nome: new FormControl('', [Validators.required]),
      cpf: new FormControl('', [Validators.required]),
      dataNascimento: new FormControl('', [Validators.required]),
      telefone: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      senha: new FormControl('', [Validators.required]),
      confirmacaoSenha: new FormControl('', [Validators.required]),
    });
  }

  /* GETs dos itens do formulário */
  get nome() {
    return this.userForm.get('nome')!;
  }
  get cpf() {
    return this.userForm.get('cpf')!;
  }
  get dataNascimento() {
    return this.userForm.get('dataNascimento')!;
  }
  get telefone() {
    return this.userForm.get('telefone')!;
  }
  get email() {
    return this.userForm.get('email')!;
  }
  get senha() {
    return this.userForm.get('senha')!;
  }
  get confirmacaoSenha() {
    return this.userForm.get('confirmacaoSenha')!;
  }

  /* Submissão do formulário */ 
  submit() {
    if (this.userForm.invalid) { // Se for inválido invalida a submissão
      return;
    }

    /* Criando um FormData com o formulário completo válidado*/
    if (this.userForm.valid) {
      const formData = {
        nome: this.userForm.value.nome,
        cpf: this.userForm.value.cpf,
        dataNascimento: this.userForm.value.dataNascimento,
        telefone: this.userForm.value.telefone,
        email: this.userForm.value.email,
        senha: this.userForm.value.senha,
        confirmacaoSenha: this.userForm.value.confirmacaoSenha,
      };

      /* Transformando em JSON */
      const jsonData = JSON.stringify(formData);

      /* Enviando JSON para o componente pai - NewClient*/
      this.onSubmit.emit(jsonData);
    }
  }

}
