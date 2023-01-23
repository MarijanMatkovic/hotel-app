import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestViewComponent } from './guest-view.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterTestingModule} from '@angular/router/testing';

describe('GuestViewComponent', () => {
  let component: GuestViewComponent;
  let fixture: ComponentFixture<GuestViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GuestViewComponent ],
      imports: [HttpClientTestingModule, MatSnackBarModule, ReactiveFormsModule, RouterTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuestViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
