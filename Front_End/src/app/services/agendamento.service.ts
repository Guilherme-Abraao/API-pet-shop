import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {

  /* Base da API*/
  private baseApiUrl = 'http://localhost:8080/api/petshop';

  /* Especificando */
  private apiUrl = `${this.baseApiUrl}/agendamento`;

  constructor(private http: HttpClient) { }

  /* Criar Agendamento no sistema */
  createAgendamento(agendamento: any): Observable<any>{
    const data = {
      dataAgendamento: agendamento.dataAgendamento, 
      horario: agendamento.horario, 
      animal: agendamento.animal, 
      banho: agendamento.banho, 
      hidratacao: agendamento.hidratacao, 
      desembolo: agendamento.desembolo, 
      tosaHigienica: agendamento.tosaHigienica, 
      tosaGeral: agendamento.tosaGeral, 
      tosaBaixa: agendamento.tosaBaixa, 
      tosaAlta: agendamento.tosaAlta, 
      tosaTesoura: agendamento.tosaTesoura, 
      unha: agendamento.unha, 
      dentes: agendamento.dentes,
      obs: agendamento.obs, 
    };
    const result = this.http.post(this.apiUrl, data);
    return result;
  }

  
}
