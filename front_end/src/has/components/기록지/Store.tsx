import {makeAutoObservable} from 'mobx'
import {} from 'mobx-react'
import {Dayjs} from "dayjs";
import {apollo_client} from "../../App";
import {gql} from "@apollo/client";
import {types} from "util";
import {RecordingPaper} from "../Types/RecordingPaper";
import {List} from "immutable";
import Optional from "optional-js";


class Store {
    data: RecordingPaper[] = [new RecordingPaper()];

    types: any = [];
    // types:
    openDrawer = false;
    curr: number | null = null;

    upload(input: RecordingPaper[]) {
        let input_normalized = input.map(item => {
            Optional.ofNullable(item.actionStartTime).ifPresent(value => {
                item.actionStartTime = (value as Dayjs).format("YYYY-MM-DDTHH:mm:ss");
            });
            // item.actionEndTime = (item.actionEndTime as Dayjs).format("YYYY-MM-DDTHH:mm:ss");
            delete item.chronometer_state;
            return item;
        });
        apollo_client.mutate({
            mutation: gql`
                mutation createRecordingPapers($input:[IrecordingPaper]){
                    createRecordingPapers(input:$input){
                        action
                    }

                }
            `,
            variables: {
                input: input_normalized
            }

        }).then(value => {
            this.data = [];
        }).catch(reason => {

            console.log(reason);
        })
    };


    add(item_count: number) {
        for (let i = 0; i < item_count; i++)
            // this.data.unshift({properties: {action: {type: "접촉전"}}})
            this.data.unshift(new RecordingPaper())


    };

    fetchTypes(templateName: string) {
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
            , variables: {
                templateName
            }
        }).then(value => {
            let {recordTypesTemplate} = value.data;
            // let recordTypes1: T_inputForm = recordTypes;
            // let type = recordTypes1.type;
            console.log('recordTypesTemplate', recordTypesTemplate);
            this.types = recordTypesTemplate;

        }).catch(reason => {
            console.log(reason);
        })

    }

    onSelectRow(index: number) {

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
