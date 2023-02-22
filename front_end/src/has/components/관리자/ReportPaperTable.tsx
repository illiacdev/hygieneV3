import React, {Component} from 'react';
import {Button, Table} from "antd";
import {observer} from "mobx-react";
import {store} from "./Store";




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
        title: "장갑",
        dataIndex: "glove",
        render:(item:any)=><span>{JSON.stringify(item)}</span>
    }

    ,{
        // key: "",
        title: "수행시간",
        dataIndex: "actionDuration",
        render:(item:any)=><span>{JSON.stringify(item)}</span>
    }
    ,{
        // key: "",
        title: "수행여부",
        dataIndex: "actionType",
        // render:(item:any)=><span>{JSON.stringify(item)}</span>
    }
    ,{
        // key: "",
        title: "수행적합성",
        dataIndex: "passFail",
        render:(item:any)=><span>{JSON.stringify(item)}</span>
    }



    ,{
        // key: "",
        title: "",
        dataIndex: "id",
        render:(item:any)=><Button onClick={()=>store.delete(item)} >삭제</Button>
    }

]

class ReportPaperTable extends Component<any,any> {



    constructor(props: any, context: any) {
        super(props, context);
        // makeObservable(this.dataSource,);
    }

    componentDidMount() {

        store.load();
    }

    render() {
        let {dataSource} = store;
        return (
            <div>
                <Table columns={columns} dataSource={dataSource}/>
            </div>
        );
    }

}


// observer()
export default observer(ReportPaperTable);
