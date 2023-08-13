import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../components/interfaces/MensagemSistema';
import { environment } from 'src/environments/environment';
import { Animal } from '../components/interfaces/Animal';
import { Cliente } from '../components/interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ActivatedRoute} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  /* private baseApiUrl = environment.baseApiUrl; */
  /* Base da API*/
  private baseApiUrl = 'http://localhost:8080/api/petshop';
  
  /* Especificando */

  private apiUrl = "";

  //Falta passar o id do usuário na URL
  //private id = Number(this.route.snapshot.paramMap.get('id'));
  //private apiUrl = `${this.baseApiUrl}/animal/cadastrarAnimal/${this.id}`;

  constructor(private http: HttpClient, private usuarioService: UsuarioService, private route: ActivatedRoute) { }

  /* Criar Animal no sistema */
  createAnimal(animal: any, id: number): Observable<any>{
    const data = {
      nome: animal.nome,
      dataNascimento: animal.dataNascimento,
      especie: animal.especie,
      raca: animal.raca,
    };

    this.apiUrl = `${this.baseApiUrl}/animal/cadastrarAnimal/${id}`;

    const result = this.http.post(this.apiUrl, data);
    return result;
  }

  /* Pegar um Animal no sistema pelo ID */
  getAnimal(id: number): Observable<Response<Animal>> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Response<Animal>>(url);
  }

}
