export interface Agendamento {
    id?: number;
    dataAgendamento: string;
    horario: string; 
    animal: string; 
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
    obs: boolean;
}

