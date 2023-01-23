import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import * as Store from 'store2';

@Injectable({
  providedIn: 'root'
})
export class ConferenceReservationService {
    private readonly LOGIN_URL = `${environment.baseApiUrl}/reservations/hall`
    constructor(private http: HttpClient) { }

    reserve(data:any){
        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.post(this.LOGIN_URL, data, {
            headers:head,
            observe:'response'
        })
    }
}
