import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../components/interfaces/MensagemSistema';
import { Cliente } from '../components/interfaces/Cliente';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';

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
    const data = {
      nome: cliente.nome,
      cpf: cliente.cpf,
      dataNascimento: cliente.dataNascimento,
      telefone: cliente.telefone,
      email: cliente.email,
      senha: cliente.senha,
    };
    const result = this.http.post(this.apiUrl, data);
    return result;
  }

  /* Pegar um Cliente no sistema pelo ID */
  getCliente(id: number): Observable<Response<Cliente>> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Response<Cliente>>(url);
  }

  /* Pegar um Cliente no sistema pelo cpf e senha, levemente codificados em base64 */
  getClienteLogin(cpf: string, senha: string): Observable<Response<Cliente>> {
    var cpfCodificado = btoa(cpf);
    var senhaCodificada = btoa(senha);
    const url = `${this.apiUrl}/${cpfCodificado}-${senhaCodificada}`;
    return this.http.get<Response<Cliente>>(url);
  }
  
  updateCliente(cliente: any): Observable<any>{
    const data = {
      nome: cliente.nome,
      cpf: cliente.cpf,
      dataNascimento: cliente.dataNascimento,
      telefone: cliente.telefone,
      email: cliente.email,
      senha: cliente.senha,
    };
    const result = this.http.put(this.apiUrl, data);
    return result;
  }
}
