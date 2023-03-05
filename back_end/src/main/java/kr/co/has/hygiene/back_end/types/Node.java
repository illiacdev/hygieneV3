package kr.co.has.hygiene.back_end.types;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
public class Node {
    public String name;

    //        @Transient
//        public Node parent;
    @Builder.Default
    public List<Node> nodes = new ArrayList<>();

    public Optional<Node> get(String name) {
        return nodes.stream().filter(node -> node.name.equals(name)).findFirst();
    }

    public Node add(Node node) {
        nodes.add(node);
        return node;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", name, nodes.toString());
    }
}