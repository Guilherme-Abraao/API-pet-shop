import { Component, Input } from '@angular/core';
import { Cliente } from '../interfaces/Cliente';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MensagemService } from 'src/app/services/mensagem.service';

@Component({
  selector: 'app-animal-view',
  templateUrl: './animal-view.component.html',
  styleUrls: ['./animal-view.component.css']
})
export class AnimalViewComponent {

  @Input() cliente!: Cliente;

  constructor(private usuarioService: UsuarioService,  private messagemService: MensagemService){ }

  ngOnInit(): void {
    
  }

  excluirAnimal(idAnimal: number){
    this.usuarioService.deleteAnimal(this.cliente, idAnimal).subscribe(
      (response) => {
        // Remova o animal excluído da lista
        this.cliente.animais = this.cliente.animais.filter(animal => animal.id !== idAnimal);
  
        this.messagemService.add('Animal excluído com sucesso. ');
      },
      (error) => {
        this.messagemService.add('Erro ao tentar excluir. ');
      }
    );
  }
}
