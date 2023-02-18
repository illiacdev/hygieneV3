import {makeAutoObservable} from 'mobx'
import {} from 'mobx-react'
import {Dayjs} from "dayjs";

export type T_Item = {
    "$schema"?: "https://json-schema.org/draft/2020-12/schema",
    "type"?: "object",
    "properties": {
        "action"?: {
            "type": string
        },
        "actionEndTime"?: {
            "type": Dayjs
        },
        "actionStartTime"?: {
            "type": Dayjs
        },
        "actionType"?: {
            "type": string
        },
        "department"?: {
            "type": string
        },
        "glove"?: {
            "type": string
        },
        "id"?: {
            "type": number
        },
        "location"?: {
            "type": string
        },
        "name"?: {
            "type": string
        },
        "observer"?: {
            "type": string
        },
        "occupation"?: {
            "type": string
        },
        "passFail"?: {
            "type": string
        },
        "subAction"?: {
            "type": string
        }
    }
};


class Store {
    data: T_Item[] = [];
    add() {
        this.data.unshift({properties:{action:{type:"손씻기"}}})
    };

    constructor() {
        makeAutoObservable(this);
    }

}

export const store = new Store();
