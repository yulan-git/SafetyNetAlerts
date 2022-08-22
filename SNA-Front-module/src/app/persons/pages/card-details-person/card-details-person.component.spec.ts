import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardDetailsPersonComponent } from './card-details-person.component';

describe('CardDetailsPersonComponent', () => {
  let component: CardDetailsPersonComponent;
  let fixture: ComponentFixture<CardDetailsPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardDetailsPersonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardDetailsPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
