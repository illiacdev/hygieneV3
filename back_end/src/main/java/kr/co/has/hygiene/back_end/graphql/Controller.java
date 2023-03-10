package kr.co.has.hygiene.back_end.graphql;

import com.google.gson.Gson;
import kr.co.has.hygiene.back_end.domain.RecordType;
import kr.co.has.hygiene.back_end.domain.RecordingPaper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@org.springframework.stereotype.Controller
public class Controller {

    private final Service service;

    /////////////////////////////////////////////////////////////
    //#기준정보
    @MutationMapping
    List<RecordType> initDB() {
        return service.initDB();
    }

    @MutationMapping
    RecordType createRecord(
            @Argument("name") String name,
            @Argument("type") String type
    ) {
        if (type == null)
            type = "text";
        return service.createRecord(name, type);
    }

    @MutationMapping
    RecordType createRecordValidValue(@Argument("type_name") String type_name, @Argument("item_name") String item_name, @Argument("type") String type) {
        return service.createRecordValidValue(type_name, item_name);
    }

    @QueryMapping
    List<RecordType> recordTypes() {
        return service.recordTypes();
    }


    //recordTypesTemplet(templateName:String):[RecordType]
    @QueryMapping
    List<RecordType> recordTypesTemplate(@Argument("templateName") String templateName) {
        return service.recordTypesTemplate(templateName);
    }

    @QueryMapping
    Optional<RecordType> recordType(@Argument("name") String type_name) {
        return service.recordType(type_name);
    }


    ///////////////////////////////////////////////////////////////////////////////
    //#기록지

    //기록1건 저장.
    @MutationMapping
    RecordingPaper createRecordingPaper(@Argument("input") RecordingPaper input) {
        return service.createRecordingPaper(input);
    }

    //기록목록 저장
    @MutationMapping
    List<RecordingPaper> createRecordingPapers(@Argument("input") List<RecordingPaper> paperList) {
        return service.createRecordingPapers(paperList);

    }

    @QueryMapping
    List<RecordingPaper> recordingPapers() {
        return service.recordingPapers();
    }

    @Builder
    static public class Node {
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

    @MutationMapping
    public String deleteRecordingPaper(@Argument("id") Long id) {

        return service.deleteRecordingPaper(id);
    }

    @MutationMapping
    public RecordingPaper updateRecordingPaper(@Argument("id") Long id, @Argument("input") RecordingPaper input) {
        return service.updateRecordingPaper(id, input);
    }


    //부서,직종,성명트리
    @QueryMapping
    String tree() {
       /* Node root = Node.builder().name("root").build();
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


        기타.add(Node.builder().name("기타").parent(기타).build());*/

        Node root = Node.builder().name("root").build();
        Node 간호부 = root.add(Node.builder().name("간호부").build());
        Node 진료부 = root.add(Node.builder().name("진료부").build());
        Node 진료지원 = root.add(Node.builder().name("진료지원").build());

        Node 기타 = root.add(Node.builder().name("기타").build());


        Node 가정의학과 = 진료부.add(Node.builder().name("가정의학과").build());
        Node 의사 = 가정의학과.add(Node.builder().name("의사").build());
        의사.add(Node.builder().name("홍길동").build());

        진료부.add(Node.builder().name("간·담,·,췌외과").build());
        진료부.add(Node.builder().name("감염내과").build());
        진료부.add(Node.builder().name("갑상선내분비외과").build());
        진료부.add(Node.builder().name("내분비내과").build());
        진료부.add(Node.builder().name("대장·항문외과,").build());


        Node 기타직종 = 기타.add(Node.builder().name("기타직종").build());
        기타직종.add(Node.builder().name("김기타").build());


        String s = new Gson().toJson(root).toString();

        return s;
    }

}
