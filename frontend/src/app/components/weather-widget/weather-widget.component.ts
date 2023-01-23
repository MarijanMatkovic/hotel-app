import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'weather-widget',
  templateUrl: './weather-widget.component.html',
  styleUrls: ['./weather-widget.component.css']
})
export class WeatherWidgetComponent implements OnInit {

  WeatherData:any;
  city = 'Zagreb'
  appid = '53421aee497bfcad1e20db71756d2232'
  url = 'https://api.openweathermap.org/data/2.5/weather?q='+ this.city +'&appid='+this.appid

  constructor() { }

  ngOnInit(): void {
    this.WeatherData = {
      main : {},
      isDay: true
    }
    this.getWeatherData();
  }
  getWeatherData() {
    fetch(this.url)
    .then(response => response.json())
    .then(data => {this.setWeatherData(data);})
  }
  setWeatherData(data:any) {
    this.WeatherData = data;
    let sunsetTime = new Date(this.WeatherData.sys.sunset * 1000);
    this.WeatherData.sunset_time = sunsetTime.toLocaleTimeString();
    let currentDate = new Date();
    this.WeatherData.isDay = (currentDate.getTime() < sunsetTime.getTime());
    this.WeatherData.temp_celcius = (this.WeatherData.main.temp - 273.15).toFixed();
    this.WeatherData.temp_min = (this.WeatherData.main.temp_min - 273.15).toFixed();
    this.WeatherData.temp_max = (this.WeatherData.main.temp_max - 273.15).toFixed();
    this.WeatherData.temp_feels_like = (this.WeatherData.main.feels_like - 273.15).toFixed();
  }

}
