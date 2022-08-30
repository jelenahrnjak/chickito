import User from './user'

export default interface VcationRequest {

    id : any;
    requestExpirationDate : string; 
    startDate : string;
    endDate : string;
    user : User;
    approved : boolean;
    reasonForRejection : string;

}