import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { HttpHeaders } from '@angular/common/http';
import * as Store from 'store2';
import { article } from '../models/article';

@Injectable({
  providedIn: 'root'
})
export class AddArticleService {
  private readonly LOGIN_URL = `${environment.baseApiUrl}/housekeeper/housekeeper`
  constructor(private http: HttpClient) {}

  getData() {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
    return this.http.get<article>(this.LOGIN_URL, {
        headers:head
    })
  }

  sendData(data:any) {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
    return this.http.post(this.LOGIN_URL, data,{
      headers:head
    })
  }

}
