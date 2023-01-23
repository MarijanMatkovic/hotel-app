import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { RemoveWorkerService } from './remove-worker.service';

describe('RemoveWorkerService', () => {
  let service: RemoveWorkerService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(RemoveWorkerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
