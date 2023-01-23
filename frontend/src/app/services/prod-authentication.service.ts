import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { Role } from '../models/role';
import { User } from '../models/user';
import { LoginService } from './login.service';
import * as Store from 'store2';
import jwtDecode from 'jwt-decode';
import {TokenizedUser} from '../models/tokenizedUser';
import {NotificationService} from './notification.service';
import {Router} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class ProdAuthenticationService implements AuthenticationService{

  private user: User | undefined
  private tuser: TokenizedUser
  private token: string | null
  private readonly lsKey = 'user'

  constructor(
    private loginService: LoginService,
    private notify: NotificationService,
    private router: Router
  ) {
    this.user = Store.default.get(this.lsKey)
    this.token = Store.default.get('token')
    if(!this.user || !this.token) return
  }

  hasRole(role: Role): boolean {
    if(!this.user) return false;
    return this.user.role == role;
  }

  isLoggedIn(): boolean {
    return this.user != undefined;
  }

  login(username: string, password: string) {
    this.loginService.login(username, password).subscribe(
      res => {
        if(res.status == 200) {
          this.token = res.headers.get('Authorization')
          if(this.token) {
            this.tuser = jwtDecode<TokenizedUser>(this.token.replace('Bearer ',''))
            this.user = new User(this.tuser.sub, this.tuser.role)
            Store.default.set('token', this.token)
            Store.default.set(this.lsKey, this.user)
            this.notify.info("Successful login!")
            this.router.navigateByUrl('')
          } else {
            this.notify.info("Unsuccessful login!")
          }
        }
      },
      error => {
        this.notify.info("Unsuccessful login!")
      })

  }

  logout(): void {
    this.user = undefined
    Store.default.remove(this.lsKey);
    this.router.navigateByUrl('/login')
  }

  getUser(): User | undefined{
    return this.user
  }



}
