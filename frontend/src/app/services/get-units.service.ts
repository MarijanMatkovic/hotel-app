import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import * as Store from 'store2';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { unit } from '../models/unit';

@Injectable({
  providedIn: 'root'
})
export class GetUnitsService {
  
  private readonly UNITS_URL = `${environment.baseApiUrl}/hotel/info/units`
  constructor(private http: HttpClient) { }
  get(){
      const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
      return this.http.get<unit[]>(this.UNITS_URL, {
        headers:head
      })
  }
}