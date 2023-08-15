import { Servico } from "./Servico";

export interface Agendamento {
    id?: number;
    dataHoraStart: string;
    horario: string;
    idAnimal: number;
    idCliente: number;
    servicos: Servico[];
    observacoes: string;
}

