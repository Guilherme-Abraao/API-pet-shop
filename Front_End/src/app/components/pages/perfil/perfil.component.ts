import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit{

  cliente!: Cliente;
  btnText: string = 'Editar';
  titulo: string = 'Meu Perfil';

  constructor(private usuarioService: UsuarioService, private route: ActivatedRoute){}
  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    /* Passa o ID no lugar do 1 */
    this.usuarioService.getCliente(1).subscribe((item) => {
      this.cliente = item.data;
    });
  }

}
