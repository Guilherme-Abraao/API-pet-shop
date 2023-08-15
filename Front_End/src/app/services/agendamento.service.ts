import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioService } from './usuario.service';
import { Agendamento } from '../components/interfaces/Agendamento';

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

    const listaAgendamentos: Agendamento [] = []; //Cria um array de agendamentos (necessário se o back exigir essa estrutura)
    const data = {
      
      dataHoraStart: agendamento.dataHoraStart,
      cliente_id: agendamento.cliente_id,
      //animal: agendamento.animal, // Caso seja necessário passar o nome do animal
      servicos: agendamento.servicos, // Enviando a lista de serviços criada no agendamento.component
      observacoes: agendamento.observacoes,
      animal_id: agendamento.animal_id,
    };

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    console.log(data);
    
    const jsonData = JSON.stringify(data);
    console.log(jsonData);

    listaAgendamentos.push(agendamento);
    console.log(listaAgendamentos);
    
    const result = this.http.post(this.apiUrl, listaAgendamentos, httpOptions);
    return result;
  }

  
}
