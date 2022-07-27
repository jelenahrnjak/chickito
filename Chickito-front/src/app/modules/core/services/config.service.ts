import {Injectable} from '@angular/core'; 
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private _api_url = environment.baseUrl
  private _auth_url = this._api_url + '/api/auth';
  private _user_url = this._api_url + '/api/users';

  private _login_url = this._auth_url + '/login';

  get login_url(): string {
    return this._login_url;
  } 
 
  get user_url(): string {
    return this._user_url;
  } 
}
