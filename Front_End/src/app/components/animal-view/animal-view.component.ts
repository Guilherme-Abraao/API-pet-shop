import { Component, Input } from '@angular/core';
import { Cliente } from '../interfaces/Cliente';

@Component({
  selector: 'app-animal-view',
  templateUrl: './animal-view.component.html',
  styleUrls: ['./animal-view.component.css']
})
export class AnimalViewComponent {

  @Input() cliente!: Cliente;

  constructor(){ }

  ngOnInit(): void {
    
  }
}
