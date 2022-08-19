import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CompanyService } from '../../../services/company.service'; 
import { Subject } from 'rxjs/Subject'; 
import { ToastrService } from 'ngx-toastr';  

@Component({
  selector: 'app-adding-company',
  templateUrl: './adding-company.component.html',
  styleUrls: ['./adding-company.component.scss']
})
export class AddingCompanyComponent implements OnInit {



  form!: FormGroup;   

  constructor( 
    private toastr: ToastrService, 
    private companyService: CompanyService,
    private router: Router, 
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      pib: ['', Validators.compose([Validators.required, Validators.pattern('^[0-9]+')])],
    });
  }

  onSubmit(){   

    this.companyService.createCompany(this.form.value)
      .subscribe(data => { 

        this.toastr.success('Novo preduzeće uspešno kreirano!')  
        this.router.navigate(['admin']);
      
      },
        error => { 
          console.log('Adding company error');  
          this.toastr.error(error['error'].message)
        });

  }

}
