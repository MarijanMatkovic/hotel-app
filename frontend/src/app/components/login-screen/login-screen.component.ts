import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { NotificationService } from '../../services/notification.service';


@Component({
  selector: 'app-login-screen',
  templateUrl: './login-screen.component.html',
  styleUrls: ['./login-screen.component.css']
})
export class LoginScreenComponent implements OnInit {

  constructor(
    private router: Router,
    private auth: AuthenticationService,
    private notification: NotificationService,
  ) {
  }

  ngOnInit(): void {
  }

  handleLoginClick(data:any): void {
    this.notification.info('Logging in...')
    this.auth.login(data.username, data.password)
  }

}
