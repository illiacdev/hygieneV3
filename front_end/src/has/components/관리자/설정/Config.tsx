import React, {Component} from 'react';
import {Button, Tabs, Upload} from "antd";
import { UploadOutlined } from '@ant-design/icons';
import _조직도업로드 from "./_조직도업로드";
import TabPane = Tabs.TabPane;

class Config extends Component {
    render() {
        return (
            <div>
                <Tabs defaultActiveKey="1"
                      // onChange={callback}
                >
                    <TabPane tab="기준정보 수정" key="1">
                        Content of Tab Pane 1
                    </TabPane>
                    <TabPane tab="조직도 업로드" key="2">
                        <_조직도업로드/>
                    </TabPane>
                    {/*<TabPane tab="Tab 3" key="3">
                        Content of Tab Pane 3
                    </TabPane>*/}
                </Tabs>



            </div>
        );
    }
}

export default Config;
