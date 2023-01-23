import { Component, OnInit } from '@angular/core';
import { myreservation } from 'src/app/models/myreservation';
import { NotificationService } from 'src/app/services/notification.service';
import { ReservationService } from 'src/app/services/reservation.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-guest-view',
  templateUrl: './guest-view.component.html',
  styleUrls: ['./guest-view.component.css']
})
export class GuestViewComponent implements OnInit {
  
  add:boolean = false;
  remove:boolean = false;
  info:boolean = false;
  resInfo:myreservation;
  serviceForm:any;
  removeForm:any;

  types:any = [
    'BREAKFAST',
    'LUNCH',
    'DINNER'
  ]

  constructor(private rs: ReservationService,
              private notify: NotificationService,
              private fb: FormBuilder) {}


  ngOnInit(): void {
    this.getReservation();
    this.serviceForm = this.fb.group({
      serviceName: [null],
      dateFrom: [null],
      dateTo: [null],
    })
    this.removeForm = this.fb.group({
      serviceName: [null],
      dateFrom: [null],
      dateTo: [null]
    })
  }

  setInfo(): void{
    this.info = !this.info;
    this.add = false;
    this.remove = false;
  }

  setAdd() {
    this.add = !this.add;
    this.info = false;
    this.remove = false;
  }

  setRemove() {
    this.remove = !this.remove;
    this.add = false;
    this.info = false;
  }

  getReservation() {
    this.rs.get().subscribe(
      res => {
        this.resInfo = res;
      },
      err => {
        this.notify.info('Dogodila se neočekivana greška!');
      }   
    )
  }

  addService(data:any) {
    let df = new Date(data.dateFrom);
    let dt = new Date(data.dateTo);
    let sendData = {'serviceName':data.serviceName,
                    'dates':undefined};
    df.setDate(df.getDate()+1)
    dt.setDate(dt.getDate()+1)
    let dates:any = [];
    while(df <= dt) {
      dates.push(df.toISOString().substring(0,10));
      df.setDate(df.getDate() + 1);
    }
    sendData.dates = dates;
    this.rs.addService(sendData).subscribe(
      res => {
        this.notify.info('Uspješno obavljeno.')
      },
      err => {
        if(err.status == 200) {
          this.notify.info('Uspješno obavljeno.')
        }
        else if(err.status == 500) {
          this.notify.info('Dogodila se greska sa serverom.');
        } else {
          this.notify.info('Dogodila se greška!');
        }
      }
    );
    this.getReservation();

  }

  removeService(data:any) {
    let df = new Date(data.dateFrom)
    let dt = new Date(data.dateTo)
    let sendData = {'serviceName': data.serviceName,
                    'dates':undefined};
    df.setDate(df.getDate()+1)
    dt.setDate(dt.getDate()+1)
    let dates:any = [];
    while(df <= dt) {
      dates.push(df.toISOString().substring(0,10));
      df.setDate(df.getDate() + 1)
    }
    sendData.dates = dates;
    this.rs.removeService(sendData).subscribe(
      res => {
        this.notify.info('Uspješno obavljeno.')
      },
      err => {
        if(err.status == 200) {
          this.notify.info('Uspješno obavljeno!')
        } else if(err.status==500) {
          this.notify.info('Dogodila se greška sa serverom.')
        } else {
          this.notify.info('Dogodila se greška!')
        }
      }
    )
    this.getReservation()
    
  }

}
