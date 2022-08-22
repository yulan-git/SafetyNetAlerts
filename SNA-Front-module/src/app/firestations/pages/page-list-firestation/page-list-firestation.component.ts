import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/core/models/address';
import { Firestation } from 'src/app/core/models/firestation';
import { FirestationsService } from '../../services/firestations.service';

@Component({
  selector: 'app-page-list-firestation',
  templateUrl: './page-list-firestation.component.html',
  styleUrls: ['./page-list-firestation.component.scss']
})
export class PageListFirestationComponent implements OnInit {
  firestationsList!: Firestation[];

  constructor(private firestationsService: FirestationsService) { 
    this.firestationsService.firestations$.subscribe((data) => {
      this.firestationsList = [...data];
      console.log(this.firestationsList);
    })
    
  }

  ngOnInit(): void {
  }
}
