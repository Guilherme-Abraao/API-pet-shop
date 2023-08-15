import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizarAgendamentoComponent } from './visualizar-agendamento.component';

describe('VisualizarAgendamentoComponent', () => {
  let component: VisualizarAgendamentoComponent;
  let fixture: ComponentFixture<VisualizarAgendamentoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VisualizarAgendamentoComponent]
    });
    fixture = TestBed.createComponent(VisualizarAgendamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
