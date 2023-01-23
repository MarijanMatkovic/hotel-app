import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import * as Store from 'store2';
@Injectable({
  providedIn: 'root'
})
export class CreateWorkerService {

  private readonly LOGIN_URL = `${environment.baseApiUrl}/admin/new-worker`

  constructor(private http: HttpClient) { }

  create(data:any) {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));    
    return this.http.post(this.LOGIN_URL, data, {
      headers:head,
      observe:'response'
    })
  }
  
}
