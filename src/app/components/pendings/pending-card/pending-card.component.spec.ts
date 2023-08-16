import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingCardComponent } from './pending-card.component';

describe('PendingCardComponent', () => {
  let component: PendingCardComponent;
  let fixture: ComponentFixture<PendingCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PendingCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
