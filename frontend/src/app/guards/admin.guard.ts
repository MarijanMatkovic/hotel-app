import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import {Role} from '../models/role'
import {AuthenticationService} from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  constructor(private auth: AuthenticationService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    if (this.auth.hasRole(Role.Admin)) return true;
    return this.auth.hasRole((Role.Owner));

  }

}
