import { Servico } from "./Servico";

export interface Agendamento {
    id?: number;
    dataHoraStart: string;
    horario: string;
    animalId: number;
    clienteId: number;
    servicos: Servico[];
    observacoes: string;
    funcionarioId: number;
}

