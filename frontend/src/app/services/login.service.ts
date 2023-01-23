import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod'
import { map } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly LOGIN_URL = `${environment.baseApiUrl}/login`

  constructor(
    private http: HttpClient
  ) {}

  login(username: string, password: string) {
    return this.http.post(
      this.LOGIN_URL, 
      {username, password},
      {observe: 'response'})
  }

}
