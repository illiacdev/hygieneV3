import React, {Component} from 'react';
import {makeAutoObservable} from "mobx";
import {Checkbox} from "antd";
import {observer} from "mobx-react";
import _ from "lodash";
import {css} from 'styled-components/macro';
import styled from "styled-components";


class A {
    name: string = "illiac";
    checked: boolean = false;
}

class Store {
    a: A = new A();

    constructor() {
        makeAutoObservable(this);
    }
}

function updateProp<TObj, K extends keyof TObj>(obj: TObj, key: K, value: TObj[K]) {
    obj[key] = value;
}

function getProp<TObj, K extends keyof TObj>(obj: TObj, key: K) {
    return obj[key];
}

const store = new Store();

const CompoTemp = (props: { a: A }) => {
    return (
        <div>
            {JSON.stringify(props.a.checked)}
            <Checkbox
                onChange={e => {
                    let checked = e.target.checked;
                    console.log(checked);
                    store.a.checked = checked;
                    store.a = _.cloneDeep(store.a)


                }
                }
                checked={props.a.checked}
            >체크박스 테스트</Checkbox>
        </div>
    );
}


class LayoutTest extends Component<any, any> {

    render() {
        return (
            <div css={css`font-size: 3em`}>
                <div css={css`background: yellow;
                  display: flex;
                  flex-direction: row;
                  justify-content: space-between`}>
                    <span css={css`background: red`}>Hello!!!!!!!!!!!!!!!!!!</span>

                    <span css={css`background: red`}>Hello</span>
                </div>
                <div css={css`display: flex;
                  justify-content: center;
                  position: relative;
                  top: -1.4em`}>
                    <span>Hello</span>
                </div>

                <CompoTemp a={store.a}/>

            </div>
        );
    }
}

const StyledDiv = styled.div`
  border: #282c34 solid 1px;
  //width: 200px;
  // height: 100px;
  ${p => p.css}

`

class GridTest extends Component<any, any> {

    render() {
        return (
            <div css={css`display: grid;grid-template-columns: 1fr 1fr`}>
                <StyledDiv css={css`background: red`}>테스트</StyledDiv>
                <StyledDiv css={css`background: green`}>테스트</StyledDiv>
                <StyledDiv css={css`background: blue`}>테스트</StyledDiv>
                <StyledDiv css={css`background: pink`}>테스트</StyledDiv>

            </div>
        );
    }
}

class Dev extends Component {
    render() {
        return (
            <div>
                <GridTest/>
            </div>
        );
    }
}

// observer()
export default observer(Dev);
