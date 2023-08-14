import { UsuarioService } from './../../services/usuario.service';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Cliente } from '../interfaces/Cliente';
import { Funcionario } from '../interfaces/Funcionario';
import { catchError } from 'rxjs';
import { MensagemService } from 'src/app/services/mensagem.service';

@Component({
  selector: 'app-client-form-column',
  templateUrl: './client-form-column.component.html',
  styleUrls: ['./client-form-column.component.css']
})
export class ClientFormColumnComponent implements OnChanges {

  @Input() btnText!: string;
  @Input() titulo!: string;
  @Input() cliente!: Cliente;
  @Input() funcionario!: Funcionario;

  isEditing = false;
  clienteForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private messagemService: MensagemService,
              private usuarioService: UsuarioService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['cliente']) {
      this.preencherFormulario();
    }
  }

  preencherFormulario(): void {
    if (this.cliente) {
      this.clienteForm = this.formBuilder.group({
        nome: [this.cliente.nome],
        cpf: [this.cliente.cpf],
        dataNascimento: [this.cliente.dataNascimento],
        telefone: [this.cliente.telefone],
        email: [this.cliente.email],
        senha: [this.cliente.senha]
      });
    }
  }

  salvarEdicao(): void {
    if (this.clienteForm && this.clienteForm.valid) {
      const novosValores = {
        nome: this.clienteForm.get('nome')?.value,
        cpf: this.clienteForm.get('cpf')?.value,
        dataNascimento: this.clienteForm.get('dataNascimento')?.value,
        telefone: this.clienteForm.get('telefone')?.value,
        email: this.clienteForm.get('email')?.value,
        senha: this.clienteForm.get('senha')?.value
      };

      this.cliente.nome = novosValores.nome; 
      this.cliente.dataNascimento = novosValores.dataNascimento; 
      this.cliente.telefone = novosValores.telefone; 
      this.cliente.email = novosValores.email; 
      this.cliente.senha = novosValores.senha;

      this.usuarioService.updateCliente(this.cliente)
      .pipe(
        catchError((error) => {
          this.messagemService.add('Erro ao atualizar o cadastro: ' + error.error.message);
          throw error;
        })
      )
      .subscribe(() => {
        this.messagemService.add('Cadastro atualizado com sucesso!');
      });

      this.isEditing = !this.isEditing; // Voltar para o modo de edição
    }
  }
}
