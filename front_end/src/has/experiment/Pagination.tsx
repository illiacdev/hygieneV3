import React, {Component} from 'react';
import {ConfigProvider, DatePicker} from "antd";
import 'dayjs/locale/ko'
import locale from 'antd/es/date-picker/locale/ko_KR';
import {RangeSelect} from "../common/CompoRangeSelect";
class Pagination extends Component {
    render() {
        return (
            <div>
                Hello!
                    <DatePicker locale={locale}/>
                <RangeSelect cb={(begin_date, end_date) => {
                }
                }/>
            </div>
        );
    }
}

export default Pagination;
