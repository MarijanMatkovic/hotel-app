import { Injectable } from '@angular/core';
import { Role } from '../models/role';
import {User} from '../models/user';


@Injectable({
  providedIn: 'root'
})
export abstract class AuthenticationService {

  login: (username: string, password: string) => void
  isLoggedIn: () => boolean
  hasRole: (role: Role) => boolean;
  logout: () => void;
  getUser: () => User | undefined

}
