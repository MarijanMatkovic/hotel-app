import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { GuestListService } from './guest-list.service';

describe('GuestListService', () => {
  let service: GuestListService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(GuestListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
