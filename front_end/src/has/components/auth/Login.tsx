import {Button, Form, FormInstance, Input} from 'antd';
import React, {Component} from 'react';
import {css} from "styled-components/macro";
import axios from "axios";
import {NavigateFunction} from "react-router/dist/lib/hooks";
import Optional from "optional-js";
import {withRouter} from "../../common/UtilCompo";

class Login extends Component<{ navigate: NavigateFunction,title?:string }, any> {
    login = (id: string, password: string) => {
        let that = this;
        console.log(id, password);

        axios.post("/api/login", {

            "id": id,
            "password": password

        }).then(value => {
            console.log(value.data);
            Optional.ofNullable(value.data).ifPresent(value1 => {
                console.log("token", value1);
                localStorage.setItem("auth_token", value1);
                that.props.navigate("/");
                // apollo_client.set

            });
        }).catch(reason => {
            console.log(reason);
        })
    }

    form!: FormInstance;

    render() {
        return (
            <div css={css`display: flex;
              justify-content: center;
              height: 100vh;
              background: #feffe6`}>
                <div css={css`margin: auto`}>
                    {/*<p>H.M.S Hygiene report Management System 손위생 관리시스템 로그인</p>*/}
                    <p>{this.props.title || "로그인"}</p>
                    <Form
                        // initialValues={{id:"admin",password:"1234"}}
                        ref={(ref: FormInstance) => this.form = ref}>
                        <Form.Item name={"id"}>
                            <Input placeholder={"아이디"} onPressEnter={()=>{

                            }
                            }/>
                        </Form.Item>
                        <Form.Item name={"password"}>
                            <Input placeholder={"패스워드"} type={"password"} onPressEnter={()=>{
                                let fieldsValue = this.form.getFieldsValue();
                                this.login(fieldsValue.id, fieldsValue.password);
                            }
                            }/>
                        </Form.Item>

                        <Button onClick={event => {
                            let fieldsValue = this.form.getFieldsValue();
                            this.login(fieldsValue.id, fieldsValue.password);
                        }}>로그인</Button>
                    </Form>
                </div>
            </div>
        );
    }
}

// withRouter()
export default withRouter(Login);
