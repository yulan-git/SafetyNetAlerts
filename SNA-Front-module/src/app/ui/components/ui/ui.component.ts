import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ui',
  templateUrl: './ui.component.html',
  styleUrls: ['./ui.component.scss']
})
export class UiComponent implements OnInit {
  public isNavOpen: boolean;

  constructor() {
    this.isNavOpen = true;
  }

  ngOnInit(): void {
  }

  public toggle(): void{
    this.isNavOpen = !this.isNavOpen;
  }

}
