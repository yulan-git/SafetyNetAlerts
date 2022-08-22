import { AddressI } from "../interfaces/address-i";
import { PersonI } from "../interfaces/person-i";
import { PersonIdI } from "../interfaces/person-id-i";

export class Person implements PersonI{
    personId!: PersonIdI;
    firstName!: string;
    birthday!: Date;
    email!: string;
    password!: string;
    address!: AddressI;
    medicationsList!: string[];
    allergiesList!: string[];

    constructor(obj?: Partial<Person>) {
        if (obj) {
            Object.assign(this, obj);
        }
    }
}
