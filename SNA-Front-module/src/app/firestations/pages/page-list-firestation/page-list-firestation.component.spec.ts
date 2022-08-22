import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageListFirestationComponent } from './page-list-firestation.component';

describe('PageListFirestationComponent', () => {
  let component: PageListFirestationComponent;
  let fixture: ComponentFixture<PageListFirestationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageListFirestationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageListFirestationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
