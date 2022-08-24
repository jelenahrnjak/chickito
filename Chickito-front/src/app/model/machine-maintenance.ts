import MachineMaintenanceItem from './machine-maintenance-item'

export default interface MachineMaintenance {

    id : any;
    startDate : string;
    endDate : string;
    author : string
    sector : string
    items : MachineMaintenanceItem[];   
}