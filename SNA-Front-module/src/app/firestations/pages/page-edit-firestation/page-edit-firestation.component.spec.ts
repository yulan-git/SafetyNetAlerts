import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageEditFirestationComponent } from './page-edit-firestation.component';

describe('PageEditFirestationComponent', () => {
  let component: PageEditFirestationComponent;
  let fixture: ComponentFixture<PageEditFirestationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageEditFirestationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageEditFirestationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
