import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HandicapPermitsListComponent } from './handicap-permits-list.component';

describe('HandicapPermitsListComponent', () => {
  let component: HandicapPermitsListComponent;
  let fixture: ComponentFixture<HandicapPermitsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HandicapPermitsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HandicapPermitsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
