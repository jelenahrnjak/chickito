import { Component, OnInit } from '@angular/core';
import Company from '../../../model/company'
import Building from '../../../model/building'
import { CompanyService } from '../../../services/company.service';
import { BuildingService } from '../../../services/building.service';
import { ToastrService } from 'ngx-toastr';  

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
  currentCompanyId : any = ""

  constructor(
    private toastr: ToastrService, 
    private companyService: CompanyService,
    private buildingService : BuildingService,
    ) { }

  ngOnInit(): void {
    this.reset()
  }

  reset(){
    this.allCompanies = []
    this.getAllCompanies();
    this.showBuildings = false
    this.allBuildings = []
  }

  selectCompany(id : any, name : string){
    this.currentCompany = name
    this.currentCompanyId = id;
    this.showBuildings = true  
    this.getBuildings(id)
  }

  getBuildings(id: any){
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

  deleteCompany(id : any, name: string){
    if (confirm('Da li ste sigurn da želite da obrišete ' + name + '?')) { //TODO modal 
      this.companyService.deleteCompany(id).subscribe(data => { 
        this.reset()
        this.toastr.success('Preduzeće uspešno obrisano!')  
      }); 
    }
  }

  deleteBuilding(id : any){ 
    if (confirm('Da li ste sigurn da želite da obrišete objekat?')) { //TODO modal 
      this.buildingService.deleteBuilding(id).subscribe(data => { 
        this.reset()
        this.selectCompany(this.currentCompanyId, this.currentCompany)
        this.toastr.success('Objekat uspešno obrisan!')  
      }); 
    }
  }

  changeHeadOffice(id:any){
    if (confirm('Da li ste sigurn da želite da promenite sedište?')) { //TODO modal 
      this.buildingService.changeHeadOffice(id).subscribe(data => { 
        this.reset()
        this.selectCompany(this.currentCompanyId, this.currentCompany)
        this.toastr.success('Sedište uspešno promenjeno!')  
      }); 
    }

  }
}
