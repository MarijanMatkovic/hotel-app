import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { RoomEditService } from './room-edit.service';

describe('RoomEditService', () => {
  let service: RoomEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(RoomEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
