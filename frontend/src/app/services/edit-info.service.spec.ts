import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { EditInfoService } from './edit-info.service';

describe('EditInfoService', () => {
  let service: EditInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(EditInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
