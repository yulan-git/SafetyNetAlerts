import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAddFirestationComponent } from './page-add-firestation.component';

describe('PageAddFirestationComponent', () => {
  let component: PageAddFirestationComponent;
  let fixture: ComponentFixture<PageAddFirestationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageAddFirestationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageAddFirestationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
