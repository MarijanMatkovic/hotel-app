import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';
import * as Store from 'store2'; 

@Injectable({
  providedIn: 'root'
})
export class RoomEditService {

  private readonly resURL = `${environment.baseApiUrl}/hotel/`
  constructor(private http: HttpClient) { }

  addRoom(data:any) {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
    return this.http.post(this.resURL+'add-unit', data,{
      headers: head
    });
  }

  removeRoom(data:any) {
    const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
    return this.http.post(this.resURL+'remove-unit', data,{
      headers: head
    });
  }

}
