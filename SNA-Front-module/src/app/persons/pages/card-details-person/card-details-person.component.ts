import { Component, Input, OnInit } from '@angular/core';
import { Person } from 'src/app/core/models/person';

@Component({
  selector: 'app-card-details-person',
  templateUrl: './card-details-person.component.html',
  styleUrls: ['./card-details-person.component.scss']
})
export class CardDetailsPersonComponent implements OnInit {
  @Input() person!: Person;
  public isShownAll: boolean;
  public isShownMed: boolean;

  constructor() {
    this.isShownAll = false;
    this.isShownMed = false;
  }

  ngOnInit(): void {
  }

  public toggleAll(): void {
    this.isShownAll = !this.isShownAll;
  }
  public toggleMed(): void {
    this.isShownMed = !this.isShownMed;
  }


}
