import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import { ReceptionistViewComponent } from './receptionist-view.component';
import {DebugElement} from '@angular/core';
import {BrowserModule, By} from '@angular/platform-browser';
import {FormBuilder, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import {RegisterService} from '../../services/register.service';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';

describe('ReceptionistViewComponent', () => {
  let component: ReceptionistViewComponent;
  let fixture: ComponentFixture<ReceptionistViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReceptionistViewComponent ],
      imports: [BrowserModule,
                FormsModule,
                ReactiveFormsModule,
                HttpClientTestingModule,
                MatSnackBarModule,
                MatFormFieldModule,
                MatInputModule, MatCardModule],
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReceptionistViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();


  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should be invalid', async() => {
      component.regForm.controls['username'].setValue("");
      component.regForm.controls['name'].setValue("");
      component.regForm.controls['oib'].setValue("");
      expect(component.regForm.valid).toBeFalsy();
  })

  it('oib should be invalid', async () =>{
      component.regForm.controls['oib'].setValue("12345678912345");
      expect(component.regForm.valid).toBeFalsy()
  })
});
