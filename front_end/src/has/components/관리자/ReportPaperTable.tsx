import React, {Component} from 'react';
import {RecordingPaper} from "../Types/RecordingPaper";
import {Table} from "antd";
import {apollo_client} from "../../App";
import {gql} from "@apollo/client";
import {makeAutoObservable, makeObservable, observable} from "mobx";
import {observer} from "mobx-react";
import {createDecoratorAnnotation} from "mobx/dist/api/decorators";


class Store {
    dataSource: RecordingPaper[] = [];
    constructor() {
        makeAutoObservable(this);
    }
}

const store = new Store();

const columns = [
    {
        // key: "",
        title: "부서",
        dataIndex: "observeDepartment",
    },
    {
        // key: "",
        title: "직종",
        dataIndex: "observeOccupation",
    },{
        // key: "",
        title: "성명",
        dataIndex: "observeName",
    }

    ,{
        // key: "",
        title: "장소",
        dataIndex: "location",
    }

    ,{
        // key: "",
        title: "행위",
        dataIndex: "actionType",
    }

    ,{
        // key: "",
        title: "세부행위",
        dataIndex: "subAction",
    }



    ,{
        // key: "",
        title: "glove",
        dataIndex: "glove",
    }

    ,{
        // key: "",
        title: "passFail",
        dataIndex: "passFail",
    }

]

class ReportPaperTable extends Component<any,any> {



    constructor(props: any, context: any) {
        super(props, context);
        // makeObservable(this.dataSource,);
    }

    componentDidMount() {

        this.load();
    }

    render() {
        let {dataSource} = store;
        return (
            <div>
                <Table columns={columns} dataSource={dataSource}/>
            </div>
        );
    }

    private load() {
        apollo_client.query({
            query:gql`
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
                        
                    }
                }
            `
            ,fetchPolicy:"no-cache"
        }).then(value => {
            store.dataSource = value.data.recordingPapers;
        }).catch(reason => {

        })
    }
}


// observer()
export default observer(ReportPaperTable);
