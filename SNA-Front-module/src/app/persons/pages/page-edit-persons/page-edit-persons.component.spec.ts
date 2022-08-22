import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageEditPersonsComponent } from './page-edit-persons.component';

describe('PageEditPersonsComponent', () => {
  let component: PageEditPersonsComponent;
  let fixture: ComponentFixture<PageEditPersonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageEditPersonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageEditPersonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
