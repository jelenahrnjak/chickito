import SparePart from "./spare-part";

export default interface Documentation {  
    workInstructions : string;
    washingInstructions : string; 
    maintenanceInstructions : string;
    spareParts : SparePart[];  
}