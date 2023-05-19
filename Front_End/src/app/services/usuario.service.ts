import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Cliente } from '../components/interfaces/Cliente';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  /* private baseApiUrl = environment.baseApiUrl; */ 
  private baseApiUrl = 'http://localhost:8080/api/petshop';
  private apiUrl = `${this.baseApiUrl}/cliente`;

  constructor(private http: HttpClient) { }

  /* Criar Cliente no sistema */
  createCliente(cliente: any): Observable<any>{
    return this.http.post<any>(this.apiUrl, cliente);
  }

}
