import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { NewAdminService } from './new-admin.service';

describe('NewAdminService', () => {
  let service: NewAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(NewAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
