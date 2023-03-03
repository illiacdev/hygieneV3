import React, {Component} from 'react';
import {css} from 'styled-components/macro';
import {makeAutoObservable} from "mobx";
import {Checkbox} from "antd";
import {observer} from "mobx-react";
import _ from "lodash";
import Pagination from "./Pagination";


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
    return obj[key] ;
}

const store = new Store();

class Dev extends Component {
    render() {
        return (
            <div css={css`font-size: 3em`}>
                <Pagination/>
            </div>
        );
    }
}

// observer()
export default observer(Dev);
