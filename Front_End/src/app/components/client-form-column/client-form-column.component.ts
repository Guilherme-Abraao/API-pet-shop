import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Cliente } from '../interfaces/Cliente';
import { Funcionario } from '../interfaces/Funcionario';

@Component({
  selector: 'app-client-form-column',
  templateUrl: './client-form-column.component.html',
  styleUrls: ['./client-form-column.component.css']
})
export class ClientFormColumnComponent implements OnInit {

  @Input() btnText!: string;
  @Input() titulo!: string;
  @Input() cliente!: Cliente;
  @Input() funcionario!: Funcionario;

  isEditing = false;
  clienteForm!: FormGroup; // Reactive Form

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    console.log('Funcionário recebido no ClientFormColumnComponent:', this.funcionario);
  }
}
