import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BuildingService } from '../../services/building.service';  
import { ToastrService } from 'ngx-toastr';  
import Company from '../../../model/company'
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-adding-building',
  templateUrl: './adding-building.component.html',
  styleUrls: ['./adding-building.component.scss']
})
export class AddingBuildingComponent implements OnInit {

  form!: FormGroup;   
  allCompanies : Company[] = []

  constructor( 
    private toastr: ToastrService, 
    private buildingService: BuildingService,
    private companyService: CompanyService,
    private router: Router, 
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      street: ['', Validators.compose([Validators.required])],
      number: ['', Validators.compose([Validators.required])],
      longitude: [''],//Validators.compose([Validators.required])],
      latitude: [''],// Validators.compose([Validators.required])],
      city: ['', Validators.compose([Validators.required])],
      country: ['', Validators.compose([Validators.required])],
      company : ['', Validators.compose([Validators.required])],
      headOffice : [false],
    });

    
    this.companyService.getAll().subscribe((data : Company[]) => {
      this.allCompanies = data;
    }); 
  }

  onSubmit(){   

    this.buildingService.createBuilding(this.form.value)
      .subscribe(data => { 
        this.toastr.success('Novi objekat uspešno kreiran!')  
        this.router.navigate(['admin']);
      },
        error => { 
          console.log('Adding building error');  
          this.toastr.error(error['error'].message)
        });

  }

}
