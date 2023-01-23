import { Component, OnInit } from '@angular/core';
import {NotificationService} from '../../services/notification.service';
import {NewAdminService} from '../../services/new-admin.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { GetWorkersService } from 'src/app/services/get-workers.service';
import { GetUnitsService } from 'src/app/services/get-units.service';
import { IWorker } from 'src/app/models/IWorker';
import { unit } from 'src/app/models/unit';
import { EditInfoService } from 'src/app/services/edit-info.service';
import { RoomEditService } from 'src/app/services/room-edit.service';


@Component({
    selector: 'app-owner-panel',
    templateUrl: './owner-panel.component.html',
    styleUrls: ['./owner-panel.component.css']
})
export class OwnerPanelComponent implements OnInit {

    make: FormGroup = new FormGroup({})
    remove: FormGroup = new FormGroup({})
    adminVis:boolean
    priceVis:boolean
    infoVis: boolean
    hotelName: FormGroup = new FormGroup({})
    fax: FormGroup = new FormGroup({})
    email: FormGroup = new FormGroup({})
    roomAdd: FormGroup = new FormGroup({})
    roomRemove: FormGroup = new FormGroup({})
    minibarEdit: FormGroup = new FormGroup({})
    unitEdit: FormGroup = new FormGroup({})
    editVis: boolean;
    roomVis: boolean;
    billVis: boolean;

    minibarPriceVis: boolean;
    unitPriceVis: boolean

    workers:IWorker[];
    units:unit[];

    unitTypes:Set<String> = new Set<String>();
    typeCapacity:any = {};
    
    minibarPrices:any;
    typePrice:any = {};
    typePriceSet: any[] = [];

    displayedColumns: string[] = ['name','surname','oib','role','username'];
    displayUnits: string[] = ['name','unitType','capacity','price'];

    displayBill:string[] = ['brojRezervacije', 'price']
    bill:any;
    total:any;

    constructor(private notify: NotificationService,
                private nas: NewAdminService,
                private fb: FormBuilder,
                private gws: GetWorkersService,
                private gus: GetUnitsService,
                private eis: EditInfoService,
                private res: RoomEditService) { }

    ngOnInit(): void {
        this.make = this.fb.group({
            username: [null]
        })
        this.remove = this.fb.group({
            username: [null]
        })
        this.hotelName = this.fb.group({
            hotelName: [null]
        })
        this.fax = this.fb.group({
            fax: [null]
        })
        this.email = this.fb.group({
            email: [null]
        })
        this.minibarEdit = this.fb.group({
            type: [null],
            price: new FormControl(this.minibarEdit.value.price, [
                Validators.pattern("[0-9]*")
            ])
        })
        this.unitEdit = this.fb.group({
            type: [null],
            price: new FormControl(this.unitEdit.value.price, [
                Validators.pattern("[0-9]*")
            ])
        })
        this.roomAdd = this.fb.group({
            unitType: [null],
            name: new FormControl(this.roomAdd.value.capacity,[
                Validators.pattern("[0-9]*")
            ])
        })
        this.roomRemove = this.fb.group({
            name: [null]
        })
        this.getWorkers();
        this.getUnits();
        this.getMinibarPrices();
        this.getBills();
    }

    makeAdmin(username: String):void{
        this.nas.makeAdmin(username).subscribe(
            res => {
                this.notify.info("Korisnik je postavljen kao administrator!")
            },
            err => {
                if(err.status == 409) {
                    this.notify.info("Zadani korisnik je već postavljen kao administrator!")
                } else if (err.status == 403) {
                    this.notify.info("Zabranjen pristup!")
                } else {
                    this.notify.info("Dogodila se greška!")
                }
            }
        )
        this.getWorkers();
    }

    removeAdmin(username: String):void{
        this.nas.removeAdmin(username).subscribe(
            res => {
                console.log(res);
                this.notify.info("Korisnik više nije admin.")
            },
            err => {
                if(err.status == 409) {
                    this.notify.info("Korisnik već nije admin.");
                }
            }
        )
        this.getWorkers();
    }

    editInfo(data: String, n: number): void {
        let sendData;
        if(n == 0) {sendData = {'hotelName':data}}
        if(n == 1) {sendData = {'email':data}}
        if(n == 2) {sendData = {'fax':data}}
        this.eis.editInfo(data).subscribe(
            res => {
                this.notify.info("Uspješno dodane izmjene.");
            },
            err => {
                if(err.status == 403) {
                    this.notify.info("Zabranjen pristup!");
                } else {
                    this.notify.info("Dogodila se greška!");
                }
            }
        )
    }

    addRoom(data:any) {
        data.capacity = this.typeCapacity[data.unitType]
        console.log(data)
        this.res.addRoom(data).subscribe(
            res => {
                this.notify.info("Jedinica uspješno dodana.")
            },
            err => {
                this.notify.info("Dogodila se greška!")
            }
        )
    }
    removeRoom(data:any) {
        let sendData;
        for(let i = 0; i< this.units.length; i++) {
            if(this.units[i].name === data.name) {
                sendData = this.units[i]
            }
        }
        console.log(sendData);
        this.res.removeRoom(sendData).subscribe(
            res => {
                console.log(res)
                this.notify.info("Jedinica uspješno uklonjena.")
            },
            err => {
                this.notify.info("Dogodila se greška.")
            }
        )
    }

    editMB(data:any) {
        data.price = +data.price
        console.log(data)
        this.eis.editPrices(data).subscribe(
            res => {
                this.notify.info("Promjena obavljena.")
            },
            err => {
                this.notify.info("Dogodila se greska!")
            }
        )
    }

    editUnit(data:any) {
        console.log(data)
        this.eis.editPrices(data).subscribe(
            res => {
                this.notify.info("Cijena jedinice postavljena.")
            },
            err => {
                this.notify.info("Dogodila se greška.")
            }
        )
    }

    setPriceVis(): void {
        this.priceVis = !this.priceVis
        this.adminVis = false
        this.editVis = false
        this.infoVis = false
        this.roomVis = false
        this.billVis = false;
    }

    setAdminVis(): void{
        this.adminVis = !this.adminVis
        this.editVis = false;
        this.infoVis = false;
        this.roomVis = false;
        this.priceVis = false;
        this.billVis = false;
    }

    setInfoVis(): void{
        this.infoVis = !this.infoVis
        this.editVis = false
        this.roomVis = false
        this.adminVis = false
        this.priceVis = false;
        this.billVis = false;
    }

    setEditMode(): void{
        this.infoVis = false
        this.editVis = true
        this.roomVis = false
        this.adminVis = false;
        this.priceVis = false;
        this.billVis = false;
    }

    setRoomVis(): void{
        this.roomVis = !this.roomVis;
        this.infoVis = false
        this.editVis = false
        this.adminVis = false;
        this.priceVis = false;
        this.billVis = false;
    }

    setBillVis(): void{
        this.billVis = !this.billVis
        this.infoVis = false;
        this.editVis = false;
        this.adminVis = false;
        this.priceVis = false;
        this.roomVis = false;
    }

    getWorkers() {
        this.gws.get().subscribe(
            res => {
                this.workers = res;
            },
            err => {
                console.log(err);
            }
        )
    }

    getUnits() {
        this.gus.get().subscribe(
            res => {
                this.units = res;
                for(let i = 0; i < res.length; i++) {
                    this.unitTypes.add(res[i].unitType)
                    let name = res[i].unitType;
                    this.typeCapacity[name] = res[i].capacity;
                    this.typePrice[name] = res[i].price;
                }

                for(let key in this.typePrice) {
                    this.typePriceSet.push({'type':key, 'price':this.typePrice[key]})
                }

            },
            err => {
                console.log(err);
            }
        )
    }

    getMinibarPrices() {
        this.eis.getMBPrices().subscribe(
            res => {
                this.minibarPrices = res
            },
            err => {
                console.log("Err occured!")
            }
        )
    }

    getBills() {
        this.eis.getBillInfo().subscribe(
            res => {
                console.log(res)
                this.bill = res
                this.total = this.bill[this.bill.length-1].price
                this.bill.length = this.bill.length - 1
            },
            err => {
                this.notify.info("Dogodila se greska.")
            }
        )
    }

    whatRole(role:string) {
        switch (role) {
            case "ROLE_HOUSEKEEPER": {
                return "Spremačica";
            }
            case "ROLE_ADMIN": {
                return "Admin";
            }
            case "ROLE_RECEPTIONIST": {
                return "Recepcionist";
            }
            case "ROLE_GUEST": {
                return "Gost";
            }
            case "ROLE_OWNER": {
                return "Vlasnik";
            }
            default:
                return "NA";
        }
    }

    whatType(type:string) {
        switch (type) {
            case "DVOKREVETNA":
                return "Dvokrevetna";
            case "JEDNOKREVETNA":
                return "Jednokrevetna";
            case "OBITELJSKA":
                return "Obiteljska";
            case "APARTMAN":
                return "Apartman";
            default:
                return "NA";
        }
    }

    showMiniBarPrice() {
        this.minibarPriceVis = !this.minibarPriceVis;
        this.unitPriceVis = false;
    }

    showUnitPrice() {
        this.unitPriceVis = !this.unitPriceVis;
        this.minibarPriceVis = false;
    }

}
