import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { GetUnitsService } from './get-units.service';

describe('GetUnitsService', () => {
  let service: GetUnitsService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(GetUnitsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
