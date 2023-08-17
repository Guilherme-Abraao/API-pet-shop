import { Component, OnInit, Output } from '@angular/core';
import { Cliente } from '../../interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ActivatedRoute} from '@angular/router';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { Funcionario } from '../../interfaces/Funcionario';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit{
  /* Passar o cliente2 e o funcionario2 para os componentes client-form-column e funcionario-form-column */
  @Output() cliente2!: Cliente;
  @Output() funcionario2!: Funcionario;

  cliente!: Cliente;
  funcionario!: Funcionario;
  btnText: string = 'Editar';
  titulo: string = 'Meu Perfil';
  jsonData: any;

  constructor(private usuarioService: UsuarioService, private route: ActivatedRoute, private funcionarioService: FuncionarioService){}

  ngOnInit(): void {
    /* const id = Number(this.route.snapshot.paramMap.get('id')); */
    /* Requisição GET para buscar os dados do Usuario e mostrar no Perfil dele*/
    const UserId = this.usuarioService.getUserId();
    
    if(UserId != 0) {
    this.usuarioService.getCliente(UserId).subscribe((item) => {

      this.jsonData = item;
      this.cliente = this.jsonData;
    });

    this.cliente2 = this.cliente;
  }
  else {
      const FuncId = this.funcionarioService.getFuncionarioId();
      
      this.funcionarioService.getFuncionario(FuncId).subscribe((item)=> {
      this.jsonData = item;
      this.funcionario = this.jsonData
    });
    this.funcionario2 = this.funcionario;
  }

}

}
