import { Component } from '@angular/core';
import { EventSettingsModel, DayService, WeekService, WorkWeekService, MonthService, AgendaService, ResizeService, DragAndDropService, WorkHoursModel } from '@syncfusion/ej2-angular-schedule';
import { DataManager, ODataV4Adaptor, Query } from '@syncfusion/ej2-data';

@Component({
  selector: 'app-calendario',
  providers: [DayService, WeekService, WorkWeekService, MonthService, AgendaService, ResizeService, DragAndDropService],
  //template de calendário
  template: `<ejs-schedule width='100%' height='550px' [timeFormat]="timeFormat"[eventSettings]="eventSettings" [dateFormat]="dateFormat" startHour='08:00' endHour='18:00'> </ejs-schedule>`,

})
export class CalendarioComponent {

  title = 'tabela-agendamentos';
  //formatos de data e horário do calendário
  public dateFormat: string = "dd/MM/yyyy";
  public timeFormat: string = "HH:mm";
  public timeScaleModel = {enable: true, interval: 30, slotCount: 2};

   // Adaptador OData
  private dataManager: DataManager = new DataManager({
    url: 'http://localhost:8080/api/petshop/agendamentos',
    adaptor: new ODataV4Adaptor,
    crossDomain: true
 });

  public eventSettings: EventSettingsModel = {
    dataSource: this.dataManager,
    fields: {
      //renomeando os atributos default de um evento
      startTime: {title: 'Horário marcado'},
      endTime: {title: 'Previsão de término'},
      description: {name:'observacoes', title: 'Observações'}

    }
  };

}
