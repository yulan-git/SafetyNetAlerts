import { AddressI } from "../interfaces/address-i";

export class Address implements AddressI {
    addressId!: number;
    address!: string;
    city!: string;
    zip!: string;

    constructor(obj?: Partial<Address>) {
        if (obj) {
            Object.assign(this, obj);
        }
    }
}
