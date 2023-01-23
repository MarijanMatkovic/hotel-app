import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import * as Store from 'store2';

@Injectable({
  providedIn: 'root'
})
export class NewAdminService {

    private readonly LOGIN_URL = `${environment.baseApiUrl}/hotel/`

    constructor(private http: HttpClient) { }

    makeAdmin(data:any) {
        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.post(this.LOGIN_URL+'new-admin', data, {
            headers: head,
            observe: 'response'
        })
    }

    removeAdmin(data:any) {
        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.post(this.LOGIN_URL+'remove-admin', data, {
            headers: head,
            observe: 'response'
        })
    }
    
}
