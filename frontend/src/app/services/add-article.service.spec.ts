import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { AddArticleService } from './add-article.service';

describe('AddArticleService', () => {
  let service: AddArticleService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(AddArticleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
