import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HousekeeperViewComponent } from './housekeeper-view.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {RouterTestingModule} from '@angular/router/testing';

// describe('HousekeeperViewComponent', () => {
//   let component: HousekeeperViewComponent;
//   let fixture: ComponentFixture<HousekeeperViewComponent>;
//
//   beforeEach(async () => {
//     await TestBed.configureTestingModule({
//       declarations: [ HousekeeperViewComponent ],
//         imports: [HttpClientTestingModule, MatSnackBarModule, ReactiveFormsModule, MatFormFieldModule,
//             MatInputModule, MatCardModule, RouterTestingModule]
//     })
//     .compileComponents();
//
//     fixture = TestBed.createComponent(HousekeeperViewComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });
//
//   it('should create', () => {
//     expect(component).toBeFalsy();
//   });
// });
