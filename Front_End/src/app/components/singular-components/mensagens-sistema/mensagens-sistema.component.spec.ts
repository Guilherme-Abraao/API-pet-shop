import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MensagensSistemaComponent } from './mensagens-sistema.component';

describe('MensagensSistemaComponent', () => {
  let component: MensagensSistemaComponent;
  let fixture: ComponentFixture<MensagensSistemaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MensagensSistemaComponent]
    });
    fixture = TestBed.createComponent(MensagensSistemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
