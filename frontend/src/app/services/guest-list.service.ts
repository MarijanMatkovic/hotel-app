import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import * as Store from 'store2';
import {IWorker} from '../models/IWorker';
import {Guest} from '../models/guest';

@Injectable({
  providedIn: 'root'
})
export class GuestListService {
    private readonly GUEST_URL = `${environment.baseApiUrl}/`
    constructor(private http: HttpClient) { }

    get(){
        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.get(this.GUEST_URL+'reservations/guests', {
            headers:head
        })
    }

    getReservations(){
        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.get(this.GUEST_URL+'reservations', {
            headers:head
        })
    }

    getBill(data:any) {

        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.post(this.GUEST_URL+'reservations/bill', data, {
            headers:head
        })
    } 

    getRooms() {
        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.get(this.GUEST_URL+'reservations/in-use', {
            headers:head
        })
    }

}
