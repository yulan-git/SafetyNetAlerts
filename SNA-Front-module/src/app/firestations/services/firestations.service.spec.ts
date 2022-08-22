import { TestBed } from '@angular/core/testing';

import { FirestationsService } from './firestations.service';

describe('FirestationsService', () => {
  let service: FirestationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirestationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
