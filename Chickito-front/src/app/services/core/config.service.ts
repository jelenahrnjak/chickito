import {Injectable} from '@angular/core'; 
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private _api_url = environment.baseUrl
  private _auth_url = this._api_url + '/api/auth';
  private _user_url = this._api_url + '/api/users';
  private _company_url = this._api_url + '/api/companies';
  private _building_url = this._api_url + '/api/buildings';
  private _sector_url = this._api_url + '/api/sectors';
  private _order_url = this._api_url + '/api/orders';
  private _machineMaintenace_url = this._api_url + '/api/machineMaintenances';
  private _machine_url = this._api_url + '/api/machines';
  private _workerOnMachines_url = this._api_url + '/api/workerOnMachines';
  private _vacationRequests_url = this._api_url + '/api/vacation-requests';

  private _login_url = this._auth_url + '/login';

  get login_url(): string {
    return this._login_url;
  } 
 
  get user_url(): string {
    return this._user_url;
  }  

  get company_url(): string {
    return this._company_url;
  } 

  get building_url(): string {
    return this._building_url;
  } 

  get sector_url(): string {
    return this._sector_url;
  } 

  get order_url(): string {
    return this._order_url;
  } 

  get machine_url(): string {
    return this._machine_url;
  } 

  get machineMaintenance_url(): string {
    return this._machineMaintenace_url;
  } 

  get workerOnMachines_url(): string {
    return this._workerOnMachines_url;
  } 

  get vacationRequests_url(): string {
    return this._vacationRequests_url;
  } 
}
