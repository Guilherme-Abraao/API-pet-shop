import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientFormColumnComponent } from './client-form-column.component';

describe('ClientFormColumnComponent', () => {
  let component: ClientFormColumnComponent;
  let fixture: ComponentFixture<ClientFormColumnComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientFormColumnComponent]
    });
    fixture = TestBed.createComponent(ClientFormColumnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
