import { Component, OnInit, Output } from '@angular/core';
import { Cliente } from '../../interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit{
  /* Passar o cliente2 para o componente client-form-column */
  @Output() cliente2!: Cliente;

  cliente!: Cliente;
  btnText: string = 'Editar';
  titulo: string = 'Meu Perfil';
  jsonData: any;

  constructor(private usuarioService: UsuarioService, private route: ActivatedRoute){}
  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    /* Passa o ID no lugar do 3 */
    this.usuarioService.getCliente(3).subscribe((item) => {
      /* Precisa transformar em JSON para funcionar */
      this.jsonData = item;
      this.cliente = this.jsonData;
    });

    this.cliente2 = this.cliente;
    
  }
}
