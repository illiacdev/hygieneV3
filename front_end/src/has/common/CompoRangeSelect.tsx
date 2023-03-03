import dayjs, {Dayjs} from "dayjs";
import {useForm} from "antd/es/form/Form";
import {css} from "styled-components/macro";
import {Button, ConfigProvider, DatePicker, Form, Space} from "antd";
import React from "react";
import locale from "antd/lib/locale/ko_KR"; // 우린 한국인이니까 ko_KR를 불러옵시다
import "dayjs/locale/ko"
import 'antd/dist/reset.css';
export const RangeSelect = (props: {
    cb: (begin_date: Dayjs, end_date: Dayjs) => void
    begin_date?: Dayjs
    end_date?: Dayjs
}) => {
    let [form] = useForm();
    let {cb} = props;

    const handleApply = () => {

    }

    return (
        <div css={css`display: flex;
              padding: 1em`}>
            <Form colon={false} form={form} layout={"inline"}
                  initialValues={{start_date: props.begin_date || dayjs(), end_date: props.end_date || dayjs()}}>
                {/*<p>조회시작일</p>*/}
                <Form.Item label={"조회시작일"} name={"start_date"}>
                    <DatePicker />
                </Form.Item>
                {/*<p>조회종료일</p>*/}
                <Form.Item label={"조회종료일"} name={"end_date"}>
                    <DatePicker/>
                </Form.Item>
            </Form>
            <Space>
                <Button onClick={() => {
                    form.setFieldValue('start_date', dayjs());
                    form.setFieldValue('end_date', dayjs());
                }
                }>오늘</Button>
                <Button onClick={() => {
                    form.setFieldValue('start_date', dayjs().add(-7, 'day'));
                    form.setFieldValue('end_date', dayjs());
                }
                }>일주일</Button>
                <Button type={"primary"}
                        onClick={() => {
                            let {start_date, end_date} = form.getFieldsValue();
                            cb(start_date, end_date);
                        }
                        }
                >조회</Button>
            </Space>
        </div>
    );
}
