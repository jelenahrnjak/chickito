import MachineMaintenanceItem from './machine-maintenance-item'

export default interface MachineMaintenance {

    id : any;
    creationDate : string;
    author : string
    items : MachineMaintenanceItem[];   
}