import OrderItem from "./order-item";

export default interface Order { 

    id: any;
    creationDate : string,
    price : number;
    author : string,
    reviewer : string,
    orderItems : OrderItem[], 
    approved : boolean;
    
}