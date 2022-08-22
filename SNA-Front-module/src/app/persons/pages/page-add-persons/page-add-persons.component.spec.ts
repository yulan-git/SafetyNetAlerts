import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAddPersonsComponent } from './page-add-persons.component';

describe('PageAddPersonsComponent', () => {
  let component: PageAddPersonsComponent;
  let fixture: ComponentFixture<PageAddPersonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageAddPersonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageAddPersonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
