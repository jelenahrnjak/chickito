import { Component, OnInit } from '@angular/core';
import Company from '../../../model/company'
import User from '../../../model/user'
import { CompanyService } from '../../../services/company.service';
import { UserService } from '../../../services/user.service';
import { ToastrService } from 'ngx-toastr';   

@Component({
  selector: 'app-users-view',
  templateUrl: './users-view.component.html',
  styleUrls: ['./users-view.component.scss']
})
export class UsersViewComponent implements OnInit {



  allCompanies : Company[] = [] 
  showUsers = false;
  allUsers : User[] = [] 
  currentCompany : any = "" 
  
  constructor(
    private toastr: ToastrService, 
    private companyService: CompanyService,
    private userService : UserService,
    ) { }

  ngOnInit(): void {
    this.reset()
  }

  reset(){
    this.allCompanies = []
    this.getAllCompanies();
    this.showUsers = false
    this.allUsers = []
  }

  selectCompany(company){
    this.currentCompany = name
    this.currentCompany = company;
    this.showUsers = true  
    this.getUsers(company.id)
  }

  getUsers(id: any){
    this.userService.findAllByCompany(id).subscribe((data : User[]) => {
      this.allUsers = data.sort((a, b) => a.role.localeCompare(b.role)); 
    }); 

  }

  getAllCompanies(){
    
    this.companyService.getAll().subscribe((data : Company[]) => {
      this.allCompanies = data;
    }); 
  }

  // deleteCompany(id : any, name: string){
  //   if (confirm('Da li ste sigurn da želite da obrišete ' + name + '?')) { //TODO modal 
  //     this.companyService.deleteCompany(id).subscribe(data => { 
  //       this.reset()
  //       this.toastr.success('Preduzeće uspešno obrisano!')  
  //     }); 
  //   }
  // }

  deleteUser(user){ 
    if (confirm('Da li ste sigurni da želite da obrišete korisnika ' + user.fullName + '?')) { //TODO modal 
      this.userService.deleteUser(user.id).subscribe(data => { 
        this.reset()
        this.selectCompany(this.currentCompany)
        this.toastr.success('Korisnik uspešno obrisan!')  
      }); 
    }
  }  
}
