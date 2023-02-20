import React, {Component} from 'react';
import {ComTop} from "./ComTop";
import {store, T_Item} from "./Store";
import {observer} from 'mobx-react';
import {ComMiddle} from "./ComMiddle";
import {Button, Checkbox, DatePicker, Drawer, Form, Select, Space} from "antd";
import {css} from "styled-components/macro";
import {toJS} from "mobx";

import "./Table.css"
import ComSelectObserver from "./ComSelectObserver";


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
        </tr>
        </thead>
    );
}


const Row = (props: { item: T_Item,index:number, types: [{ name: string, type: string, recordValidValues: [{ name: string }] }] }) => {

    if (!props.types)
        return <></>


    return (
        <tr>
            <td><Button onClick={() => {
                store.openDrawer = true;
                store.curr = props.index;
                console.log('store.curr',store.curr)


            }}>관찰대상선택</Button></td>

            <td>부서</td>
            <td>직종</td>
            <td>성명</td>
            {props.types.map(value => {

                // <td>
                if (value.type == "bool")
                    return (
                        <td><Checkbox>{value.name}</Checkbox></td>
                    )

                if (value.type == "chronometer")
                    return (
                        <>
                            <td><input type={"number"}/></td>
                            <td><Button>시작</Button></td>
                        </>
                    )

                if (value.type == "inputText")
                    return (
                        <td><input type={"text"}/></td>
                    )


                let items = value.recordValidValues.map((value1: any) => {
                        return {value: value1.name, label: value1.name}
                    })
                ;
                if (value.type == "select") {
                    return (

                        <td>
                            {/*<span>{value.name}</span>*/}
                            <Select options={items}>{value.name}</Select>
                        </td>
                    )
                }

                return (
                    <tr>{value.name}미구현</tr>
                )
            })}
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
                        <Button>업로드</Button>
                    </Space>
                </div>
                <hr/>

                <div css={css`overflow: auto`}>
                    <table border={1} border-collapse={"collapse"}>
                        <HeadRow types={toJS(store.types)}/>
                        <tbody>
                        {store.data.map((item,index) =>
                            <Row types={toJS(store.types)} index={index} item={item}/>)}
                        </tbody>
                    </table>
                </div>
                <Drawer width={'80%'} open={store.openDrawer} onClose={() => store.openDrawer = false}>
                    <ComSelectObserver cb={(index, 부서, 직종, 성명) => {
                        store.data[index]!.properties!.name!.value = 성명;
                        store.data[index]!.properties!.occupation!.value = 직종;
                        store.data[index]!.properties!.department!.value = 부서;
                    }
                    }/>
                </Drawer>
            </div>
        );
    }
}


export default observer(ComRecord);
