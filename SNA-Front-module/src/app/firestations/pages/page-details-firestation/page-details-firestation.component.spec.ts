import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageDetailsFirestationComponent } from './page-details-firestation.component';

describe('PageDetailsFirestationComponent', () => {
  let component: PageDetailsFirestationComponent;
  let fixture: ComponentFixture<PageDetailsFirestationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageDetailsFirestationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageDetailsFirestationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
