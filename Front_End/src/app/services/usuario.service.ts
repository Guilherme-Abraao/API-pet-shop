import { Cliente } from 'src/app/components/interfaces/Cliente';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../components/interfaces/MensagemSistema';

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
    const url = `${this.apiUrl}/cadastrarCliente`;
    const data = {
      nome: cliente.nome,
      cpf: cliente.cpf,
      dataNascimento: cliente.dataNascimento,
      telefone: cliente.telefone,
      email: cliente.email,
      senha: cliente.senha,
    };
    const result = this.http.post(url, data);
    return result;
  }

  /* Pegar um Cliente no sistema pelo ID */
  getCliente(id: number): Observable<Response<Cliente>> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Response<Cliente>>(url);
  }

  /* Pegar um Cliente no sistema pelo email e senha */
  getClienteLogin(email: string, senha: string): Observable<Response<Cliente>> {
    const url = `${this.apiUrl}/${email}/${senha}`;
    return this.http.get<Response<Cliente>>(url);
  }
  
  updateCliente(cliente: any): Observable<any>{

    const url = `${this.apiUrl}/${cliente.id}`;

    const data = {
      nome: cliente.nome,
      cpf: cliente.cpf,
      dataNascimento: cliente.dataNascimento,
      telefone: cliente.telefone,
      email: cliente.email,
      senha: cliente.senha,
    };
    const result = this.http.put(url, data);
    return result;
  }

  /* Deletar um animal de cliente */
  deleteAnimal(cliente: Cliente, idAnimal: number): Observable<any>{
    const url = `${this.apiUrl}/${cliente.id}/${idAnimal}`;
    const result =  this.http.delete<Response<Cliente>>(url);
    return result;
  }

  /* Armazenar o ID do usuário para Navegar com os dados dele*/
  private userId: number=0;

  setUserId(id: number) {
    this.userId = id;
  }

  getUserId() {
    return this.userId;
  }



}
