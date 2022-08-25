import MachineMaintenanceItem from './machine-maintenance-item'

export default interface MachineMaintenance {

    id : any;
    year : number; 
    author : string
    sector : string
    items : MachineMaintenanceItem[];   
}