import { Component, NgModule, OnInit, SimpleChange, SimpleChanges } from '@angular/core';
import { RegisterService } from '../../services/register.service';
import {NotificationService} from '../../services/notification.service';
import {catchError, delay, first, of, tap, throwError} from 'rxjs';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from '@angular/forms';
import {GuestListService} from '../../services/guest-list.service';
import {Guest} from '../../models/guest';
import { resolve } from 'path';
//import {FormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-receptionist-view',
  templateUrl: './receptionist-view.component.html',
  styleUrls: ['./receptionist-view.component.css']
})
export class ReceptionistViewComponent implements OnInit {

  showCovid:boolean = false;
  country:any;
  regForm: FormGroup = new FormGroup({})
  bill: FormGroup = new FormGroup({})
  guestListVis:boolean = false;
  newGuestVis:boolean = false;
  guests: any;
  displayedColumns: string[] = ['name','surname','accommodatingUnitName'];
  
  billVis:boolean;
  billView:boolean;
  reservations:any;

  billData:any;

  constructor(
    private regService: RegisterService,
    private notify: NotificationService,
    private fb: FormBuilder,
    private gls: GuestListService){}

  ngOnInit(): void {
    this.regForm = this.fb.group({
      username: [null, Validators.required],
      name: [null],
      surname: [null],
      address: [null],
      oib: new FormControl(this.regForm.value.oib, [
        Validators.pattern("[0-9]*"),
        Validators.minLength(11),
        Validators.maxLength(11)
      ]),
      phoneNumber: [null],
      password: [null],
      country: [null]
      })
      this.bill = this.fb.group({
        username: [null]
      })
      this.getReservations();
      this.getGuestList();
  }

  handleRegister(data:any): void {

    if(data.country != null) {
      this.country = data.country.alpha3Code;
      data.country = data.country.name
    }
    this.regService.register(data).subscribe(
      res => {
        if(res.status == 200) {
          this.notify.info
          (
          "Registration successful!"+
          "\n Username: "+data.username+
          "\n Password: "+data.password
          );
          if(this.country != undefined) {
            this.showCovid = true;
          }

        }
      },
      err => {
        if(err.status == 409) {
          this.notify.info("User exists!")
        } else {
          this.notify.info("Error occured!")
          if(this.country != undefined) {
            this.showCovid = true;
          }
        }
      }
    )
  }

  async handleBill(data:any) {
    
    let billId;
    for(let i = 0; i< this.reservations.length; i++) {
      if(this.reservations[i].username === data.username){
        billId = this.reservations[i].id
      }
    }
    
    const response = this.gls.getBill({'brojRezervacije':billId})
    .subscribe(
      res => {
        this.billData = res;
      },
      err => {
        this.notify.info("Dogodila se greška.")
      }
    )

    await new Promise(f => setTimeout(f,1000))

    this.billView = true;
  }

  showDialog():boolean {
    return this.showCovid;
  }

  getCountry():string {
    return this.country;
  }

  setGuestListVis(): void{
    this.newGuestVis = false;
    this.guestListVis = !this.guestListVis
    this.billVis = false
    this.billView = false
  }

  setNewGuestVis(): void{
    this.guestListVis = false;
    this.newGuestVis = !this.newGuestVis;
    this.billVis = false
    this.billView = false
  }
  
  setBillVis(): void{
    this.billVis = !this.billVis
    this.newGuestVis = false
    this.guestListVis = false
  }

  getGuestList(): void {
      this.gls.get().subscribe(
          res => {
            this.guests = res;
          },
          err => {
            this.notify.info('Dogodila se greška.')
          }
      )
  }

  getReservations(): void {
    this.gls.getReservations().subscribe(
      res => {
        this.reservations = res
      },
      err => {
        this.notify.info("Dogodila se greška.")
      }
    )
  }
}





