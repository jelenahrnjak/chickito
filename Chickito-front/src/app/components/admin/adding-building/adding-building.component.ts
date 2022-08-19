import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BuildingService } from '../../../services/building.service';  
import { ToastrService } from 'ngx-toastr';  
import Company from '../../../model/company'
import { CompanyService } from '../../../services/company.service';

@Component({
  selector: 'app-adding-building',
  templateUrl: './adding-building.component.html',
  styleUrls: ['./adding-building.component.scss']
})
export class AddingBuildingComponent implements OnInit {

  form!: FormGroup;   
  allCompanies : Company[] = [] 
  display = "none";

  constructor( 
    private toastr: ToastrService, 
    private buildingService: BuildingService,
    private companyService: CompanyService,
    private router: Router, 
    private formBuilder: FormBuilder,) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      street: ['', Validators.compose([Validators.required])],
      number: ['', Validators.compose([Validators.required])],
      longitude: [''],//Validators.compose([Validators.required])],
      latitude: [''],// Validators.compose([Validators.required])],
      city: ['', Validators.compose([Validators.required])],
      postalCode: ['', Validators.compose([Validators.required])],
      country: ['', Validators.compose([Validators.required])],
      companyId : ['', Validators.compose([Validators.required])],
      headOffice : [false],
    });

    
    this.companyService.getAll().subscribe((data : Company[]) => {
      this.allCompanies = data;
    }); 
  }

  checkHeadOffice() : string{
    const companyId = this.form.get('companyId')?.value 

    for(let i=0 ; i < this.allCompanies.length; i++){  
      
      if(this.allCompanies[i].id == companyId && this.allCompanies[i].headOfficeAddress != null){
        
        return this.allCompanies[i].headOfficeAddress
      }
  }

    return ''
  }

  onSubmit(){   
    console.log('evo ' + this.checkHeadOffice())
    if(this.form.get('headOffice')?.value == true && this.checkHeadOffice() !== ''){
      this.display = "block" 
      return
    } 

    this.create()
  }

  create(){
    this.display = "none";

    const building = {
      'companyId' : this.form.get('companyId')?.value,
      'address' : {
        'street' : this.form.get('street')?.value,
        'number' : this.form.get('number')?.value,
        'longitude' : this.form.get('longitude')?.value,
        'latitude' : this.form.get('latitude')?.value,
        'city' : {
          'name' : this.form.get('city')?.value,
          'postalCode' : this.form.get('postalCode')?.value,
          'country' : this.form.get('country')?.value, 
        }, 
      },
      'headOffice' : this.form.get('headOffice')?.value, 
    } 

    this.buildingService.createBuilding(building)
    .subscribe(data => { 
      this.toastr.success('Novi objekat uspešno kreiran!')  
      this.router.navigate(['admin']);
    },
      error => { 
        console.log('Adding building error');  
        this.toastr.error(error['error'].message)
      });
  }
 
  
  leaveOldHeadOffice(){
    this.form.get('headOffice')?.setValue(false)  
  }
  

}
