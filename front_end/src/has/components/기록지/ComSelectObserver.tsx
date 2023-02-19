import React, {Component} from 'react';
import {apollo_client} from "../../App";
import {gql} from "@apollo/client";
import {Button} from "antd";

class ComSelectObserver extends Component {
    state = {tree: undefined,depth2:null}

    componentDidMount() {
        apollo_client.query({
            query: gql`
                query {
                    tree
                }
            `,
            fetchPolicy: "no-cache",

        }).then(value => {
            let tree = value.data.tree;
            let parse = JSON.parse(tree);
            console.log(parse);
            this.setState({tree: parse});

            for (const [key, value] of Object.entries(parse)) {
                console.log(`${key}: ${value}`);
            }

        }).catch(reason => {
            console.log(reason);
        })

    }

    render() {

        if (!this.state.tree)
            return null;

        return (
            <div>
                {
                    // JSON.stringify((Object.entries(this.state.tree) as any)[1][1])
                    (Object.entries(this.state.tree) as any)[1][1].map((item: any) => {
                        return <Button onClick={() => {
                            console.log(JSON.stringify(item.nodes));
                            this.setState({...this.state,depth2:item})

                            // item.nodes.map((item2: any) => {})
                        }
                        }>{item.name}</Button>
                    })
                }
                {
                    this.state.depth2 ? (this.state.depth2 as any).nodes.map((i:any)=><Button>{i.name}</Button>) : null
                    // JSON.stringify(this.state.depth2)
                }

            </div>
        );
    }
}

export default ComSelectObserver;
