import { TestBed } from '@angular/core/testing';
import { CreateWorkerService } from './create-worker.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('CreateWorkerService', () => {
  let service: CreateWorkerService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(CreateWorkerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
