import {makeAutoObservable} from 'mobx'
import {} from 'mobx-react'
import {Dayjs} from "dayjs";
import {apollo_client} from "../../App";
import {gql} from "@apollo/client";
import {types} from "util";
import {RecordingPaper} from "../Types/RecordingPaper";
import {List} from "immutable";






class Store {
    data: RecordingPaper[] = [];

    types: any = [];
    // types:
    openDrawer = false;
    curr: number | null = null;
    upload(input:RecordingPaper[]){
        apollo_client.mutate({
            mutation:gql`
                mutation createRecordingPapers($input:[IrecordingPaper]){
                    createRecordingPapers(input:$input){
                        action
                    }
                    
                }
            `,
            variables:{
                input
            }

        }).then(value => {
            this.data = [];
        }).catch(reason => {

        })
    };


    add(item_count: number) {
        for (let i = 0; i < item_count; i++)
            // this.data.unshift({properties: {action: {type: "접촉전"}}})
            this.data.unshift(new RecordingPaper())


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

    delete(index: number) {
        console.log(index);
        let list = List(this.data);
        let list1 = list.delete(index);
        this.data = list1.toArray();

    }
}

export const store = new Store();
