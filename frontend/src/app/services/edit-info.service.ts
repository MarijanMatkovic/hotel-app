import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import * as Store from 'store2';

@Injectable({
  providedIn: 'root'
})
export class EditInfoService {

  private readonly LOGIN_URL = `${environment.baseApiUrl}/hotel/`

  constructor(private http: HttpClient) {}

  getInfo() {
    const head = new HttpHeaders().append('Authorization',Store.default.get('token'))
    return this.http.get(this.LOGIN_URL+'info', {
      headers: head,
    })
  }

  editInfo(data:any) {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
    return this.http.post(this.LOGIN_URL+'edit-info', data, {
        headers: head,
        observe: 'response'
    })
  }

  getMBPrices() {
    const head = new HttpHeaders().append('Authorization',Store.default.get('token'))
    return this.http.get(this.LOGIN_URL+'prices',{
      headers: head
    })
  }

  editPrices(data:any) {
    const head = new HttpHeaders().append('Authorization',Store.default.get('token'))
    return this.http.post(this.LOGIN_URL+'edit-prices',data,{
      headers:head
    })
  }

  getBillInfo() {
    const head = new HttpHeaders().append('Authorization',Store.default.get('token'))
    return this.http.get(this.LOGIN_URL+'info/bills',{
      headers:head
    })
  }

}
