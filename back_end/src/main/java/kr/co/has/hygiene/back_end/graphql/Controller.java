package kr.co.has.hygiene.back_end.graphql;

import kr.co.has.hygiene.back_end.domain.RecordType;
import kr.co.has.hygiene.back_end.domain.RecordingPaper;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@org.springframework.stereotype.Controller
public class Controller {

    private final Service service;

    @MutationMapping
    RecordType createRecord(
            @Argument("name")String name,
            @Argument("type")String type
            ){
        if(type == null)
            type = "text";
        return service.createRecord(name, type);
    }
    @MutationMapping
    RecordType createRecordValidValue(@Argument("type_name")String type_name, @Argument("item_name")String item_name,@Argument("type")String type) {
        return service.createRecordValidValue(type_name, item_name);
    }

    @QueryMapping
    List<RecordType> recordTypes(){
        return service.recordTypes();
    }


    //recordTypesTemplet(templateName:String):[RecordType]
    @QueryMapping
    List<RecordType> recordTypesTemplate(@Argument("templateName")String templateName){
        return service.recordTypesTemplate(templateName);
    }

    @QueryMapping
    Optional<RecordType> recordType(@Argument("name") String type_name) {
        return service.recordType(type_name);
    }

    @MutationMapping
    RecordingPaper createRecordingPaper(@Argument("input") RecordingPaper input){
        return service.createRecordingPaper(input);
    }

    @QueryMapping
    List<RecordingPaper> recordingPapers(){
        return service.recordingPapers();
    }
}
