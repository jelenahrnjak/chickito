import OrderItem from "./order-item";

export default interface Order { 

    id: number;
    creationDate : string,
    price : number;
    author : string,
    reviewer : string,
    orderItems : OrderItem[], 
    approved : boolean,
    sector : string,
    
}