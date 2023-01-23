import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { ProdAuthenticationService } from './prod-authentication.service';
import {MatSnackBarModule} from '@angular/material/snack-bar';

describe('ProdAuthenticationService', () => {
  let service: ProdAuthenticationService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule, MatSnackBarModule]});
    service = TestBed.inject(ProdAuthenticationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
