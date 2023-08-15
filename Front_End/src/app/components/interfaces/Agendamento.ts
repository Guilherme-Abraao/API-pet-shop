import { Servico } from "./Servico";

export interface Agendamento {
    id?: number;
    dataHoraStart: string;
    horario: string;
    animal: string;
    //idAnimal: number; Para passar id do animal ao criar agendamento?
    servicos: Servico[];
    banho: boolean;
    hidratacao: boolean;
    desembolo: boolean;
    tosaHigienica: boolean;
    tosaGeral: boolean;
    tosaBaixa: boolean;
    tosaAlta: boolean;
    tosaTesoura: boolean;
    unha: boolean;
    dentes: boolean;
    observacoes: string;
}

