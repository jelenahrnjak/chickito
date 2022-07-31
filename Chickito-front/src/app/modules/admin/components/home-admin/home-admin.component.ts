import { Component, OnInit } from '@angular/core';
import Company from '../../../model/company'
import Building from '../../../model/building'
import { CompanyService } from '../../services/company.service';
import { BuildingService } from '../../services/building.service';

@Component({
  selector: 'app-home-admin',
  templateUrl: './home-admin.component.html',
  styleUrls: ['./home-admin.component.scss']
})
export class HomeAdminComponent implements OnInit {

  allCompanies : Company[] = [] 
  showBuildings = false;
  allBuildings : Building[] = []
  currentCompany : string = ""

  constructor(
    private companyService: CompanyService,
    private buildingService : BuildingService,
    ) { }

  ngOnInit(): void {
    this.getAllCompanies()
  }

  selectCompany(id : any, name : string){
    this.currentCompany = name
    this.showBuildings = true 
    this.buildingService.findAllByCompany(id).subscribe((data : Building[]) => {
      console.dir(data)
      this.allBuildings = data.sort((a, b) => Number(b.headOffice) - Number(a.headOffice));
    });  
  }

  getAllCompanies(){
    
    this.companyService.getAll().subscribe((data : Company[]) => {
      this.allCompanies = data;
    }); 
  }
}
