import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import { AgendamentoService } from 'src/app/services/agendamento.service';
import { MensagemService } from 'src/app/services/mensagem.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.css']
})
export class AgendamentoComponent {

  /* FormGroup do formulario */
  agendamentoForm!: FormGroup;

  /* Construtor chamando os serviços */
  constructor(private agendamentoService: AgendamentoService, 
    private http: HttpClient, 
    private messagemService: MensagemService,
    private router: Router) {}

  /* Inicialização do formulário */
  ngOnInit(): void {
    this.agendamentoForm = new FormGroup({
      dataAgendamento: new FormControl('', [Validators.required]),
      horario: new FormControl('', [Validators.required]),
      animal: new FormControl('', [Validators.required]),

      banho: new FormControl(''),
      hidratacao: new FormControl(''),
      desembolo: new FormControl(''),
      tosaHigienica: new FormControl(''),
      tosaGeral: new FormControl(''),
      tosaBaixa: new FormControl(''),
      tosaAlta: new FormControl(''),
      tosaTesoura: new FormControl(''),
      unha: new FormControl(''),
      dentes: new FormControl(''),

      obs: new FormControl('', [Validators.required]),
    });
  }

  /* Submissão do formulário */ 
  submit() {
    if (this.agendamentoForm.invalid) { // Se for inválido invalida a submissão
      return this.messagemService.add('Formulário Inválido, verifique se os dados estão corretos!'); ;
    }

    /* Criando um FormData com o formulário completo válidado*/
      if (this.agendamentoForm.valid) {
        const formData = {
          dataAgendamento: this.agendamentoForm.value.dataAgendamento,
          horario: this.agendamentoForm.value.horario,
          animal: this.agendamentoForm.value.animal,

          banho: this.agendamentoForm.value.banho,
          hidratacao: this.agendamentoForm.value.hidratacao,
          desembolo: this.agendamentoForm.value.desembolo,
          tosaHigienica: this.agendamentoForm.value.tosaHigienica,
          tosaGeral: this.agendamentoForm.value.tosaGeral,
          tosaBaixa: this.agendamentoForm.value.tosaBaixa,
          tosaAlta: this.agendamentoForm.value.tosaAlta,
          tosaTesoura: this.agendamentoForm.value.tosaTesoura,
          unha: this.agendamentoForm.value.unha,
          dentes: this.agendamentoForm.value.dentes,

          obs: this.agendamentoForm.value.obs,
        };
  
        /* Mudando tipo de dado para JSON */
        const jsonData = JSON.stringify(this.agendamentoForm.value);

        console.log(jsonData);

        /* Enviando cliente para o Service */
        this.agendamentoService.createAgendamento(jsonData)
        .pipe(
          catchError((error) => {
            this.messagemService.add('Erro ao agendar: ' + error.error.message);
            throw error;
          })
        )
        .subscribe(() => {
          this.messagemService.add('Agendamento realizado com sucesso! ');
          this.router.navigate(['/home']);
        });
      }
    
  }

}
