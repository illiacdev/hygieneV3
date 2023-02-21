import React, {Component} from 'react';
import {ComTop} from "./ComTop";
import {store} from "./Store";
import {observer} from 'mobx-react';
import {ComMiddle} from "./ComMiddle";
import {Button, Checkbox, Drawer, Form, Input, Select, Space} from "antd";
import {css} from "styled-components/macro";
import {toJS} from "mobx";

import "./Table.css"
import ComSelectObserver from "./ComSelectObserver";
import {Combobox} from "react-widgets/cjs";
import "react-widgets/styles.css";
import {RecordingPaper} from "../Types/RecordingPaper";
import Optional from "optional-js";
import _ from "lodash";
import {Util} from "../../common/Util";


const HeadRow = (props: { types: [{ name: string, type: string, recordValidValues: [{ name: string }] }] }) => {
    return (
        <thead>
        <tr>
            <th>관찰대상선택</th>
            <th>부서</th>
            <th>직종</th>
            <th>성명</th>
            {props.types.map(value => {
                if (value.name === "수행시간")
                    return (
                        <>
                            <th>수행시간</th>
                            <th>수행시간</th>
                        </>
                    )

                return (<th>{value.name}</th>)
            })}
            <th>삭제</th>
        </tr>
        </thead>
    );
}

const map = new Map<string, string>([
    ["부서", "observeDepartment"],
    ["직종", "observeOccupation"],
    ["이름", "observeName"],
    ["장갑", "glove"],
    ["수행여부", "passFail"],
    ["장소", "location"],
    ["행위", "actionType"],
    ["세부행위", "subAction"],
])


function updateProp<TObj, K extends keyof TObj>(obj: TObj, key: K, value: TObj[K]) {
    obj[key] = value;
}

function getProp<TObj, K extends keyof TObj>(obj: TObj, key: K) {
    return obj[key];
}


const Row = (props: { data: RecordingPaper[]; item: RecordingPaper, index: number, types: [{ name: string, type: string, recordValidValues: [{ name: string }] }] }) => {

    if (!props.types)
        return <></>

    let {observeDepartment, observeOccupation, observeName, glove, passFail} = props.item;

    return (
        <tr>
            <td><Button onClick={() => {
                store.openDrawer = true;
                store.curr = props.index;
                console.log('store.curr', store.curr)


            }}>관찰대상선택</Button></td>

            <td>{observeDepartment}</td>
            <td>{observeOccupation}</td>
            <td>{observeName}</td>
            {props.types.map(value => {

                if (value.type == "bool") {
                    let onChange = (e: any) => {
                        let checked: boolean = e.target.checked;
                        Util.updateProp2(props.item, map.get(value.name)!, checked);
                        props.data[props.index] = _.cloneDeep(props.data[props.index]);
                    }

                    return Optional.ofNullable(map.get(value.name))
                        .map(fieldName => Util.getProp2(props.item, fieldName))
                        .map(value => <td align={"center"}><Checkbox  onChange={onChange} checked={value as boolean}/></td>)
                            .orElse(<p>{`${value.name}와(과) bind된 오브젝트 속성이 없습니다.`}</p>)
                }

                if (value.type == "chronometer")
                    return (
                        <>
                            <td><Input  type={"number"}/></td>
                            <td><Button>시작</Button></td>
                        </>
                    )


                if (value.type == "combobox")
                    return (
                        <>
                            <td><Combobox data={["Red", "Yellow", "Blue", "Orange"]}/></td>
                        </>
                    )

                if (value.type == "inputText") {

                    // return <p>{map.get(value.name)}</p>
                   /* let onChange = (e: any) => {
                        let e_value = e.target.value;
                        Util.updateProp2(props.item, map.get(value.name)!, e_value);
                        props.data[props.index] = _.cloneDeep(props.data[props.index]);
                    }

                    return Optional.ofNullable(map.get(value.name))
                        .map(fieldName => Util.getProp2(props.item, fieldName))
                        .map(value => <td><Input type={"text"} onChange={onChange} value={value as string}/></td>)
                        .orElse(<p>{`${value.name}와(과) bind된 오브젝트 속성이 없습니다.`}</p>)*/


                    type ObjectKey = keyof typeof props.item;
                    let orElse: string = Optional.ofNullable(map.get(value.name))
                        .map(name => name as ObjectKey)
                        .map(fieldName => store.data[props.index][fieldName])
                        .orElse("") as string;

                    return (
                        <td><Input type={"text"} value={orElse} onChange={v => {
                            console.log(v.target.value);
                            Optional.ofNullable(map.get(value.name))
                                .map(name => name as ObjectKey)
                                .ifPresent(value1 => {
                                    updateProp(props.data[props.index], value1, v.target.value);
                                    props.data[props.index] = _.cloneDeep(props.data[props.index]);
                                })
                        }
                        }/></td>
                    )
                }


                let items = value.recordValidValues.map((value1: any) => {
                        return {value: value1.name, label: value1.name}
                    })
                ;
                if (value.type == "select") {
                    type ObjectKey = keyof typeof props.item;
                    let orElse: string = Optional.ofNullable(map.get(value.name))
                        .map(name => name as ObjectKey)
                        // .map(fieldName => props.item[fieldName])
                        .map(fieldName => store.data[props.index][fieldName])
                        .orElse(true) as string;

                    return (

                        <td>
                            {/*<span>{value.name}</span>*/}
                            <Select value={orElse} onChange={value2 => {
                                // console.log(JSON.stringify(value1));

                                Optional.ofNullable(map.get(value.name))
                                    .map(name => name as ObjectKey)
                                    .ifPresent(value1 => {
                                        updateProp(props.data[props.index], value1, value2);
                                        props.data[props.index] = _.cloneDeep(props.data[props.index]);
                                    })

                            }
                            } options={items}>{value.name}</Select>
                        </td>
                    )
                    
                }

                return (
                    <tr>{value.name}미구현</tr>
                )
            })
            }
            <td><Button onClick={ ()=>store.delete(props.index)}>삭제</Button></td>
        </tr>
    )
}

    class ComRecord extends Component {

        form: any;


        componentDidMount() {
            // store.fetchTypes("세일병원");
            store.fetchTypes("성빈센트");
        }

        render() {
            return (
                <div css={css`padding: 1em`}>
                    <ComTop/>
                    <ComMiddle/>
                    <div css={css`display: flex;
                      flex-direction: row-reverse`}>
                        <Space>
                            <Form ref={ref => this.form = ref} initialValues={{addCount: 1}}>
                                <Form.Item name={"addCount"}>
                                    <Select options={[
                                        {value: 1, label: '1명'},
                                        {value: 2, label: '2명'},
                                        {value: 3, label: '3명'},
                                        {value: 4, label: '4명'},
                                        {value: 5, label: '5명'},
                                    ]}>

                                    </Select>
                                </Form.Item>
                            </Form>
                            <Button onClick={() => {
                                let item_count = this.form.getFieldValue("addCount");
                                console.log('fieldsValue', item_count);

                                console.log('onClick!');
                                store.add(item_count);

                            }}>관찰추가</Button>
                            <Button onClick={() => store.upload(store.data)}>업로드</Button>
                        </Space>
                    </div>
                    <hr/>

                    <div css={css`overflow: auto`}>
                        <table border={1} border-collapse={"collapse"}>
                            <HeadRow types={toJS(store.types)}/>
                            <tbody>
                            {store.data.map((item, index) =>
                                <Row data={store.data} types={toJS(store.types)} index={index} item={item}/>)}
                            </tbody>
                        </table>
                    </div>
                    <Drawer width={'80%'} open={store.openDrawer} onClose={() => store.openDrawer = false}>
                        <ComSelectObserver row_index={store.curr!} cb={(index, 부서, 직종, 성명) => {
                            let tItem = store.data[index] as RecordingPaper;
                            tItem!.observeName = 성명;
                            tItem!.observeDepartment = 부서;
                            tItem!.observeOccupation = 직종;
                            console.log(toJS(tItem));
                            store.openDrawer = false;
                        }
                        }/>
                    </Drawer>
                </div>
            );
        }
    }


    export default observer(ComRecord);
