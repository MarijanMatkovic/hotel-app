import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import * as Store from 'store2';
import {Reservation} from '../models/reservation';
import { guestList } from '../models/guestList';

@Injectable({
  providedIn: 'root'
})
export class ReservationsListService {
    private readonly RESERVATIONS_URL = `${environment.baseApiUrl}/reservations`
    constructor(private http: HttpClient) { }

    get(){
        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.get<Reservation[]>(this.RESERVATIONS_URL, {
            headers:head
        })
    }


    getGuests() {
        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.get<guestList[]>(this.RESERVATIONS_URL+'/guests', {
            headers:head
        })
    }
}
