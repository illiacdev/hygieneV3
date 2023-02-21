import React, {Component} from 'react';
import {apollo_client} from "../../App";
import {gql} from "@apollo/client";
import {Button, Space} from "antd";
import {makeAutoObservable, toJS} from "mobx";
import {store} from "./Store";
import {observer} from "mobx-react";
import {css} from "styled-components/macro";
import {tree_file} from "../../resources/tree";

class Store {

    root: Node | null = null;
    nodes: Node[] = [];
    부서: string = "";
    직종: string = "";
    이름: string = "";
    curr_node_index: number | null = null;

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

const TreeNodes = (props: { nodes: Node[],row_index:number }) => {
    return (
        <div css={css``}>{props.nodes.map((parentNode, index, array) => {
            return (
                <div css={css``}>
                    <Space wrap>
                        {
                            parentNode.nodes.map((chiledNode,index2) => {
                                return (
                                    <Button onClick={() => {

                                        localStore.nodes = localStore.nodes.slice(0, index + 1);
                                        localStore.nodes.push(chiledNode);
                                        localStore.curr_node_index = props.row_index;
                                        // localStore.calcName();
                                        let depth = calcDepth(chiledNode, 0);
                                        console.log('calcDepth', depth);

                                        if ((chiledNode.myDepth() >= 3) && (chiledNode.isTerminalNode())) {
                                        // if ((depth >= 3) && (chiledNode.nodes.length == 0)) {
                                            localStore.부서 = chiledNode.parent!.parent!.name;
                                            localStore.직종 = chiledNode.parent!.name;
                                            localStore.이름 = chiledNode.name;
                                        } else {
                                            localStore.부서 = "";
                                            localStore.직종 = "";
                                            localStore.이름 = "";
                                        }
                                        // localStore.nodes[index + 1] = chiledNode;
                                    }
                                    }>{chiledNode.name}</Button>
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

        calcDepth = (node: Node, depth: number): number => {
            if (node.parent == null)
                return depth;
            return this.calcDepth(node.parent, depth + 1);
        }
        myDepth(){
            return this.calcDepth(this, 0);
        }

        isTerminalNode(){
            return this.nodes.length == 0;
        }
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


    class ComSelectObserver extends Component<{ row_index:number,cb: (index: number, 부서: string, 직종: string, 성명: string) => void }, any> {
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

                nodes = tree_file.nodes;
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
                <div >
                    <TreeNodes nodes={toJS(localStore.nodes)} row_index={this.props.row_index}/>
                    {`${localStore.부서} - ${localStore.직종} - ${localStore.이름}`}
                    <Button css={css`display: block;margin-top: 2em`

                    }
                            onClick={()=>{
                                this.props.cb(localStore.curr_node_index!,localStore.부서,localStore.직종,localStore.이름)
                            }
                            }
                    >관찰자확인</Button>
                </div>
            );
        }
    }

// observer()
    export default observer(ComSelectObserver);
