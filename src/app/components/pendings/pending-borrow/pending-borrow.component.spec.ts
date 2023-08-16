import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingBorrowComponent } from './pending-borrow.component';

describe('PendingBorrowComponent', () => {
  let component: PendingBorrowComponent;
  let fixture: ComponentFixture<PendingBorrowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PendingBorrowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingBorrowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
