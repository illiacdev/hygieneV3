import React, {Component} from 'react';
import _조직도업로드 from "../설정/_조직도업로드";
import ReportGenOption from "./ReportGenOption";
import {Button, Checkbox, Radio, Space} from "antd";
import {css} from "styled-components/macro";
import styled from "styled-components";


const StyledDiv = styled.div`
  border: #282c34 solid 1px;
  //width: 200px;
  // height: 100px;
  padding: 1em;
  ${p => p.css}

`

class Top extends Component<{ columns: any }, any> {
    render() {
        let {columns} = this.props;
        return (
            <div css={css`display: grid;
              grid-template-columns: 1fr 1fr`}>
                <StyledDiv>
                    <p>수행여부판단</p>
                    <Radio.Group>
                        <Radio value={1}>수행여부</Radio>
                        <Radio value={2}>수행적합성</Radio>
                    </Radio.Group>
                </StyledDiv>
                
                <StyledDiv>
                    <_조직도업로드/>
                </StyledDiv>

                {/*<ReportGenOption columns={this.props.columns}/>*/}

                <StyledDiv>
                    <p>리포트생성 옵션</p>
                    {columns
                        .filter((item: any) => {
                            return item.dataIndex;
                        })
                        .filter((item: any) => {
                            let validItems = new Set([
                                '부서',
                                '직종',
                                '장소',
                                '행위',
                                '세부행위',
                                '장갑',
                            ]);

                            return validItems.has(item.title);

                        })
                        .map((item: any) => {
                            return (
                                <Space><Checkbox>{item.title}</Checkbox></Space>
                            )
                        })}
                </StyledDiv>




                <StyledDiv>
                    <Space>
                        <Button type={"primary"}>리포트 생성</Button>
                        <Button type={"primary"}>KONIS리포트 생성</Button>
                    </Space>
                </StyledDiv>

            </div>
        );
    }
}

export default Top;
