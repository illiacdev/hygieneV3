package kr.co.has.hygiene.back_end.graphql;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
//@Data
@Builder
class Node{
    public String name;
    public Node parent;
    public List<Node> nodes = new ArrayList<>();
    public Node add(Node node){
        nodes.add(node);
        return node;
    }
}

class ControllerTest {
    @Test
    void name() {
        Node root = Node.builder().name("root").build();
        Node 간호부 = root.add(Node.builder().name("간호부").parent(root).build());
        Node 진료부 = root.add(Node.builder().name("진료부").parent(root).build());
        Node 진료지원 = root.add(Node.builder().name("진료지원").parent(root).build());
        Node 기타 = root.add(Node.builder().name("기타").parent(root).build());


        진료부.add(Node.builder().name("가정의학과").parent(진료부).build());
        진료부.add(Node.builder().name("간·담,·,췌외과").parent(진료부).build());
        진료부.add(Node.builder().name("감염내과").parent(진료부).build());
        진료부.add(Node.builder().name("갑상선내분비외과").parent(진료부).build());
        진료부.add(Node.builder().name("내분비내과").parent(진료부).build());
        진료부.add(Node.builder().name("대장·항문외과,").parent(진료부).build());


        기타.add(Node.builder().name("기타").parent(기타).build());

        Gson gson = new Gson();
        String s = gson.toJson(기타).toString();
        System.out.printf("", s);

    }
}*/
