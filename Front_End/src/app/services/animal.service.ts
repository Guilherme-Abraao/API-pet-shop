import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../components/interfaces/MensagemSistema';
import { environment } from 'src/environments/environment';
import { Animal } from '../components/interfaces/Animal';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  /* private baseApiUrl = environment.baseApiUrl; */

  /* Base da API*/
  private baseApiUrl = 'http://localhost:8080/api/petshop';

  /* Especificando */
  private apiUrl = `${this.baseApiUrl}/animal`;

  constructor(private http: HttpClient) { }

  /* Criar Animal no sistema */
  createAnimal(animal: any): Observable<any>{
    const data = {
      nome: animal.nome,
      dataNascimento: animal.dataNascimento,
      especie: animal.especie,
      raca: animal.raca,
    };
    const result = this.http.post(this.apiUrl, data);
    return result;
  }

  /* Pegar um Animal no sistema pelo ID */
  getAnimal(id: number): Observable<Response<Animal>> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Response<Animal>>(url);
  }

}
