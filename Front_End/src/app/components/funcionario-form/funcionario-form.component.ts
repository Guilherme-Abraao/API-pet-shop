import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { MensagemService } from 'src/app/services/mensagem.service';
import { Funcionario } from '../interfaces/Funcionario';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Response } from '../interfaces/MensagemSistema';
import { FuncionarioService } from 'src/app/services/funcionario.service';

@Component({
  selector: 'app-funcionario-form',
  templateUrl: './funcionario-form.component.html',
  styleUrls: ['./funcionario-form.component.css']
})
export class FuncionarioFormComponent implements OnInit{
  /* Enviar para o componente pai - New-Cliente */
  @Output() onSubmit = new EventEmitter<Funcionario>();

  /* Receber do componente pai - New-Cliente */
  @Input() btnText!: string;
  @Input() titulo!: string;

  /* FormGroup do formulario e fatimes é um ícone*/
  userForm!: FormGroup;
  faTimes = faTimes;

  /* Construtor chamando os serviços */
  constructor(private funcionarioService: FuncionarioService,
              private http: HttpClient,
              private messagemService: MensagemService
              ) {}

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
      salario: new FormControl('', [Validators.required]),
      cargo: new FormControl('', [Validators.required]),
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
  get salario() {
    return this.userForm.get('salario')!;
  }
  get cargo() {
    return this.userForm.get('cargo')!;
  }

  /* Submissão do formulário */
  submit() {
    if (this.userForm.invalid) { // Se for inválido invalida a submissão
      return this.messagemService.add('Formulário Inválido, verifique se os dados estão corretos!'); ;
    }

    if (this.userForm.value.senha != this.userForm.value.confirmacaoSenha) { // Se for inválido invalida a submissão
      return this.messagemService.add('As senhas devem ser iguais!'); ;
    }

    /* Criando um FormData com o formulário completo válidado*/
    if(this.userForm.value.senha == this.userForm.value.confirmacaoSenha){
      if (this.userForm.valid) {
        const formData = {
          nome: this.userForm.value.nome,
          cpf: this.userForm.value.cpf,
          dataNascimento: this.userForm.value.dataNascimento,
          telefone: this.userForm.value.telefone,
          email: this.userForm.value.email,
          senha: this.userForm.value.senha,
          cargo: this.userForm.value.cargo,
          salario: this.userForm.value.salario,
        };
  
        // console.log(this.userForm.value);
        this.onSubmit.emit(this.userForm.value);
      }
    }
    
  }
}
