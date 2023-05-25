import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../components/interfaces/MensagemSistema';
import { Cliente } from '../components/interfaces/Cliente';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  /* private baseApiUrl = environment.baseApiUrl; */ 

  /* Base da API*/
  private baseApiUrl = 'http://localhost:8080/api/petshop';

  /* Especificando */
  private apiUrl = `${this.baseApiUrl}/cliente`;

  constructor(private http: HttpClient) { }

  /* Criar Cliente no sistema */
  createCliente(cliente: any): Observable<any>{
    const result = this.http.post(this.apiUrl, cliente, {headers: {'Content-type': 'aplication/json'}});
    return result;
  }

  /* Pegar um Cliente no sistema pelo ID */
  getCliente(id: number): Observable<Response<Cliente>> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Response<Cliente>>(url);
  }

}
