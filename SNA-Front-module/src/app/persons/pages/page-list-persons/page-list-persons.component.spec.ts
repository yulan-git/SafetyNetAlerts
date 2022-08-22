import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageListPersonsComponent } from './page-list-persons.component';

describe('PageListPersonsComponent', () => {
  let component: PageListPersonsComponent;
  let fixture: ComponentFixture<PageListPersonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageListPersonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageListPersonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
