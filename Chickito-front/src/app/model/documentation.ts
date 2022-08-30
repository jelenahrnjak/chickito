import SparePart from "./spare-part";

export default interface Documentation {

    id : any;
    workInstructions : string;
    washingInstructions : string; 
    maintenanceInstructions : string;
    spareParts : SparePart[];  
}