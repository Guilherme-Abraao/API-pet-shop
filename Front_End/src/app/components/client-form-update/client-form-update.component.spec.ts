import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientFormUpdateComponent } from './client-form-update.component';

describe('ClientFormUpdateComponent', () => {
  let component: ClientFormUpdateComponent;
  let fixture: ComponentFixture<ClientFormUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientFormUpdateComponent]
    });
    fixture = TestBed.createComponent(ClientFormUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
