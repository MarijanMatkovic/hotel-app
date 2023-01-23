import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationsComponent } from './reservations.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {ReactiveFormsModule} from '@angular/forms';

describe('ReservationsComponent', () => {
  let component: ReservationsComponent;
  let fixture: ComponentFixture<ReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationsComponent ],
      imports: [HttpClientTestingModule, MatSnackBarModule, ReactiveFormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should be invalid', async () =>{
      component.roomRes.controls["type"].setValue("");
      expect(component.roomRes.valid).toBeFalsy();
  })
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
