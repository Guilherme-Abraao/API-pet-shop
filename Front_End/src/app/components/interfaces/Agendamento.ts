import { Servico } from "./Servico";

export interface Agendamento {
    id?: number;
    dataHoraStart: string;
    horario: string;
    animal_id: number;
    cliente_id: number;
    servicos: Servico[];
    observacoes: string;
}

