import { Component } from '@angular/core';
import { EventSettingsModel, DayService, WeekService, WorkWeekService, MonthService, AgendaService, ResizeService, DragAndDropService } from '@syncfusion/ej2-angular-schedule';

@Component({
  selector: 'app-calendario',
  providers: [DayService, WeekService, WorkWeekService, MonthService, AgendaService, ResizeService, DragAndDropService],
  //template de calendário
  template: `<ejs-schedule width='100%' height='550px' [timeFormat]="timeFormat"
  [eventSettings]="eventSettings" [dateFormat]="dateFormat" > </ejs-schedule>`,

})
export class CalendarioComponent {

  title = 'tabela-agendamentos';
  //formatos de data e horário do calendário
  public dateFormat: string = "dd/MM/yyyy";
  public timeFormat: string = "HH:mm";

  
  //cria um array de objetos que será lido pelo EventSettings para gerar os Appointments, como nos exemplos abaixo
  public data: object[] = [
    {
      //nome do animal como título (subject)
      Subject: 'Peter',
      //new Date(Ano, Mês (0 ao 11), Dia, Horas, Minutos)
      StartTime: new Date(2023,5,20,7,30),
      EndTime: new Date(2023,5,20,8,30),
      Serviço: 'Banho e Hidratação',
      Raça: 'Corgi',
      Funcionário: 'Felipe',
      Observações: 'Serviços: Banho e Hidratação \n Raça: Corgi \n Funcionário: Felipe \n Objetos deixados: nenhum\n ',
      
    },
    {
      Subject: 'Thom',
      StartTime: new Date(2023,5,21,14,0),
      EndTime: new Date(2023,5,21,15,30),
      Serviço: 'Banho e Tosa na Tesoura',
      Raça: 'Zwergspitz',
      Funcionário: 'Patrick',
      Observações: 'Serviços: Banho e Tosa na Tesoura \n Raça: Zwergspitz \n Funcionário: Patrick \n Objetos deixados: coleira\n ',
      
    },
    {
      Subject: 'Cacau',
      StartTime: new Date(2023,5,20,16,0),
      EndTime: new Date(2023,5,20,18,0),
      Serviço: 'Banho, Hidratação, Unha e Tosa Geral',
      Raça: 'Persa',
      Funcionário: 'James',
      Observações: 'Serviços: Banho, Hidratação, Unha e Tosa Geral \n Raça: Persa \n Funcionário: James \n Objetos deixados: coleira e shampoo \n ',
    }

  ];

  public eventSettings: EventSettingsModel = {
    dataSource: this.data,
    fields: {
      //renomeando os atributos default de um evento
      startTime: {title: 'Horário marcado'},
      endTime: {title: 'Previsão de término'},
      description: {name:'Observações', title: 'Observações'}

    }
  };

    /* POSSÍVEL IMPLEMENTAÇÃO DE REMOTE DATA BINDING
    
    private data: DataManager = new DataManager ({
    url:'http://localhost:8080/api/petshop',
    adaptor: new JsonAdaptor,
    crossDomain: true
  })
  */
}
