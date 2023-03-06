import React, {Component} from 'react';
import {Button, Checkbox, Radio, Select, Space} from "antd";
import { css } from 'styled-components/macro';

class ReportGenOption extends Component<any,any> {
    render() {
        let {columns} = this.props;
        return (
            <div css={css`margin-left: 1em;margin-bottom: 1em`}>
                <p>리포트생성 옵션</p>
                {columns
                    .filter((item:any)=>{
                        return item.dataIndex;
                    })
                    .filter((item:any)=>{
                        let validItems = new Set( [
                            '부서',
                            '직종',
                            '장소',
                            '행위',
                            '세부행위',
                            '장갑',
                        ]);

                        return validItems.has(item.title);

                    })
                    .map((item:any)=>{
                    return (
                        <Space><Checkbox>{item.title}</Checkbox></Space>
                    )
                })}

                <p>수행여부판단</p>
                <Radio.Group>
                    <Radio value={1}>수행여부</Radio>
                    <Radio value={2}>수행적합성</Radio>
                </Radio.Group>


                <Space><Button type={"primary"}>리포트 생성</Button>
                    <Button type={"primary"}>KONIS리포트 생성</Button></Space>

            </div>
        );
    }
}

export default ReportGenOption;
