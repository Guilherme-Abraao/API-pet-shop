import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../components/interfaces/MensagemSistema';
import { Funcionario } from '../components/interfaces/Funcionario';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {

  /* private baseApiUrl = environment.baseApiUrl; */

  /* Base da API*/
  private baseApiUrl = 'http://localhost:8080/api/petshop';

  /* Especificando */
  private apiUrl = `${this.baseApiUrl}/funcionario`;

  constructor(private http: HttpClient) { }

  /* Criar Funcionario no sistema */
  createFuncionario(funcionario: any): Observable<any>{
    const data = {
      nome: funcionario.nome,
      cpf: funcionario.cpf,
      dataNascimento: funcionario.dataNascimento,
      telefone: funcionario.telefone,
      email: funcionario.email,
      senha: funcionario.senha,
      role: funcionario.role,
      salario: funcionario.salario,
    };
    const result = this.http.post(this.apiUrl, data);
    return result;
  }

  /* Pegar um Funcionario no sistema pelo ID */
  getFuncionario(id: number): Observable<Response<Funcionario>> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Response<Funcionario>>(url);
  }

}
