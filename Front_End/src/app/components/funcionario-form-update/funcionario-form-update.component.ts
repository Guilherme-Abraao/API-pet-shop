import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Funcionario } from '../interfaces/Funcionario';
import { FormControl, FormGroup } from '@angular/forms';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { MensagemService } from 'src/app/services/mensagem.service';

@Component({
  selector: 'app-funcionario-form-update',
  templateUrl: './funcionario-form-update.component.html',
  styleUrls: ['./funcionario-form-update.component.css']
})
export class FuncionarioFormUpdateComponent implements OnInit {
/* Enviar para o componente pai - Update-Funcionario */
@Output() onSubmit = new EventEmitter<Funcionario>();
 
funcForm!: FormGroup;

constructor(private funcionarioService: FuncionarioService,
   private messagemService: MensagemService)
   {}

   /* os campos não devem ser obrigatórios enquanto o valor dos
   campos do formulário automaticamente não forem os dados do funcionario atual */
ngOnInit(): void {
  this.funcForm = new FormGroup({
    nome: new FormControl('', ),
    cpf: new FormControl('', ),
    dataNascimento: new FormControl('', ),
    telefone: new FormControl('', ),
    email: new FormControl('', ),
    senha: new FormControl('', ),
  });
}
 /* GETs dos itens do formulário */
 get nome() {
  return this.funcForm.get('nome')!;
}
get cpf() {
  return this.funcForm.get('cpf')!;
}
get dataNascimento() {
  return this.funcForm.get('dataNascimento')!;
}
get telefone() {
  return this.funcForm.get('telefone')!;
}
get email() {
  return this.funcForm.get('email')!;
}
get senha() {
  return this.funcForm.get('senha')!;
}

/* Submissão do formulário */
submit() {
 if (this.funcForm.invalid) { // Se for inválido invalida a submissão
   return this.messagemService.add('Formulário Inválido, verifique se os dados estão corretos!'); ;
 }

 /* Criando um FormData com o formulário completo válidado*/
   if (this.funcForm.valid) {
     const formData = {
       nome: this.funcForm.value.nome,
       cpf: this.funcForm.value.cpf,
       dataNascimento: this.funcForm.value.dataNascimento,
       telefone: this.funcForm.value.telefone,
       email: this.funcForm.value.email,
       senha: this.funcForm.value.senha,
     };

     // console.log(this.userForm.value);
     this.onSubmit.emit(this.funcForm.value);
   }
}

}
