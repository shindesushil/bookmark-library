import { TestBed } from '@angular/core/testing';

import { WindowrefService } from './windowref.service';

describe('WindowrefService', () => {
  let service: WindowrefService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WindowrefService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
