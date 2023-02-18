import React from 'react';

// import {Predef} from "../Config/Predef";
// import Optional from "optional-js";
import {Predef} from "./Predef";
import {css} from 'styled-components/macro';
// import '../font.css';

const FixRes = (component: React.ComponentType) => {
    return class extends React.Component {

        calcZoom = () => {
            // return Optional.ofNullable(Config.scale)
            //     .orElse(window.innerWidth / Config.main_width);
            let scale = Math.min(window.innerWidth / Predef.main_width, window.innerHeight / Predef.main_height);
            scale = window.innerWidth / Predef.main_width;
            scale = Predef.scale || scale;
            return scale;

        }
        ;

        state = {
            zoom: this.calcZoom(),
        };

        componentDidMount() {
            let that = this;
            window.onresize = function () {
                that.setState({zoom: that.calcZoom()});
            }
        }

        render() {
            let WrapComponent = component;
            return (
                <div style={{
                    zoom: this.state.zoom,
                    // userSelect: 'none',
                    // overflow: 'hidden',
                    width: `${Predef.main_width}px`,
                    // margin: 'auto',
                    // fontFamily: 'HAS',
                    // background:'yellow',
                    // height: `${100 / this.state.zoom}vh`,
                    // height: '540px',
                    boxSizing: "border-box",
                    // margin: 'auto'
                }}>
                    <div css={css`
                      //background: azure;
                      //border: #282c34 solid 1px;
                      height: 100%;
                    `}><WrapComponent/></div>
                </div>
            );
        }
    }
}

export default FixRes;
