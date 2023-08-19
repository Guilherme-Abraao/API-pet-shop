import { Servico } from "./Servico";

export interface Agendamento {
    id: number;
    dataHoraStart: string;
    dataHoraEnd: string;
    horario: string;
    clienteId: number;
    servicos: Servico[];
    observacoes: string;
    funcionarioId: number;
    funcionarioNome: string;
}

