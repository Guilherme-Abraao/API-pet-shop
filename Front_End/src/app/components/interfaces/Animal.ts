import { Agendamento } from './Agendamento';
export interface Animal {
    id: number;
    idade: number; 
    nome: string;
    dataNascimento: string;
    especie: string;
    raca: string;
    agendamentos: Agendamento[];
}

