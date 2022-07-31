import Address from "./address";

export default interface Building {

    id : number;
    companyName : string; 
    address : Address;
    headOffice : boolean;
}