import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { RemoveReservationService } from './remove-reservation.service';

describe('RemoveReservationService', () => {
  let service: RemoveReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(RemoveReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
