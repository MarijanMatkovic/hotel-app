import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../services/notification.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AddArticleService } from 'src/app/services/add-article.service';
import { GuestListService } from 'src/app/services/guest-list.service';

@Component({
  selector: 'app-housekeeper-view',
  templateUrl: './housekeeper-view.component.html',
  styleUrls: ['./housekeeper-view.component.css']
})
export class HousekeeperViewComponent implements OnInit {

  addArticles: FormGroup = new FormGroup({});

  articles:any;

  room:number;
  currentMinibar:any;
  minibarArticles: number = 0;
  minibarVis:boolean

  rooms:any;

  constructor(
              private aas: AddArticleService,
              private fb: FormBuilder,
              private notify: NotificationService,
              private gls: GuestListService) {
  }
   
   ngOnInit(): void {
    this.addArticles = this.fb.group({
      room: new FormControl(this.addArticles.value.room,[
        Validators.pattern("[0-9]*")
      ]),
      article: [null],
      amount: new FormControl(this.addArticles.value.amount, [
        Validators.pattern("[1-9][0-9]*")
      ]),
      cleaned: [null],
      ready: [null]
    })
    this.getArticles()
    this.getRooms()
   }

  handleAdd(data:any): void {
    this.room = data.room;
    if(this.currentMinibar == undefined) {
      this.currentMinibar = {};
      this.currentMinibar[data.article] = +data.amount;
    } else {
      if(this.currentMinibar[data.article] == undefined) {
        this.currentMinibar[data.article] = +data.amount;
      } else {
        this.currentMinibar[data.article] += +data.amount;
      }
    }

    this.minibarArticles++;
    this.addArticles.reset();
    this.addArticles.controls['room'].setValue(this.room)
    this.addArticles.controls['cleaned'].setValue(data.cleaned)
    this.addArticles.controls['ready'].setValue(data.ready)
    this.setMiniBarVis();
  }

  getArticles():any {
    this.articles = undefined;
    this.aas.getData().subscribe(
      res => {
        this.articles = res;
      },
      err => {
        console.log('DOGODILA SE GRESKA');
      }
    )
  }

  remove(key:any) {
    key = ''+ key;
    this.currentMinibar[key] = undefined;
    this.minibarArticles--;
    this.setMiniBarVis();
  }

  sendData(data: any) {
    let toSend = {"roomName": this.room, "cleaned": data.cleaned, "ready": data.ready, "minibarList": this.currentMinibar};

    this.aas.sendData(toSend).subscribe(
      res => {
        if(!res) {
          this.notify.info('Slanje nije uspjelo! Pregledajte izvještaj.');
        } else {
          this.notify.info('Slanje usješno!');
          this.addArticles.reset();
        }
      },
      err => {
        this.notify.info('Dogodila se neočekivana greška!');
        console.log(err);
      }
      );

    this.addArticles.reset();
    this.currentMinibar = null
    this.minibarArticles = 0;
  }

  setMiniBarVis() {
    if(this.minibarArticles > 0) {
      this.minibarVis = true;
    } else {
      this.minibarVis = false;
    }
  }

  getRooms() {
    this.gls.getRooms().subscribe(
      res => {
        console.log(res)
        this.rooms = res;
      },
      err => {
        this.notify.info("Dogodila se greška.")
      }
    )
  }
}
