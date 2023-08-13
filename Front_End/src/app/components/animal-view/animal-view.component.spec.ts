import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalViewComponent } from './animal-view.component';

describe('AnimalViewComponent', () => {
  let component: AnimalViewComponent;
  let fixture: ComponentFixture<AnimalViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnimalViewComponent]
    });
    fixture = TestBed.createComponent(AnimalViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
