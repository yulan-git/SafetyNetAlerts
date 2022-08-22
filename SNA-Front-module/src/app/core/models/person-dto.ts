import { AddressI } from "../interfaces/address-i";
import { PersonIdI } from "../interfaces/person-id-i";
import { Person } from "./person";

export class PersonDto {
    lastName!: string;
    firstName!: string;
    phone!: string;
    email!: string;
    password!: string;
    address!: AddressI;

    constructor(obj?: Partial<Person>) {
        if (obj) {
            Object.assign(this, obj);
        }
    }
}
