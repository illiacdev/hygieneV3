package kr.co.has.hygiene.back_end.graphql;

import kr.co.has.hygiene.back_end.domain.RecordType;
import kr.co.has.hygiene.back_end.domain.RecordingPaper;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;

@AllArgsConstructor
@org.springframework.stereotype.Controller
public class Controller {

    private final Service service;

    @QueryMapping
    List<String> input_items(@Argument("name") String type_name) {
        return service.input_items(type_name);
    }
    @QueryMapping
    List<RecordingPaper> recordingPapers(){
        return service.recordingPapers();
    }

    @MutationMapping
    String addTypeItem(@Argument("type_name")String type_name,@Argument("item_name")String item_name) {
        RecordType recordType = service.addTypeItem(type_name, item_name);
        return recordType.getName();
    }


    @MutationMapping
    String addType(@Argument("name")String name) {
        RecordType recordType = service.addType(name);
        return recordType.getName();
    }


    @MutationMapping
    RecordingPaper addRecordingPaper(@Argument("input") RecordingPaper input){
        return service.addRecordingPaper(input);
    }
}
