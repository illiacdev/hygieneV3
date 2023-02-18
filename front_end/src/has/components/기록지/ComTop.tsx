import styled from "styled-components";
import {css} from "styled-components/macro";
import logo from '../../resources/logo.png';

const StyledSpan = styled.span`
  //margin-left: 2em;
  font-size: 1.5em;
  font-weight: bold

`;


export const ComTop = () => {
    return (
        <div css={css`;
          display: flex;`}>
            <img src={logo} css={css`width: 100px;
              object-fit: contain`}/>

            <div css={css`width: 100%`}>
                <div css={css`width: 100%;
                  height: 1.5em;
                  background: #0054a5`}></div>
                <div css={css`display: flex;
                  justify-content: space-between;
                  font-size: .8em;
                  width: 100%`}>
                    <StyledSpan>손위생 관찰 기록지</StyledSpan>
                    <StyledSpan>접촉주의 체크리스트</StyledSpan>
                    <StyledSpan>ATP Test</StyledSpan>
                    <StyledSpan>역학조사(설문)</StyledSpan>
                    <StyledSpan>역학조사(관리)</StyledSpan>
                    <StyledSpan>감염관리 웹 인터페이스 Ver 1.0</StyledSpan>
                    <StyledSpan>로그아웃</StyledSpan>
                </div>
            </div>
        </div>
    );
}
