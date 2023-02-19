import {css} from "styled-components/macro";
import dayjs,{Dayjs} from  'dayjs'
import {Button} from "antd";
export const ComMiddle = () => {
    return (
        <div css={css`display: flex;
          padding-left: 1em;
          //background: blue;
          margin-top: 1em;
          margin-bottom: 1em;
          align-items: center`}>
            <div css={css`font-size: 1.5em;
              font-weight: bold;
              height: 4em;
              display: flex;
              flex-direction: column;
              justify-content: space-evenly`}>
                <div css={css`;`}> 기관명 : 병원</div>
               <div css={css``}> 현재시간 : {dayjs().format('YYYY년 MM월 DD일 HH:mm')}</div>
            </div>

            <div css={css`font-size: 2.5em;
              font-weight: bold;
              background: #0057aa;
              color: white;
              border-radius: .2em;
              margin-left: 9em;
              padding: 1em;
              padding-top: .1em;
              padding-bottom: .1em;

              //padding-left: 1.5em;
              //padding-right: 1.5em;
              //height: 2em;
              //margin-top: 1em;
              //margin-left: 2em;
            `}>
                손위생 관찰 기록지
            </div>
        </div>
    );
}
