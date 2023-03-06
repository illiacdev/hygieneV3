import styled from "styled-components";
import {css} from "styled-components/macro";
// import logo from '../../../asset/성빈센트병원.png';
import logo from '../../../asset/세일병원.png';
// import logo from '../../../asset/인제대서울백병원.png';
import {Button} from "antd";
import {useNavigate} from "react-router-dom";

const StyledSpan = styled.span`
  //margin-left: 2em;
  font-size: 1.5em;
  font-weight: bold;
  ${props => props.css};
`;


export const ComTop = () => {
    const navigate = useNavigate();
//localStorage.removeItem("auth_token");
    if(!localStorage.getItem("auth_token"))
        navigate("/login");
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
                    <Button css={css`cursor: pointer`} onClick={event => {
                        localStorage.removeItem("auth_token");
                        navigate('/login');
                    }
                    }>로그아웃</Button>
                </div>
            </div>
        </div>
    );
}
