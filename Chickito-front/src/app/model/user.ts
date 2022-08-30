export default interface User {

    id : number;
    companyId : number;
    username : string; 
    password : string;
    firstName : string;
    lastName : string;
    email : string;
    phoneNumber : string; 
    role : string;
    gender : any;
    fullName : string;
    sector : string;
    mainWorker : boolean;
    availableVacationDays : number;
    vacationDays : Date[];
}