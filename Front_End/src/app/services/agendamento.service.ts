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

    const data = {
      
      dataHoraStart: agendamento.dataHoraStart,
      animalId: agendamento.animal,
      banho: agendamento.banho,
      servicos: agendamento.servicos, // Enviando a lista de serviços criada acima
      observacoes: agendamento.observacoes,
    };

    console.log(data);
    const result = this.http.post(this.apiUrl, data);
    return result;
  }

  
}
