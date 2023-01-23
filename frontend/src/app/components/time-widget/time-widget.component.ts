import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'time-widget',
  templateUrl: './time-widget.component.html',
  styleUrls: ['./time-widget.component.css']
})
export class TimeWidgetComponent implements OnInit {

  constructor() { }
  public day:string;
  public hour:string = '00';
  public minute:string = '00';
  public seconds:string = '00';
  private daysArray = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday','Saturday'];
  private date = new Date;

  ngOnInit(): void {
    setInterval(() => {
      const date = new Date();
      this.setUpdate(date);
    },1000);

    this.day = this.daysArray[this.date.getDay()];
  }

  setUpdate(date:Date) {
    const hours = date.getHours();
    this.hour = hours < 10 ? '0'+hours.toString() : hours.toString();
    const minutes = date.getMinutes();
    this.minute = minutes < 10 ? '0'+minutes.toString() : minutes.toString();
    const seconds = date.getSeconds();
    this.seconds = seconds < 10 ? '0'+seconds.toString() : seconds.toString();
  }

}
