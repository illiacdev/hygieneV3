import {RecordingPaper} from "../Types/RecordingPaper";
import {makeAutoObservable} from "mobx";
import {apollo_client} from "../../App";
import {gql} from "@apollo/client";

class Store {
    dataSource: RecordingPaper[] = [];

    constructor() {
        makeAutoObservable(this);
    }

    load() {
        apollo_client.query({
            query: gql`
                query {
                    recordingPapers {
                        id
                        action
                        observeDepartment
                        observeOccupation
                        observeName
                        glove
                        passFail
                        location
                        actionType
                        subAction
                        actionDuration


                    }
                }
            `
            , fetchPolicy: "no-cache"
        }).then(value => {

            this.dataSource = (value.data.recordingPapers as RecordingPaper[])
                .map(value1 => {
                    value1.key = value1.id;
                    return value1;
                });

            // let s = JSON.stringify(value.data.recordingPapers);
            // console.log(s);
        }).catch(reason => {

        })
    }


    delete(id: any) {
        apollo_client.mutate({
            mutation: gql`
                mutation deleteRecordingPaper($id:ID){
                    deleteRecordingPaper(id: $id)
                }
            `,
            variables: {
                id

            }
            ,
            fetchPolicy: "no-cache"

        }).then(value => {
            console.log(value.data.deleteRecordingPaper);
            this.load();
        }).catch(reason => {

        })
    }

    save(item: RecordingPaper) {
        let id = item.id;
        delete item.id;
        apollo_client.mutate({
            mutation: gql`
                mutation deleteRecordingPaper($id:ID!,$input:IrecordingPaper!){
                    updateRecordingPaper(id: $id,input: $input){
                        id
                    }
                }
            `,
            variables: {
                id,
                input:item
            }
            ,
            fetchPolicy: "no-cache"

        }).then(value => {
            console.log(value.data.deleteRecordingPaper);
            this.load();
        }).catch(reason => {
            console.log(reason);
        })
    }
}

export const store = new Store();
