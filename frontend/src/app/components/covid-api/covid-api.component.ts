import { Component, Input, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'covid-api',
  templateUrl: './covid-api.component.html',
  styleUrls: ['./covid-api.component.css']
})
export class CovidAPIComponent implements OnInit {

  @Input() country: any = undefined;

  total:any;
  year:string;
  month:string;
  day:string;
  url = 'https://api.covid19api.com/'

  last = 'HR'

  constructor() { }
  
  ngOnInit(): void {
  }
  
  ngOnChanges(changes: SimpleChanges) {

    let prev = changes['country'].previousValue
    let next = changes['country'].currentValue

    if(prev !== next) {
      this.last = next;
      this.getInput(next);
    }

  }

  getInput(country:string) {
    this.getCountries(country);
  }

  getCountries(country:string) {
    if(country == '') return;
    this.total = 'Loading...';
    fetch(this.url+'total/country/' + country)
    .then(response => response.json())
    .then(data => {this.setCountries(data);})
  }
  setCountries(data:any) {
    let n = data.length;
    let country = data[n-1];
    this.total = country.Confirmed;
    let date = country.Date;
    this.year = date.substring(0,4);
    this.month = date.substring(5,7);
    this.day = date.substring(8,10);
  }

}
