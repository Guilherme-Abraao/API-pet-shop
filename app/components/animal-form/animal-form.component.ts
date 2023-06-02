import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Animal } from '../interfaces/Animal';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { AnimalService } from 'src/app/services/animal.service';
import { MensagemService } from 'src/app/services/mensagem.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-animal-form',
  templateUrl: './animal-form.component.html',
  styleUrls: ['./animal-form.component.css']
})
export class AnimalFormComponent implements OnInit{

  /* Enviar para o componente pai - New-Animal */
  @Output() onSubmit = new EventEmitter<Animal>();

  /* Receber do componente pai - New-Animal */
  @Input() btnText!: string;
  @Input() titulo!: string;

  /* FormGroup do formulario e fatimes é um ícone*/
  userForm!: FormGroup;
  faTimes = faTimes;

  /* Construtor chamando os serviços */
  constructor(private usuarioService: AnimalService,
              private http: HttpClient,
              private messagemService: MensagemService,) {}

  /* Inicialização do formulário */
  ngOnInit(): void {
    this.userForm = new FormGroup({
      nome: new FormControl('', [Validators.required]),
      dataNascimento: new FormControl('', [Validators.required]),
      raca: new FormControl('', [Validators.required]),
      especie: new FormControl('', [Validators.required]),
    });
  }

  /* GETs dos itens do formulário */
  get nome() {
    return this.userForm.get('nome')!;
  }
  get dataNascimento() {
    return this.userForm.get('dataNascimento')!;
  }
  get especie() {
    return this.userForm.get('especie')!;
  }
  get raca() {
    return this.userForm.get('raca')!;
  }

  /* Submissão do formulário */
  submit() {
    if (this.userForm.invalid) { // Se for inválido invalida a submissão
      return this.messagemService.add('Formulário Inválido, verifique se os dados estão corretos!'); ;
    }

    /* Criando um FormData com o formulário completo válidado*/
    if(this.userForm.value.senha == this.userForm.value.confirmacaoSenha){
      if (this.userForm.valid) {
        const formData = {
          nome: this.userForm.value.nome,
          dataNascimento: this.userForm.value.dataNascimento,
          raca: this.userForm.value.raca,
          especie: this.userForm.value.especie,
        };
  
        // console.log(this.userForm.value);
        this.onSubmit.emit(this.userForm.value);
      }
    }
    
  }
}
