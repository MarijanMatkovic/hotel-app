import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import * as Store from 'store2';
import {IWorker} from '../models/IWorker';

@Injectable({
  providedIn: 'root'
})
export class GetWorkersService {
    private readonly WORKER_URL = `${environment.baseApiUrl}/hotel/info/workers`
    constructor(private http: HttpClient) { }

    get(){
        const head = new HttpHeaders().append('Authorization', Store.default.get('token'));
        return this.http.get<IWorker[]>(this.WORKER_URL, {
          headers:head
        })
    }
}
