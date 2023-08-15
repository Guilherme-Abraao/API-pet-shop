import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioService } from './usuario.service';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {

  /* Base da API*/
  private baseApiUrl = 'http://localhost:8080/api/petshop';

  /* Especificando */
  private apiUrl = `${this.baseApiUrl}/agendamentos`;

  constructor(private http: HttpClient) { }

  /* Criar Agendamento no sistema */
  createAgendamento(agendamento: any): Observable<any>{

    // Para resolver a inconsistência entre as definições do back e front, criei uma lista de serviços a ser preenchida
    const servicos = [];
  if (agendamento.banho == true) servicos.push('Banho');
  if (agendamento.hidratacao == true) servicos.push('Hidratação');
  if (agendamento.desembolo == true) servicos.push('Desembolo');
  if (agendamento.tosaHigienica == true) servicos.push('Tosa Higiênica');
  if (agendamento.tosaGeral == true) servicos.push('Tosa Geral');
  if (agendamento.tosaAlta == true) servicos.push('Tosa Alta');
  if (agendamento.tosaBaixa == true) servicos.push('Tosa Baixa');
  if (agendamento.tosaTesoura == true) servicos.push('Tosa Tesoura');
  if (agendamento.unha == true) servicos.push('Unhas');
  if (agendamento.dentes == true) servicos.push('Dentes');

    const data = {
      
      dataHoraStart: agendamento.dataHoraStart,
      horario: agendamento.horario,
      animal: agendamento.animal,
      banho: agendamento.banho,
      servicos: servicos, // Enviando a lista de serviços criada acima
      observacoes: agendamento.observacoes,
    };
    console.log(agendamento);
    const result = this.http.post(this.apiUrl, data);
    return result;
  }

  
}
