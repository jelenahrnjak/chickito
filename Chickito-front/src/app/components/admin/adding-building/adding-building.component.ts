import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BuildingService } from '../../../services/building.service';  
import { ToastrService } from 'ngx-toastr';  
import Company from '../../../model/company'
import { CompanyService } from '../../../services/company.service';
import { MapsAPILoader, MouseEvent } from '@agm/core';

@Component({
  selector: 'app-adding-building',
  templateUrl: './adding-building.component.html',
  styleUrls: ['./adding-building.component.scss']
})
export class AddingBuildingComponent implements OnInit {

  form: FormGroup = this.formBuilder.group({
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
  allCompanies : Company[] = []
  selectAddress = true; 
  display = "none"; 
  zoom : number = 6;
  address : string = '' 
  lat = 46.09915226260574;
  lng = 19.659298920686695;
  constructor( 
    private toastr: ToastrService, 
    private buildingService: BuildingService,
    private companyService: CompanyService,
    private router: Router, 
    private formBuilder: FormBuilder,) {}

  ngOnInit(): void {
 
    
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
    
    if(this.form.get('headOffice')?.value == true && this.checkHeadOffice() !== ''){
      this.display = "block" 
      return
    } 

    this.create()
  }

  create(){
    this.display = "none";

    var long = ''
    var lat = ''

    if(this.selectAddress){
      long =  this.form.get('longitude')?.value
      lat = this.form.get('latitude')?.value
    }

    const building = {
      'companyId' : this.form.get('companyId')?.value,
      'address' : {
        'street' : this.form.get('street')?.value,
        'number' : this.form.get('number')?.value,
        'longitude' : long,
        'latitude' : lat,
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
  
  markerDragEnd($event: MouseEvent) {
    console.log($event.coords.lat)
    console.log($event.coords.lng)

    
    this.form.get('postalCode')?.setValue("24300")
    this.form.get('latitude')?.setValue($event.coords.lat)
    this.form.get('longitude')?.setValue($event.coords.lng)  
    
    this.getAddress($event.coords.lat, $event.coords.lng);
  }


  getAddress(latitude, longitude) {
    
    var geocoder = new google.maps.Geocoder();

    geocoder.geocode({ 'location': { lat: latitude, lng: longitude } }, (results, status) => {
      
      if (status === 'OK') {
        if (results[0]) {
          this.zoom = 6;
          this.address = results[0].formatted_address;
          console.log(results[0])
          if(results[0].address_components.filter(ac=>~ac.types.indexOf('locality')) != undefined){
            this.form.get('city')?.setValue(results[0].address_components.filter(ac=>~ac.types.indexOf('locality'))[0].long_name) 
          }

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('country')) != undefined){
            this.form.get('country')?.setValue(results[0].address_components.filter(ac=>~ac.types.indexOf('country'))[0].long_name)  
          }

          if( results[0].address_components.filter(ac=>~ac.types.indexOf('street_number')) != undefined){
            this.form.get('number')?.setValue(results[0].address_components.filter(ac=>~ac.types.indexOf('street_number'))[0].long_name) 
          }

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('route')) != undefined){
            this.form.get('street')?.setValue(results[0].address_components.filter(ac=>~ac.types.indexOf('route'))[0].long_name)  
          }

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('postal_code')) != undefined){ 
            this.form.get('postalCode')?.setValue(results[0].address_components.filter(ac=>~ac.types.indexOf('postal_code'))[0].long_name)  
          }

        } else {
          this.toastr.error('Došlo je do greške pri dobavljanju izabrane adrese')  
        }
      } else {
        
        this.toastr.error('Došlo je do greške pri dobavljanju izabrane adrese')  
      }

    });

    console.log(this.address)
  }

  resetAddress(){
    
    this.form.get('postalCode')?.setValue("")
    this.form.get('latitude')?.setValue(this.lat)
    this.form.get('longitude')?.setValue(this.lng)  
    this.form.get('city')?.setValue("")
    this.form.get('country')?.setValue("")
    this.form.get('street')?.setValue("")
    this.form.get('number')?.setValue("")
    this.address = ""

  } 
}
