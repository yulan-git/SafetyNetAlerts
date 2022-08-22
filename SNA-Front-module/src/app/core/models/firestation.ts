import { FirestationI } from "../interfaces/firestation-i";
import { Address } from "./address";

export class Firestation implements FirestationI{
    station!: number;
    addressList!: Address[];

    constructor(obj?: Partial<Firestation>) {
        if (obj) {
            Object.assign(this, obj);
        }
    }
}
