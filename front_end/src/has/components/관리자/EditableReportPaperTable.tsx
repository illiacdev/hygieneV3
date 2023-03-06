import React, {Component} from 'react';
import {gql} from "@apollo/client";
import {Button, DatePicker, Form, Input, InputNumber, Table, Typography} from "antd";
import {observer} from "mobx-react";
import {toJS} from "mobx";
import {store} from './Store'
import {RecordingPaper} from "../Types/RecordingPaper";
import _조직도업로드 from "./설정/_조직도업로드";
import ReportGenOption from "./ReportGenOption";

/*
interface Item {
    key: string;
    id: string;
    ingredient: {
        name: string
    }
    unit_name: string
    qty: number
    store_name: string
    description: string
    date: string

}*/

class Item extends RecordingPaper {
    key?: string;


}


interface EditableCellProps extends React.HTMLAttributes<HTMLElement> {
    editing: boolean;
    dataIndex: string;
    title: any;
    inputType: 'number' | 'text';
    record: Item;
    index: number;
    children: React.ReactNode;
}


const EditableCell: React.FC<EditableCellProps> = ({
                                                       editing,
                                                       dataIndex,
                                                       title,
                                                       inputType,
                                                       record,
                                                       index,
                                                       children,
                                                       ...restProps
                                                   }) => {
    const inputNode = inputType === 'number' ? <InputNumber/> : <Input/>;

    return (
        <td {...restProps}>
            {editing ? (
                <Form.Item
                    name={dataIndex}
                    style={{margin: 0}}
                    /*rules={[
                        {
                            required: true,
                            message: `Please Input ${title}!`,
                        },
                    ]}*/
                >
                    {inputNode}
                </Form.Item>
            ) : (
                children
            )}
        </td>
    );
};


class EditableReportPaperTable extends Component {
    state = {editingKey: '', dataSource: [], dataSource2: []}

    form: any;
    columns = [
        {
            // key: "",
            title: "부서",
            dataIndex: "observeDepartment",
            editable: true,

        },
        {
            // key: "",
            title: "직종",
            dataIndex: "observeOccupation",
            editable: true,
        }, {
            // key: "",
            title: "성명",
            dataIndex: "observeName",
            editable: true,
        }

        , {
            // key: "",
            title: "장소",
            dataIndex: "location",
            editable: true,
        }

        , {
            // key: "",
            title: "행위",
            dataIndex: "actionType",
            editable: true,
        }

        , {
            // key: "",
            title: "세부행위",
            dataIndex: "subAction",
            editable: true,
        }

        , {
            // key: "",
            title: "장갑",
            dataIndex: "glove",
            render: (item: any) => <span>{JSON.stringify(item)}</span>,
            editable: true,
        }

        , {
            // key: "",
            title: "수행시간",
            dataIndex: "actionDuration",
            // render:(item:any)=><span>{JSON.stringify(item)}</span>
            editable: true,
        }
        , {
            // key: "",
            title: "수행여부",
            dataIndex: "actionType",
            // render:(item:any)=><span>{JSON.stringify(item)}</span>
            editable: true,
        }
        , {
            // key: "",
            title: "수행적합성",
            dataIndex: "passFail",
            render: (item: any) => <span>{JSON.stringify(item)}</span>,
            editable: true,
        }
        ,
        {
            title: 'operation',
            // dataIndex: 'operation',
            render: (_: any, record: Item) => {
                let editable = this.isEditing(record);

                let that = this;

                console.log("TAGeditable", editable)

                function edit(record: Item) {
                    let test = {...that.state, editingKey: record.key}
                    // console.log("TAG_EDIT", JSON.stringify(test));
                    // console.log("TAG_EDIT", JSON.stringify(record));

                    that.setState({...that.state, editingKey: record.key}, () => {
                        console.log("TAG_EDIT", JSON.stringify(that.state));
                    })
                    that.form.setFieldsValue({...record});


                }

                function cancel() {
                    that.setState({...that.state, editingKey: ''})
                }

                function save(item: Item) {
                    console.log('save', item);
                    store.save(item);
                    // store.updateIngredientWarehousingSimple(item);
                }

                console.log("TAG", "render oper!!!");

                return editable ? (
                    <span>
                        {/*<p>{JSON.stringify(record)}</p>*/}
                        <Typography.Link onClick={() => {
                            let fielsdsValue: Item = that.form.getFieldsValue();
                            fielsdsValue.id = record.id;
                            save(fielsdsValue);
                            cancel();
                        }} style={{marginRight: 8}}>
              Save
            </Typography.Link>
                        <Button onClick={cancel}>취소</Button>
                        {/*<Popconfirm title="Sure to cancel?" onConfirm={cancel}>
              <a>Cancel</a>
            </Popconfirm>*/}
          </span>
                ) : (
                    /*<Typography.Link disabled={editingKey !== ''} onClick={() => edit(record)}>
                        Edit
                    </Typography.Link>*/

                    <Button disabled={this.state.editingKey !== ''} onClick={() => {
                        console.log("TAG3", "OnClick");
                        edit(record);
                    }}>
                        Edit
                    </Button>

                );
            },
        }
        , {
            // key: "",
            title: "",
            dataIndex: "id",
            render: (item: any) => <Button onClick={() => store.delete(item)}>삭제</Button>
        }

    ]
    ;

    isEditing = (record: Item) => {
        console.log("TAGisEditing key 1", record.key)
        console.log("TAGisEditing key 2", this.state.editingKey)
        return record.key === this.state.editingKey;
    };


    mergedColumns = this.columns.map((col) => {
        let {isEditing} = this;
        // console.log("TAG1", JSON.stringify(col));
        if (!col.editable) {
            return col;
        }
        console.log("TAG2", "editable!")
        return {
            ...col,
            onCell: (record: Item) => ({
                record,
                inputType: col.dataIndex === 'actionDuration' ? 'number' : 'text',
                dataIndex: col.dataIndex,
                title: col.title,
                editing: isEditing(record),
                // editing: true,
            }),
        };
    });

    componentDidMount() {
        store.load();
    }

    render() {
        return (
            <div>
                {/* <Button onClick={event => {
                    this.fetch_dataSource()
                }
                }>로드</Button>*/}
                <_조직도업로드/>


                <ReportGenOption columns={this.columns}/>
                <Form ref={ref => this.form = ref}>
                    <Form.Item label={"조회기간"}>
                        <DatePicker.RangePicker/>
                    </Form.Item>
                    <Table
                        components={{
                            body: {
                                cell: EditableCell,
                            },
                        }}
                        columns={this.mergedColumns} dataSource={toJS(store.dataSource)}/>
                </Form>
            </div>
        );
    }
}

// observer()
export default observer(EditableReportPaperTable);
