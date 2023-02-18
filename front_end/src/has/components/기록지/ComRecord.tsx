import React, {Component} from 'react';
import {ComTop} from "./ComTop";
import {store, T_Item} from "./Store";
import {observer} from 'mobx-react';
import {ComMiddle} from "./ComMiddle";
import {Button, Select, Space} from "antd";
import {css} from "styled-components/macro";


const Row = (props: { item: T_Item }) => {
    return (
        <div>
            Row!
        </div>
    )
}

class ComRecord extends Component {
    render() {
        return (
            <div css={css`padding: 1em`}>
                <ComTop/>
                <ComMiddle/>
                <div css={css`display: flex;flex-direction: row-reverse`}>
                    <Space>
                        <Select defaultValue={1} options={[
                        {value: 1, label: '1명'},
                        {value: 2, label: '2명'},
                        {value: 3, label: '3명'},
                        {value: 4, label: '4명'},
                        {value: 5, label: '5명'},
                    ]}>

                    </Select>
                        <Button onClick={store.add}>관찰추가</Button>
                        <Button>업로드</Button>
                    </Space>
                </div>
                <hr/>
                {store.date.map(item => <Row item={item}/>)}
            </div>
        );
    }
}


export default observer(ComRecord);
