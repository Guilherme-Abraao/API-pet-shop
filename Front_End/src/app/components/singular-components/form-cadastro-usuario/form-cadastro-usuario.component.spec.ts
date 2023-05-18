import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCadastroUsuarioComponent } from './form-cadastro-usuario.component';

describe('FormCadastroUsuarioComponent', () => {
  let component: FormCadastroUsuarioComponent;
  let fixture: ComponentFixture<FormCadastroUsuarioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormCadastroUsuarioComponent]
    });
    fixture = TestBed.createComponent(FormCadastroUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
