import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CovidAPIComponent } from './covid-api.component';

describe('CovidAPIComponent', () => {
  let component: CovidAPIComponent;
  let fixture: ComponentFixture<CovidAPIComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CovidAPIComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CovidAPIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
