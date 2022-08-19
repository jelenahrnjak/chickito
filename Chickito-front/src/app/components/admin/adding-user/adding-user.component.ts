import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService, } from '../../../services/user.service';  
import { ToastrService } from 'ngx-toastr';  
import { CompanyService } from '../../../services/company.service';
import { SectorService } from '../../../services/sector.service';
import Company from '../../../model/company';
import Sector from '../../../model/sector';

@Component({
  selector: 'app-adding-user',
  templateUrl: './adding-user.component.html',
  styleUrls: ['./adding-user.component.scss']
})
export class AddingUserComponent implements OnInit {
 
  form!: FormGroup; 
  allCompanies : Company[] = [] 
  passRegex =  new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-+_!@#$%^&*.,?:;<>=`~\\]\x22\x27\(\)\{\}\|\/\[\\\\?]).{8,}$')
  
  constructor( 
    private toastr: ToastrService, 
    private userService: UserService,
    private router: Router, 
    private formBuilder: FormBuilder,
    private companyService: CompanyService,
    private sectorService: SectorService,) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      companyId : ['', Validators.compose([Validators.required])],
      username: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.maxLength(32)])],
      repeatedPassword: ['', Validators.compose([Validators.required,Validators.maxLength(32)])],
      firstName: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      lastName: ['', Validators.compose([Validators.required, Validators.maxLength(64)])],
      email: ['', Validators.compose([Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
      phoneNumber: ['', Validators.compose([Validators.required])],
      role : ['', Validators.compose([Validators.required])],
      gender : ['', Validators.compose([Validators.required])],
      sector : [''],
    }); 
    
    this.companyService.getAll().subscribe((data : Company[]) => {
      this.allCompanies = data;
    }); 
  } 
 
  checkLeader() : string{
    const companyId = this.form.get('companyId')?.value 
    const sector = this.form.get('sector')?.value  
    this.sectorService.findByCompanyAndType(companyId, sector).subscribe((data : Sector) => {
      return data.leader;
    }); 

    return ''
  }

  onSubmit(){  

    const pass = this.form.get('password')?.value

    if(!pass.match(this.passRegex)){
      this.toastr.error('Lozinka mora da sadži najmanje 8 karaktera, malo, veliko slovo, cifru i specijalni karakter.')
      return

    }
    
    if(pass != this.form.get('repeatedPassword')?.value){
      this.toastr.error('Lozinke se ne poklapaju')
      return
    }
 
    if(this.form.get('role')?.value != '2' && !this.form.get('sector')?.value){
      this.toastr.error('Izaberite sektor.')
      return

    }

    if(this.form.get('role')?.value == '3'){
      const companyId = this.form.get('companyId')?.value 
      const sector = this.form.get('sector')?.value  
      this.sectorService.findByCompanyAndType(companyId, sector).subscribe((data : Sector) => {
        const leader = data.leader;
        
        if(leader != null){
          this.toastr.error('Rukovodilac sektora već postoji. Trenutni rukovodilac je ' + leader + ".")
          return

        }else{
          this.create()
        }
      }); 
    }else{
      this.create()
    } 
  }

  create(){
    
    this.userService.createUser(this.form.value)
      .subscribe(data => { 
        this.toastr.success('Novi zaposleni uspešno kreiran!')  
        this.router.navigate(['admin']);
      },
        error => { 
          console.log('Adding user error');  
          this.toastr.error(error['error'].message)
        });
  }
}
