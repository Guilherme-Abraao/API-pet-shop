import { Component, Input } from '@angular/core';
import { Cliente } from '../interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-animal-view',
  templateUrl: './animal-view.component.html',
  styleUrls: ['./animal-view.component.css']
})
export class AnimalViewComponent {

  @Input() cliente!: Cliente;

  constructor(private usuarioService: UsuarioService){ }

  ngOnInit(): void {
    
  }

  excluirAnimal(idAnimal: number){
    console.log("teste");
    console.log(idAnimal);
    this.usuarioService.deleteAnimal(this.cliente, idAnimal); 
  }
}
