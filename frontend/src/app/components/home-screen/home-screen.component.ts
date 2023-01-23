import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {User} from '../../models/user';
import {Role} from 'src/app/models/role';
import { EditInfoService } from 'src/app/services/edit-info.service';
import { NotificationService } from 'src/app/services/notification.service';
import * as Store from 'store2';

@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.css']
})
export class HomeScreenComponent implements OnInit {

  public user: User | undefined

  info:any;
  infoVis:boolean;

  displayInfo: string[] = ['hotelName','address','fax','oib','email'];

  constructor(private auth: AuthenticationService,
              private eis: EditInfoService,
              private notify: NotificationService) {
  }

  ngOnInit(): void {
    this.user = this.auth.getUser()
    this.getInfo()
  }

  getInfo() {
    this.eis.getInfo().subscribe(
      res => {
        this.info = res;
        this.infoVis = true;
      },
      err => {
      }
    )
  }

  checkLoggedIn(): boolean {
    return this.auth.isLoggedIn();
  }

  logout(): void {
    this.auth.logout()
    Store.default.clearAll()
  }

  getUsername() : String{
    if(this.user?.username)
      return this.user.username
    return ''
  }

  getRole(): String {
    // @ts-ignore
    return this.user?.role.toString()
  }


  ifGuest(): boolean {
    if(this.getRole() == 'ROLE_GUEST')
      return true;
    return false;
  }

  ifAdmin(): boolean {
    if(this.getRole() == 'ROLE_ADMIN' || this.getRole() == 'ROLE_OWNER')
      return true;
    return false;
  }

  ifOwner(): boolean {
    if(this.getRole() == 'ROLE_OWNER')
      return true;
    return false;
  }

  ifHousekeeper(): boolean {
    if(this.getRole() == 'ROLE_HOUSEKEEPER')
      return true;
    return false;
  }

  ifReceptionist(): boolean {
    if(this.getRole() == 'ROLE_RECEPTIONIST' || this.getRole() == 'ROLE_ADMIN' || this.getRole() == 'ROLE_OWNER')
      return true;
    return false;
  }

}
