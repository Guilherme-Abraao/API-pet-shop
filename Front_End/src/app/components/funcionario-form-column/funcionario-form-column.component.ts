import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Funcionario } from '../interfaces/Funcionario';
import { catchError } from 'rxjs';
import { MensagemService } from 'src/app/services/mensagem.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';

@Component({
  selector: 'app-funcionario-form-column',
  templateUrl: './funcionario-form-column.component.html',
  styleUrls: ['./funcionario-form-column.component.css']
})
export class FuncionarioFormColumnComponent implements OnChanges {

  @Input() btnText!: string;
  @Input() titulo!: string;
  @Input() funcionario!: Funcionario;

  isEditing = false;
  funcionarioForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private messagemService: MensagemService,
              private funcionarioService: FuncionarioService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['funcionario']) {
      this.preencherFormulario();
    }
  }

  preencherFormulario(): void {
    if (this.funcionario) {
      this.funcionarioForm = this.formBuilder.group({
        nome: [this.funcionario.nome],
        cpf: [this.funcionario.cpf],
        dataNascimento: [this.funcionario.dataNascimento],
        telefone: [this.funcionario.telefone],
        email: [this.funcionario.email],
        senha: [this.funcionario.senha]
      });
    }
  }

  salvarEdicao(): void {
    if (this.funcionarioForm && this.funcionarioForm.valid) {
      const novosValores = {
        nome: this.funcionarioForm.get('nome')?.value,
        cpf: this.funcionarioForm.get('cpf')?.value,
        dataNascimento: this.funcionarioForm.get('dataNascimento')?.value,
        telefone: this.funcionarioForm.get('telefone')?.value,
        email: this.funcionarioForm.get('email')?.value,
        senha: this.funcionarioForm.get('senha')?.value
      };

      this.funcionario.nome = novosValores.nome;
      this.funcionario.dataNascimento = novosValores.dataNascimento;
      this.funcionario.telefone = novosValores.telefone;
      this.funcionario.email = novosValores.email;
      this.funcionario.senha = novosValores.senha;

      this.funcionarioService.updateFuncionario(this.funcionario)
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
