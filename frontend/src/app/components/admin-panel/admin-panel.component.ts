import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../services/notification.service';
import { CreateWorkerService } from 'src/app/services/create-worker.service';  
import { Role } from 'src/app/models/role';
import { Worker } from '../../models/worker';
import {RemoveWorkerService} from '../../services/remove-worker.service';
import * as http from 'http';
import {HttpClient} from '@angular/common/http';
import {GetWorkersService} from '../../services/get-workers.service';
import {workspaceSchemaPath} from '@angular/cli/src/utilities/config';
import {tap, window, windowToggle, windowWhen} from 'rxjs';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {IWorker} from "../../models/IWorker";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

    addVis: boolean
    removeVis: boolean

    workers:IWorker[];
    displayedColumns: string[] = ['name','surname','oib','role','username', 'remove'];


    roleList = [Role.Housekeeper, Role.Receptionist]
  // workers$: any;
  addWorker: FormGroup = new FormGroup({});

  constructor(private notify: NotificationService,
              private cws: CreateWorkerService,
              private rws: RemoveWorkerService,
              private gws: GetWorkersService,
              private fb: FormBuilder) { }

  ngOnInit(): void {
      this.addVis = false
      this.removeVis = false
      this.getWorkers()
      this.addWorker = this.fb.group({
        name: [null],
        surname: [null],
        oib: new FormControl(this.addWorker.value.oib, [
          Validators.pattern("[0-9]*"),
          Validators.minLength(11),
          Validators.maxLength(11)
        ]),
        address: [null],
        phoneNumber: [null],
        username: [null],
        password: [null],
        role: [null],
        dateOfHire: [null]
      })
  }

  handleCreate(data:any): void {
    this.cws.create(data).subscribe(
      res => {
        if(res.status == 200) {
          this.notify.info
          (
          "Uspješno stvoren radnik!"+
          "\n Radnik: "+data.name+" "+data.surname
          );
        }
      },
      err => {
        if(err.status == 409) {
          this.notify.info("Radnik već postoji!")
        } else {
          this.notify.info("Dogodila se greška!")
        }
      }
    )

      this.getWorkers()
  }

  getRoleList():Role[] {
    return this.roleList;
  }

  roleToString(role:Role) {
    switch (role) {
      case Role.Receptionist:
        return 'Djelatnik na recepciji'
      case Role.Housekeeper:
        return 'Sobarica/Spremačica'
      default:
        return ''
    }
  }

  getWorkers():any{

    this.gws.get().subscribe(
      res => {
        this.workers = res;
      },
      error => {
        console.log("Dogodila se greška!")
      }
    )
  }

  removeWorker(worker: Worker) {
      let sendOib = {'oib':worker.oib}
      this.rws.remove(sendOib).subscribe(
          res => {
              if(res.status == 200) {
                this.notify.info("Radnik uspješno uklonjen!");
                this.ngOnInit();
              }
          },
          err => {
              this.notify.info("Dogodila se pogreška!");
          }
      )

  }

    hideAdd(): void{
        this.removeVis = false
        this.addVis = !this.addVis
    }

    hideRemove(): void{
        this.addVis = false
        this.removeVis = !this.removeVis
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

}
