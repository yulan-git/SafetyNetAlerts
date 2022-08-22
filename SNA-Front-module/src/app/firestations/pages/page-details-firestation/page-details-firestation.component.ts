import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Address } from 'src/app/core/models/address';
import { FirestationsService } from '../../services/firestations.service';

@Component({
  selector: 'app-page-details-firestation',
  templateUrl: './page-details-firestation.component.html',
  styleUrls: ['./page-details-firestation.component.scss']
})
export class PageDetailsFirestationComponent implements OnInit {
  adressesList!: Address[];
  station!: number;
  enfants!: string;


  constructor(private firestationsService: FirestationsService, private route: ActivatedRoute) {
    this.route.params.subscribe(
      (params: Params) => {
        console.log(params);
        this.station = +params['station'];
        console.log(this.station);
        
        this.firestationsService.getById(this.station).subscribe(station => {
          this.adressesList = [...station.addressList];
        })
      })
  }

  ngOnInit(): void {
  }

  getChild(addressId: number) {
      this.firestationsService.getChildren(addressId).subscribe(enfants => {
        this.enfants = enfants;
        console.log(this.enfants);
      })
  }
}
