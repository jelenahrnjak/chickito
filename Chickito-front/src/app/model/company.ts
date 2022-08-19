import Sector from './sector'

export default interface Company {

    id : number;
    name : string;
    pib : string;
    establishmentDate : string;
    headOfficeId : number;
    headOfficeAddress : string;
    sectors : Sector[];
}