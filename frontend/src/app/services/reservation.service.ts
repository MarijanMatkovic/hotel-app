import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import * as Store from 'store2'; 
import { myreservation } from '../models/myreservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private readonly resURL = `${environment.baseApiUrl}/myreservation`
  constructor(private http: HttpClient) { }


  get() {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
    return this.http.get<myreservation>(this.resURL,{
      headers: head
    });
  }

  addService(data:any) {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
    return this.http.post(this.resURL+'/add-service', data,{
      headers: head,
    });
  }

  removeService(data:any) {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
    return this.http.post(this.resURL+'/remove-service', data, {
      headers: head
    })
  }

  

}
