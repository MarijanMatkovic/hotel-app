import { Component, OnInit } from '@angular/core';
import {Form, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NotificationService} from '../../services/notification.service';
import {RoomReservationService} from '../../services/room-reservation.service';
import {ConferenceReservationService} from '../../services/conference-reservation.service';
import {ResUser} from '../../models/resUser';
import {RemoveReservationService} from '../../services/remove-reservation.service';
import {Reservation} from '../../models/reservation';
import {ReservationsListService} from '../../services/reservations-list.service';
import {Router} from "@angular/router"
import { GuestListService } from 'src/app/services/guest-list.service';
import { guestList } from 'src/app/models/guestList';


@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

    roomVis: Boolean
    confRoomVis: Boolean
    userAdded: Boolean
    removeResVis: Boolean
    resListVis: Boolean

    displayedColumns: string[] = ['id','username','dateFrom','dateTo', 'remove', 'bill'];
    reservations: Reservation[]

    roomRes: FormGroup = new FormGroup({})
    confRes: FormGroup = new FormGroup({})
    addUsers: FormGroup = new FormGroup({})
    remRes: FormGroup = new FormGroup({})
    resUsers: ResUser[] = []

    billVis:boolean;
    billView:boolean;
    billData:any;

    guestVis:boolean
    guests:any ;
    displayGuest: string[] = ['name','surname','oib','address','phoneNumber','username','accommodatingUnitName']

    roomlistVis:boolean;
    rooms:any;
    displayRooms: string[] = ['unitType','capacity','name','price']

    constructor(private fb: FormBuilder,
                private notify: NotificationService,
                private rrs: RoomReservationService,
                private crs: ConferenceReservationService,
                private removeRes: RemoveReservationService,
                private resListService: ReservationsListService,
                private gls: GuestListService) {
        this.roomVis = false;
        this.confRoomVis = false;
        this.removeResVis = false;
        this.userAdded = false;
        this.resListVis = false;
    }

    ngOnInit(): void {
        this.roomRes = this.fb.group({
            type: [null, Validators.required],
            dateFrom: [null],
            dateTo: [null],
            lunch: [null],
            dinner: [null]
        })
        this.confRes = this.fb.group({
            name:[null],
            type: [null],
            date: [null]
        })
        this.addUsers = this.fb.group({
            name: [null],
            password: [null]
        })
        this.remRes = this.fb.group({
            username: [null]
        })
        this.getReservations();
        this.getGuestList();
        this.getRooms();
    }

    hideRoomVis():void {
        this.roomlistVis = !this.roomlistVis
        this.confRoomVis = false
        this.removeResVis = false
        this.resListVis = false
        this.guestVis = false
        this.roomVis = false
    }

    hideGuestList():void {
        this.confRoomVis = false
        this.removeResVis = false
        this.resListVis = false
        this.roomVis = false
        this.guestVis = !this.guestVis
        this.roomlistVis = false
    }

    hideShowRoom(): void{
        this.roomlistVis = false
        this.confRoomVis = false
        this.removeResVis = false
        this.resListVis = false
        this.roomVis = !this.roomVis
        this.guestVis = false
    }

    hideShowConf(): void{
        this.roomlistVis = false
        this.roomVis = false
        this.removeResVis = false
        this.resListVis = false
        this.confRoomVis = !this.confRoomVis
        this.guestVis = false
    }

    hideShowRem(): void{
        this.roomlistVis = false
        this.roomVis = false
        this.confRoomVis = false
        this.resListVis = false
        this.removeResVis = !this.removeResVis
        this.guestVis = false
    }

    hideShowResList(): void{
        this.roomlistVis = false
        this.roomVis = false
        this.confRoomVis = false
        this.removeResVis = false
        this.resListVis = !this.resListVis
        this.guestVis = false
    }

    submitRoom(data: any){
        data.users = this.resUsers;
        console.log(data)
        let df = new Date(this.roomRes.controls["dateFrom"].value)
        let dt = new Date(this.roomRes.controls["dateTo"].value)
        if(!this.validateRoomRes(df, dt)){
            this.notify.info("Pogrešno uneseni datumi prijave i odjave!")
            return
        }
        this.rrs.reserve(data).subscribe(
            res => {
                if(res.status == 200)
                    this.notify.info("Rezervacija uspješna!")
            },
                err => {
                    if(err.status == 409) {
                        this.notify.info("U zadanom periodu nije moguće rezervirati sobu!")
                    } else {
                        this.notify.info("Dogodila se greška!")
                    }
                }
        )


    }

    submitConf(data: any){
        let date = new Date(this.confRes.controls["date"].value)
        if(!this.validateConfRes(date)){
            this.notify.info("Datum rezervacije mora biti najmanje 3 dana nakon današnjeg dana!");
            return
        }
        this.crs.reserve(data).subscribe(
            res => {
                if(res.status == 200)
                    this.notify.info("Rezervacija uspješna!")
            },
            err => {
                if(err.status == 409) {
                    this.notify.info("U zadanom periodu nije moguće rezervirati dvoranu!")
                } else {
                    this.notify.info("Dogodila se greška!")
                }
            }
        )

    }

    validateRoomRes(dateFrom: Date, dateTo: Date): boolean{
        let today = new Date();
        if(dateFrom.getDate() < today.getDate())
            return false
        return dateFrom < dateTo;

    }

    validateConfRes(date: Date){
        let today = new Date();
        return today.getDate() + 3 < date.getDate();

    }

    handleAddUser(name: string, password:string):void{
        let user = new ResUser(name, password)
        this.resUsers.push(user)
        this.userAdded = true
        this.addUsers.reset();
    }

    removeUser(user: ResUser): void{
        let index = this.resUsers.indexOf(user)
        this.resUsers.splice(index, 1)
    }

    removeReservation(data: any){
        this.removeRes.remove({'username':data.username}).subscribe(
            res => {
                if(res.status == 200)
                    this.notify.info("Rezervacija uklonjena!")
            },
            err => {
                this.notify.info("Dogodila se greška! Rezervacija nije uklonjena")
            }
        )
    }
    

    getReservations() {
        this.resListService.get().subscribe(
            res => {
                this.reservations = res;
            },
            err => {
                console.log(err);
            }
        )
    }

    getGuestList() {
        this.resListService.getGuests().subscribe(
            res => {
                let curr = res
                this.guests = []
                for(let i=0; i < curr.length; i++) {
                    if(curr[i].oib !== null) {
                        this.guests.push(curr[i])
                    }
                }
                
            },
            err => {
                this.notify.info('Dogodila se greška')
            }
        )
    }

    getRooms() {
        this.gls.getRooms().subscribe(
            res => {
                console.log(res)
                this.rooms = res;
            },
            err => {
                this.notify.info('Dogodila se greška.')
            }
        )
    }

    async handleBill(data:any) {
    
        let billId;
        for(let i = 0; i< this.reservations.length; i++) {
            if(this.reservations[i].username === data){
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

}
