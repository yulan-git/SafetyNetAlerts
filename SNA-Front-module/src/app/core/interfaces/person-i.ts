import { AddressI } from "./address-i";
import { PersonIdI } from "./person-id-i";

export interface PersonI {
    "personId": PersonIdI,
    "firstName": string;
    "birthday": Date,
    "email": string,
    "password": string,
    "address": AddressI,
    "medicationsList": string[],
    "allergiesList": string[]
}
