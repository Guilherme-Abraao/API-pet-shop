import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FuncionarioFormUpdateComponent } from './funcionario-form-update.component';

describe('FuncionarioFormUpdateComponent', () => {
  let component: FuncionarioFormUpdateComponent;
  let fixture: ComponentFixture<FuncionarioFormUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FuncionarioFormUpdateComponent]
    });
    fixture = TestBed.createComponent(FuncionarioFormUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
