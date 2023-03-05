package kr.co.has.hygiene.back_end.graphql;

import com.google.gson.Gson;
import kr.co.has.hygiene.back_end.domain.Entity조직도;
import kr.co.has.hygiene.back_end.domain.RecordType;
import kr.co.has.hygiene.back_end.domain.RecordingPaper;
import kr.co.has.hygiene.back_end.types.Node;
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


        String tree = service.tree();
        if (tree != null)
            return tree;
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
        Node 진료부 = root.add(Node.builder().name("진료부").build());
        진료부.add(Node.builder().name("가정의학과").build());
        진료부.add(Node.builder().name("간·담·췌외과").build());
        진료부.add(Node.builder().name("감염내과").build());
        진료부.add(Node.builder().name("갑상선내분비외과").build());
        진료부.add(Node.builder().name("내분비내과").build());
        진료부.add(Node.builder().name("대장·항문외과").build());
        진료부.add(Node.builder().name("류마티스내과").build());
        진료부.add(Node.builder().name("마취통증의학과").build());
        진료부.add(Node.builder().name("방사선종양학과").build());
        진료부.add(Node.builder().name("병리과").build());
        진료부.add(Node.builder().name("비뇨의학과").build());
        진료부.add(Node.builder().name("산부인과").build());
        진료부.add(Node.builder().name("성형외과").build());
        진료부.add(Node.builder().name("소아청소년과").build());
        진료부.add(Node.builder().name("소화기내과").build());
        진료부.add(Node.builder().name("순환기내과").build());
        진료부.add(Node.builder().name("신경과").build());
        진료부.add(Node.builder().name("신경외과").build());
        진료부.add(Node.builder().name("신장내과").build());
        진료부.add(Node.builder().name("심장혈관흉부외과").build());
        진료부.add(Node.builder().name("안과(안센터)").build());
        진료부.add(Node.builder().name("영상의학과").build());
        진료부.add(Node.builder().name("위장관외과").build());
        진료부.add(Node.builder().name("유방외과").build());
        진료부.add(Node.builder().name("응급의학과").build());
        진료부.add(Node.builder().name("이비인후과").build());
        진료부.add(Node.builder().name("재활의학과").build());
        진료부.add(Node.builder().name("정신건강의학과").build());
        진료부.add(Node.builder().name("정형외과").build());
        진료부.add(Node.builder().name("종양내과").build());
        진료부.add(Node.builder().name("진단검사의학과").build());
        진료부.add(Node.builder().name("치과").build());
        진료부.add(Node.builder().name("피부과").build());
        진료부.add(Node.builder().name("핵의학과").build());
        진료부.add(Node.builder().name("혈관·이식외과").build());
        진료부.add(Node.builder().name("혈액내과").build());
        진료부.add(Node.builder().name("호스피스완화의학과").build());
        진료부.add(Node.builder().name("호흡기내과").build());
        진료부.nodes.stream().forEach(node -> {
            node.add(Node.builder().name("교원(조교수, 임상교수 등등)").build());
            node.add(Node.builder().name("전공의(레지던트), 치과 수련의").build());
            node.add(Node.builder().name("인턴").build());
            node.add(Node.builder().name("간호사").build());
            node.add(Node.builder().name("병원지원직*").build());
            node.add(Node.builder().name("물리/언어/작업치료사").build());
            node.add(Node.builder().name("방사선사").build());
            node.add(Node.builder().name("미화(용역)").build());
            node.add(Node.builder().name("임상병리사").build());
            node.add(Node.builder().name("응급구조사").build());
            node.add(Node.builder().name("실습학생").build());
            node.add(Node.builder().name("원목팀").build());
            node.add(Node.builder().name("치위생사").build());
            node.add(Node.builder().name("사무직").build());
        });


        /*진료부.add(Node.builder().name("교원(조교수, 임상교수 등등)").build());
        진료부.add(Node.builder().name("전공의(레지던트), 치과 수련의").build());
        진료부.add(Node.builder().name("인턴").build());
        진료부.add(Node.builder().name("간호사").build());
        진료부.add(Node.builder().name("병원지원직*").build());
        진료부.add(Node.builder().name("물리/언어/작업치료사").build());
        진료부.add(Node.builder().name("방사선사").build());
        진료부.add(Node.builder().name("미화(용역)").build());
        진료부.add(Node.builder().name("임상병리사").build());
        진료부.add(Node.builder().name("응급구조사").build());
        진료부.add(Node.builder().name("실습학생").build());
        진료부.add(Node.builder().name("원목팀").build());
        진료부.add(Node.builder().name("치위생사").build());
        진료부.add(Node.builder().name("사무직").build());*/

        Node 간호부 = root.add(Node.builder().name("간호부").build());
        간호부.add(Node.builder().name("31병동/분만실").build());
        간호부.add(Node.builder().name("41병동").build());
        간호부.add(Node.builder().name("42병동").build());
        간호부.add(Node.builder().name("51병동/집중관찰실").build());
        간호부.add(Node.builder().name("52병동").build());
        간호부.add(Node.builder().name("61병동/응급병동").build());
        간호부.add(Node.builder().name("호스피스완화의료병동").build());
        간호부.add(Node.builder().name("62병동").build());
        간호부.add(Node.builder().name("71병동/뇌졸중집중치료실").build());
        간호부.add(Node.builder().name("72병동").build());
        간호부.add(Node.builder().name("81병동").build());
        간호부.add(Node.builder().name("82병동").build());
        간호부.add(Node.builder().name("91병동").build());
        간호부.add(Node.builder().name("92병동").build());
        간호부.add(Node.builder().name("101병동/BMT병동").build());
        간호부.add(Node.builder().name("102병동").build());
        간호부.add(Node.builder().name("55병동").build());
        간호부.add(Node.builder().name("65병동").build());
        간호부.add(Node.builder().name("내과중환자실").build());
        간호부.add(Node.builder().name("신경외과중환자실").build());
        간호부.add(Node.builder().name("신생아실/신생아중환자실").build());
        간호부.add(Node.builder().name("외과중환자실").build());
        간호부.add(Node.builder().name("내시경실").build());
        간호부.add(Node.builder().name("수술실").build());
        간호부.add(Node.builder().name("응급의료센터").build());
        간호부.add(Node.builder().name("흉부심혈관중환자실").build());
        간호부.add(Node.builder().name("인공신장실").build());
        간호부.add(Node.builder().name("소아주사실").build());
        간호부.add(Node.builder().name("단기입원치료실").build());
        간호부.add(Node.builder().name("주사실").build());
        간호부.add(Node.builder().name("전담간호 Unit").build());
        간호부.add(Node.builder().name("심뇌혈관센터(심장초음파실)").build());

        간호부.nodes.stream().forEach(node -> {
            node.add(Node.builder().name("교원(조교수, 임상교수 등등)").build());
            node.add(Node.builder().name("전공의(레지던트), 치과 수련의").build());
            node.add(Node.builder().name("인턴").build());
            node.add(Node.builder().name("간호사").build());
            node.add(Node.builder().name("병원지원직*").build());
            node.add(Node.builder().name("물리/언어/작업치료사").build());
            node.add(Node.builder().name("방사선사").build());
            node.add(Node.builder().name("미화(용역)").build());
            node.add(Node.builder().name("임상병리사").build());
            node.add(Node.builder().name("응급구조사").build());
            node.add(Node.builder().name("실습학생").build());
            node.add(Node.builder().name("원목팀").build());
            node.add(Node.builder().name("치위생사").build());
            node.add(Node.builder().name("사무직").build());
        });

        Node 진료지원 = root.add(Node.builder().name("진료지원").build());
        진료지원.add(Node.builder().name("건강증진센터").build());
        진료지원.add(Node.builder().name("기능검사실").build());
        진료지원.add(Node.builder().name("재활치료실").build());
        진료지원.add(Node.builder().name("영상의학과").build());
        진료지원.add(Node.builder().name("진단검사의학과(외래채혈실)").build());
        진료지원.add(Node.builder().name("치과").build());

        진료지원.nodes.stream().forEach(node -> {
            node.add(Node.builder().name("교원(조교수, 임상교수 등등)").build());
            node.add(Node.builder().name("전공의(레지던트), 치과 수련의").build());
            node.add(Node.builder().name("인턴").build());
            node.add(Node.builder().name("간호사").build());
            node.add(Node.builder().name("병원지원직*").build());
            node.add(Node.builder().name("물리/언어/작업치료사").build());
            node.add(Node.builder().name("방사선사").build());
            node.add(Node.builder().name("미화(용역)").build());
            node.add(Node.builder().name("임상병리사").build());
            node.add(Node.builder().name("응급구조사").build());
            node.add(Node.builder().name("실습학생").build());
            node.add(Node.builder().name("원목팀").build());
            node.add(Node.builder().name("치위생사").build());
            node.add(Node.builder().name("사무직").build());
        });

        Node 기타 = root.add(Node.builder().name("기타").build());
        기타.add(Node.builder().name("응급구조사").build());
        기타.add(Node.builder().name("원목팀").build());
        기타.add(Node.builder().name("미화(용역)").build());
        기타.nodes.stream().forEach(node -> {
            node.add(Node.builder().name("교원(조교수, 임상교수 등등)").build());
            node.add(Node.builder().name("전공의(레지던트), 치과 수련의").build());
            node.add(Node.builder().name("인턴").build());
            node.add(Node.builder().name("간호사").build());
            node.add(Node.builder().name("병원지원직*").build());
            node.add(Node.builder().name("물리/언어/작업치료사").build());
            node.add(Node.builder().name("방사선사").build());
            node.add(Node.builder().name("미화(용역)").build());
            node.add(Node.builder().name("임상병리사").build());
            node.add(Node.builder().name("응급구조사").build());
            node.add(Node.builder().name("실습학생").build());
            node.add(Node.builder().name("원목팀").build());
            node.add(Node.builder().name("치위생사").build());
            node.add(Node.builder().name("사무직").build());
        });


/*
        Node 가정의학과 = 진료부.add(Node.builder().name("가정의학과").build());
        Node 의사 = 가정의학과.add(Node.builder().name("의사").build());
        의사.add(Node.builder().name("홍길동").build());

        진료부.add(Node.builder().name("간·담,·,췌외과").build());
        진료부.add(Node.builder().name("감염내과").build());
        진료부.add(Node.builder().name("갑상선내분비외과").build());
        진료부.add(Node.builder().name("내분비내과").build());
        진료부.add(Node.builder().name("대장·항문외과,").build());


        Node 기타직종 = 기타.add(Node.builder().name("기타직종").build());
        기타직종.add(Node.builder().name("김기타").build());*/


        String s = new Gson().toJson(root).toString();

        return s;
    }

    @QueryMapping
    List<Entity조직도> organizationCharters() {
        List<Entity조직도> entity조직도s = service.organizationCharters();
        return entity조직도s;
    }
}
