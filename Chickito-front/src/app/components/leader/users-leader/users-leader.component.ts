import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import User from '../../../model/user' 
import { UserService } from '../../../services/user.service';
import { ToastrService } from 'ngx-toastr';   

@Component({
  selector: 'app-users-leader',
  templateUrl: './users-leader.component.html',
  styleUrls: ['./users-leader.component.scss']
})
export class UsersLeaderComponent implements OnInit {


  termUser = ''

  showUsers = false;
  allUsers : User[] = [] 
  currentCompany : any = "" 
  
  constructor(
    private toastr: ToastrService,  
    private userService : UserService,
    private http: HttpClient
    ) { }

  ngOnInit(): void {
    this.getUsers() 
  } 
 

  getUsers(){
    this.userService.findAllWorkersByLeader().subscribe((data : User[]) => {
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
