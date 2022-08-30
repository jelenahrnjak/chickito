import { Component, OnInit } from '@angular/core';
import User from '../../../model/user' 
import { UserService } from '../../../services/user.service';
import { ToastrService } from 'ngx-toastr';   

@Component({
  selector: 'app-users-director',
  templateUrl: './users-director.component.html',
  styleUrls: ['./users-director.component.scss']
})
export class UsersDirectorComponent implements OnInit {


  termUser = ''

  showUsers = false;
  allUsers : User[] = [] 
  currentCompany : any = "" 
  
  constructor(
    private toastr: ToastrService,  
    private userService : UserService, 
    ) { }

  ngOnInit(): void {
    this.getUsers() 
  } 
 

  getUsers(){
    this.userService.findAllByDirector().subscribe((data : User[]) => {
      this.allUsers = data.sort((a, b) => a.fullName.localeCompare(b.fullName)); 
    }); 

  } 

  deleteUser(user){ 
    if (confirm('Da li ste sigurni da želite da obrišete korisnika ' + user.fullName + '?')) { //TODO modal 
      this.userService.deleteUser(user.id).subscribe(data => { 
        this.getUsers() 
        this.toastr.success('Korisnik uspešno obrisan!')  
      }); 
    }
  }  
}