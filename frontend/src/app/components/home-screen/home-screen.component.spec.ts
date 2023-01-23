import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeScreenComponent } from './home-screen.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterTestingModule} from '@angular/router/testing';

// describe('HomeScreenComponent', () => {
//   let component: HomeScreenComponent;
//   let fixture: ComponentFixture<HomeScreenComponent>;
//
//   beforeEach(async () => {
//     await TestBed.configureTestingModule({
//       declarations: [ HomeScreenComponent ],
//         imports: [HttpClientTestingModule, MatSnackBarModule, RouterTestingModule]
//     })
//     .compileComponents();
//
//     fixture = TestBed.createComponent(HomeScreenComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });
//
//   it('should create', () => {
//     expect(component).toBeFalsy();
//   });
// });
