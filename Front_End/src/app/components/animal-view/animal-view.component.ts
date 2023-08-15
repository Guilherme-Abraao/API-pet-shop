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
    this.usuarioService.deleteAnimal(this.cliente, idAnimal).subscribe(
      (response) => {
        // Aqui você pode lidar com a resposta da API, se necessário
        console.log('Animal excluído com sucesso:', response);
      },
      (error) => {
        // Lida com erros da chamada à API
        console.error('Erro ao excluir animal:', error);
      }
    );
  }
}
