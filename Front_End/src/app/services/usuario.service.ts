import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  /* private baseApiUrl = environment.baseApiUrl; */ 
  private baseApiUrl = 'http://localhost:8080/api/petshop';
  private apiUrl = `${this.baseApiUrl}api/usuario`;

  constructor(private http: HttpClient) { }

  createCliente(formData: FormData): Observable<FormData> {
    return this.http.post<FormData>(this.apiUrl, formData);
  }

}
