import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { ReservationsListService } from './reservations-list.service';

describe('ReservationsListService', () => {
  let service: ReservationsListService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(ReservationsListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
