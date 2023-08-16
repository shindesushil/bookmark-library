import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingReturnComponent } from './pending-return.component';

describe('PendingReturnComponent', () => {
  let component: PendingReturnComponent;
  let fixture: ComponentFixture<PendingReturnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PendingReturnComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingReturnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
