import React, {Component} from 'react';
import {Button, Upload} from "antd";
import {UploadOutlined} from "@ant-design/icons";
import axios from 'axios';

class _조직도업로드 extends Component {
    file: any = null;
    ref: any = null;

    upload = (file: any) => {
        // JSON.stringify(file);
        let ref1 = this.ref;
        let file1 = ref1.files[0];
        console.log(file1);
        let formData = new FormData();
        formData.append("file", ref1.files[0]);

       /* axios.get("/api/info")
            .then(value => {
                console.log(value.data);
            }).catch(reason => {
            return reason;
        })
        return;*/
        // axios.post("http://localhost:8080/api/upload", formData, {
        axios.post("/api/upload", formData, {

            // headers: {'Content-Type': 'multipart/form-data'}
            headers: {"Content-Type": "multipart/form-data"}
        }).then(value => {
            console.log('call upload api result : ', value.data)
        }).catch(reason => {
            console.log('call upload api error  : ', reason)
        });
    }

    render() {
        return (
            <div>
                <Upload>
                    <Button icon={<UploadOutlined/>}>조직도 엑셀파일 업로드</Button>
                </Upload>

                <input ref={ref => this.ref = ref} type={"file"}

                       onChange={(event: any) => {
                           // JSON.stringify(this.ref);
                           const [file] = event.target.files;
                           console.log('file', file);

                       }
                       }/>

                <Button onClick={() => {
                    this.upload(this.file);
                }
                }>업로드</Button>

            </div>
        );
    }
}

export default _조직도업로드;
