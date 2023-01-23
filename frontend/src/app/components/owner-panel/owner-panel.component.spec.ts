import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerPanelComponent } from './owner-panel.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ReactiveFormsModule} from '@angular/forms';
import {DebugElement} from '@angular/core';

describe('OwnerPanelComponent', () => {
  let component: OwnerPanelComponent;
  let fixture: ComponentFixture<OwnerPanelComponent>;
  let el: DebugElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerPanelComponent ],
      imports: [MatSnackBarModule, HttpClientTestingModule, ReactiveFormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    el = fixture.debugElement;
  });
  it('should be disabled', () => {
      component.make.controls["username"].setValue("Mate");
      expect(el.nativeElement.querySelector('button').disabled).toBeFalsy();
  })
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
