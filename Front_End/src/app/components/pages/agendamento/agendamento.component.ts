import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import { AgendamentoService } from 'src/app/services/agendamento.service';
import { MensagemService } from 'src/app/services/mensagem.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Cliente } from '../../interfaces/Cliente';

@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.css']
})
export class AgendamentoComponent {

  /* FormGroup do formulario */
  agendamentoForm!: FormGroup;

  /* Um cliente do tipo cliente para pegar os dados do Login, json para auxiliar */
  cliente?: Cliente;
  jsonData: any;
  Animais:string[]=[''];

  /* Construtor chamando os serviços */
  constructor(private agendamentoService: AgendamentoService,
    private http: HttpClient,
    private messagemService: MensagemService,
    private router: Router,
    private usuarioService: UsuarioService
    ) {}

  /* Inicialização do formulário */
  ngOnInit(): void {
    this.agendamentoForm = new FormGroup({

      dataHoraStart: new FormControl('', [Validators.required]),
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

      observacoes: new FormControl('', [Validators.required]),
    });

    this.agendamentoForm.patchValue({
      dataHoraStart: new Date().toISOString(),
    });

    /* Pegar dados de quem fez login no sistema e armazenando em cliente*/
    const id = this.usuarioService.getUserId()
    this.usuarioService.getCliente(id).subscribe((item) => {

      this.jsonData = item;
      this.cliente = this.jsonData;
    });
  }

  /* Submissão do formulário */
  submit() {
    if (this.agendamentoForm.invalid) { // Se for inválido invalida a submissão
      return this.messagemService.add('Formulário Inválido, verifique se os dados estão corretos!'); ;
    }

    /* Criando um FormData com o formulário completo válidado*/
      if (this.agendamentoForm.valid) {

        const servicosSelecionados = [];
        
        const dataAgendamento = this.agendamentoForm.value.dataHoraStart.split('T')[0];
        const horaAgendamento = this.agendamentoForm.value.horario;
        const dataHoraStart = `${dataAgendamento}T${horaAgendamento}:00`;

        if (this.agendamentoForm.value.banho) servicosSelecionados.push('banho');
        if (this.agendamentoForm.value.hidratacao) servicosSelecionados.push('hidratacao');
        if (this.agendamentoForm.value.desembolo) servicosSelecionados.push('desembolo');
        if (this.agendamentoForm.value.tosaHigienica) servicosSelecionados.push('tosaHigienica');
        if (this.agendamentoForm.value.tosaGeral) servicosSelecionados.push('tosaGeral');
        if (this.agendamentoForm.value.tosaBaixa) servicosSelecionados.push('tosaBaixa');
        if (this.agendamentoForm.value.tosaAlta) servicosSelecionados.push('tosaAlta');
        if (this.agendamentoForm.value.tosaTesoura) servicosSelecionados.push('tosaTesoura');
        if (this.agendamentoForm.value.unha) servicosSelecionados.push('unha');
        if (this.agendamentoForm.value.dentes) servicosSelecionados.push('dentes');

        const formData = {
          dataHoraStart: dataHoraStart,
          animal: this.agendamentoForm.value.animal,
          servicos: servicosSelecionados,
          observacoes: this.agendamentoForm.value.observacoes,
        };
  
        // /* Mudando tipo de dado para JSON */
        const jsonData = JSON.stringify(formData);

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
