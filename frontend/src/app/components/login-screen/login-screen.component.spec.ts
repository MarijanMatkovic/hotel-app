import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginScreenComponent } from './login-screen.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {RouterTestingModule} from '@angular/router/testing';

describe('LoginScreenComponent', () => {
  let component: LoginScreenComponent;
  let fixture: ComponentFixture<LoginScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginScreenComponent ],
        imports: [HttpClientTestingModule, MatSnackBarModule, RouterTestingModule, FormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
