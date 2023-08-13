import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Cliente } from '../interfaces/Cliente';
import { HttpClient } from '@angular/common/http';
import { MensagemService } from 'src/app/services/mensagem.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-client-form-update',
  templateUrl: './client-form-update.component.html',
  styleUrls: ['./client-form-update.component.css']
})
export class ClientFormUpdateComponent implements OnInit {
 /* Enviar para o componente pai - Update-Client */
 @Output() onSubmit = new EventEmitter<Cliente>();
 
 userForm!: FormGroup;

 constructor(private usuarioService: UsuarioService,
    private messagemService: MensagemService)
    {}

    /* os campos não devem ser obrigatórios enquanto o valor dos
    campos do formulário automaticamente não forem os dados do cliente atual */
 ngOnInit(): void {
   this.userForm = new FormGroup({
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

 /* Submissão do formulário */
 submit() {
  if (this.userForm.invalid) { // Se for inválido invalida a submissão
    return this.messagemService.add('Formulário Inválido, verifique se os dados estão corretos!'); ;
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
      };

      // console.log(this.userForm.value);
      this.onSubmit.emit(this.userForm.value);
    }
}

}
