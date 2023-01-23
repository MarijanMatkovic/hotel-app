import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import { User } from '../models/user';
import {first, firstValueFrom, map, Observable, tap} from 'rxjs';
import * as Store from 'store2';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private readonly LOGIN_URL = `${environment.baseApiUrl}/register`

  constructor(
    private http: HttpClient
  ) { }

  register(data:any) {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));    
    return this.http.post(this.LOGIN_URL, data, {
      headers:head,
      observe:'response'
    })
  }



}
