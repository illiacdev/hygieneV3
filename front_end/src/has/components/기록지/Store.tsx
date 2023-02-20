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
            "type": string;
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
            "type": string;
            value: string;

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
            "type": string;
            value: string;
        },
        "observer"?: {
            "type": string;
            value: string;
        },
        "occupation"?: {
            "type": string;
            value: string;
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
    curr: number | null = null;


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
                },
            `,
            fetchPolicy: "no-cache"
            ,variables:{
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

    onSelectRow(index:number){

    }
    constructor() {
        makeAutoObservable(this);
    }

}

export const store = new Store();
