import React, {Component} from 'react';
import ComRecord from "./components/기록지/ComRecord";
import {ApolloClient, ApolloLink, HttpLink, InMemoryCache} from "@apollo/client";


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
            <div>
                <ComRecord/>
            </div>
        );
    }
}

export default App;
