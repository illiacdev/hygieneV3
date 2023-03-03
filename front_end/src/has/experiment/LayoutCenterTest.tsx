// @flow
import * as React from 'react';
import {css} from "styled-components/macro";
import Pagination from "./Pagination";

type Props = {

};
export const LayoutCenterTest = (props: Props) => {
    return (
        <div css={css`font-size: 3em`}>
            <div css={css`background: yellow;display: flex;flex-direction: row;justify-content: space-between`}>
                <span css={css`background: red`}>Hello!!!!!!!!!!!!!!!!!!</span>

                <span css={css`background: red`}>Hello</span>
            </div>
            <div css={css`display: flex;justify-content: center;position: relative;top: -1.4em`}>
                <span>Hello</span>
            </div>


        </div>
    );
};
