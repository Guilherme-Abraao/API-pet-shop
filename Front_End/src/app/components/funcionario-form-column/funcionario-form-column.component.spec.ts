import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FuncionarioFormColumnComponent } from './funcionario-form-column.component';

describe('FuncionarioFormColumnComponent', () => {
  let component: FuncionarioFormColumnComponent;
  let fixture: ComponentFixture<FuncionarioFormColumnComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FuncionarioFormColumnComponent]
    });
    fixture = TestBed.createComponent(FuncionarioFormColumnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
