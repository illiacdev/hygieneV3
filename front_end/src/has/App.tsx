import React, {Component} from 'react';
import ComRecord from "./components/기록지/ComRecord";
import {ApolloClient, ApolloLink, HttpLink, InMemoryCache} from "@apollo/client";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import ReportPaperTable from "./components/관리자/ReportPaperTable";
import Login from "./components/auth/Login";
import Dev from "./experiment/Dev";
import EditableReportPaperTable from "./components/관리자/EditableReportPaperTable";
import Config from "./components/관리자/설정/Config";
import DomainHome from "./experiment/DomainHome";


// const httpLink = new HttpLink({ uri: 'https://api.example.com/graphql' });
const httpLink = new HttpLink({ uri: '/graphql' });

const authLink = new ApolloLink((operation, forward) => {
    // Retrieve the authorization token from local storage.
    const token = localStorage.getItem('auth_token');

    // Use the setContext method to set the HTTP headers.
    operation.setContext({
        headers: {
            authorization: token ? `Bearer ${token}` : ''
        }
    });

    // Call the next link in the middleware chain.
    return forward(operation);
});

export const apollo_client = new ApolloClient({
    // uri: 'http://localhost:8080/graphql',
    // uri: '/graphql',
    link:authLink.concat(httpLink),
    cache: new InMemoryCache(),

});

class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <Routes>
                    <Route path={"/"} element={<ComRecord/>}/>
                    {/*<Route path="/:id" element={<DomainHome/>} />*/}
                    <Route path={"/config"} element={<Config/>}/>

                    <Route path={"/login"} element={<Login title={"손위생 기록지 관리시스템"}/>}/>
                    <Route path={"/report_list"} element={<EditableReportPaperTable/>}/>
                    {/*<Route path={"/edit_report_list"} element={<EditableReportPaperTable/>}/>*/}
                    {/*<Route path={"/report"} element={<ReportPaperTable/>}/>*/}
                    <Route path={"/list"} element={<EditableReportPaperTable/>}/>
                    {/*<Route path={"/dev"} element={<Dev/>}/>*/}
                    <Route path={"/dev"} element={<Dev/>}/>

                </Routes>
            </BrowserRouter>
        );
    }
}

export default App;
