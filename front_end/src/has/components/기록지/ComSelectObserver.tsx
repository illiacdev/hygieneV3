import React, {Component} from 'react';
import {apollo_client} from "../../App";
import {gql} from "@apollo/client";
import {Button, Space} from "antd";
import {makeAutoObservable, toJS} from "mobx";
import {store} from "./Store";
import {observer} from "mobx-react";
import {css} from "styled-components/macro";


class Store {

    root: Node | null = null;
    nodes: Node[] = [];
    부서: string = "";
    직종: string = "";
    이름: string = "";

    calcName() {
        if (this.nodes.length >= 3) {
            this.부서 = this.nodes[this.nodes.length - 3].name;
            this.직종 = this.nodes[this.nodes.length - 2].name;
            this.이름 = this.nodes[this.nodes.length - 1].name;
        } else {
            this.부서 = "";
            this.직종 = "";
            this.이름 = "";
        }

    }

    constructor() {
        makeAutoObservable(this);
    }
}

const localStore = new Store();


const calcDepth = (node: Node, depth: number): number => {
    if (node.parent == null)
        return depth;
    return calcDepth(node.parent, depth + 1);
}

const TreeNodes = (props: { nodes: Node[] }) => {
    return (
        <div>{props.nodes.map((value, index, array) => {
            return (
                <div css={css``}>
                    <Space>
                        {
                            value.nodes.map(value1 => {
                                return (
                                    <Button onClick={() => {
                                        console.log(value1)

                                        localStore.nodes = localStore.nodes.slice(0, index + 1);
                                        localStore.nodes.push(value1);
                                        // localStore.calcName();
                                        let depth = calcDepth(value1, 0);
                                        console.log('calcDepth', depth);

                                        if ((depth >= 3) && (value1.nodes.length == 0)) {
                                            localStore.부서 = value1.parent!.parent!.name;
                                            localStore.직종 = value1.parent!.name;
                                            localStore.이름 = value1.name;
                                        } else {
                                            localStore.부서 = "";
                                            localStore.직종 = "";
                                            localStore.이름 = "";
                                        }
                                        // localStore.nodes[index + 1] = value1;
                                    }
                                    }>{value1.name}</Button>
                                )
                            })
                        }
                    </Space>
                    <hr/>
                </div>)

        })}
        </div>);
}
    ;

    class Node {
        name: string;
        parent: Node | null = null;
        nodes: Node[] = [];

        add(node: Node) {
            this.nodes.push(node);
        }


        constructor(name: string, parent: Node | null) {
            this.name = name;
            this.parent = parent;
        }
    }

    const init = (node: Node, nodes: any[]) => {
        nodes.forEach(value => {
            let node1 = new Node(value.name, node);
            node.nodes.push(node1)
            init(node1, value.nodes);
        });
    }


    class ComSelectObserver extends Component<{ cb: (index: number, 부서: string, 직종: string, 성명: string) => void }, any> {
        // state = {tree: undefined, group: []}

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
                let nodes: any[] = parse.nodes;
                console.log(parse);

                localStore.root = new Node("root", null);
                localStore.nodes = [];

                init(localStore.root, nodes);
                localStore.nodes.push(localStore.root);
                console.log(localStore.root);


            }).catch(reason => {
                console.log(reason);
            })

        }

        render() {

            let toJS1 = toJS(localStore.root);
            if (toJS1 == null)
                return null;

            return (
                <div>
                    <TreeNodes nodes={toJS(localStore.nodes)}/>
                    {`${localStore.부서} - ${localStore.직종} - ${localStore.이름}`}
                </div>
            );
        }
    }

// observer()
    export default observer(ComSelectObserver);
