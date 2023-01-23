import { service } from "./service";

export interface myreservation {
    accommodatingUnit:string,
    start:string,
    end:string,
    services:service[],
}