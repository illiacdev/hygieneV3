import {makeAutoObservable} from 'mobx'
import {} from 'mobx-react'
import {Dayjs} from "dayjs";
import {apollo_client} from "../../App";
import {gql} from "@apollo/client";
import {types} from "util";

export type T_Item = {
    "$schema"?: "https://json-schema.org/draft/2020-12/schema",
    "type"?: "object",
    "properties": {
        action: {
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

    types: any = [];
    // types:
    openDrawer = false;


    add(item_count: number) {
        for (let i = 0; i < item_count; i++)
            this.data.unshift({properties: {action: {type: "접촉전"}}})
    };

    fetchTypes(templateName:string) {
        apollo_client.query({
            query: gql`
                query recordTypesTemplate($templateName:String) {
                    recordTypesTemplate(templateName:$templateName){
                        name
                        type
                        recordValidValues{
                            name
                        }
                    }
                }
            `,variables:{
                templateName
            }
        }).then(value => {
            let {recordTypesTemplate} = value.data;
            // let recordTypes1: T_inputForm = recordTypes;
            // let type = recordTypes1.type;
            console.log('recordTypesTemplate',recordTypesTemplate);
            this.types = recordTypesTemplate;

        }).catch(reason => {
            console.log(reason);
        })

    }

    constructor() {
        makeAutoObservable(this);
    }

}

export const store = new Store();
