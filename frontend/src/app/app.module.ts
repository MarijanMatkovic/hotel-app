import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginScreenComponent } from './components/login-screen/login-screen.component';
import { HousekeeperViewComponent } from './components/housekeeper-view/housekeeper-view.component';
import { AdminPanelComponent } from './components/admin-panel/admin-panel.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { GuestViewComponent } from './components/guest-view/guest-view.component';
import { ReceptionistViewComponent } from './components/receptionist-view/receptionist-view.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HomeScreenComponent } from './components/home-screen/home-screen.component';
import { OwnerPanelComponent } from './components/owner-panel/owner-panel.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Overlay } from '@angular/cdk/overlay';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthenticationService } from './services/authentication.service';
import { LoginService } from './services/login.service';
import { NotificationService } from './services/notification.service';
import { ProdAuthenticationService } from './services/prod-authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { MatListModule } from '@angular/material/list';
import { WeatherWidgetComponent } from './components/weather-widget/weather-widget.component';
import { TimeWidgetComponent } from './components/time-widget/time-widget.component';
import { CovidAPIComponent } from './components/covid-api/covid-api.component';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatFormFieldControl} from '@angular/material/form-field';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectCountryModule} from '@angular-material-extensions/select-country';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card'
import {RouterModule} from '@angular/router';


const authService = {provide: AuthenticationService, useClass: ProdAuthenticationService}

@NgModule({
  declarations: [
    AppComponent,
    LoginScreenComponent,
    HousekeeperViewComponent,
    AdminPanelComponent,
    ReservationsComponent,
    GuestViewComponent,
    ReceptionistViewComponent,
    HomeScreenComponent,
    OwnerPanelComponent,
    WeatherWidgetComponent,
    TimeWidgetComponent,
    CovidAPIComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        BrowserAnimationsModule,
        HttpClientModule,
        MatListModule,
        MatSelectModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatInputModule,
        MatButtonModule,
        ReactiveFormsModule,
        MatIconModule,
        MatDialogModule,
        MatCheckboxModule,
        MatFormFieldModule,
        MatRadioModule,
        MatSelectCountryModule.forRoot('hr'),
        MatTableModule,
        MatCardModule,
        RouterModule
    ],
  providers: [MatSnackBar, Overlay, NotificationService, LoginService, authService, HttpClient],
  bootstrap: [AppComponent],
})
export class AppModule { }
