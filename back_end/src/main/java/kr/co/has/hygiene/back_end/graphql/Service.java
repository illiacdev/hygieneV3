package kr.co.has.hygiene.back_end.graphql;

import com.google.gson.Gson;
import kr.co.has.hygiene.back_end.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {
    private final RecordingPaperRepository recordingPaperRepository;
    private final RecordTypeRepository recordTypeRepository;
    private final RecordValidValueRepository recordValidValueRepository;


    @Transactional
    public Optional<RecordType> recordType(String typeName) {
        return recordTypeRepository.findByName(typeName);
    }


    /*@Transactional
    public RecordType createRecordValidValue(String typeName, String itemName) {
        RecordType recordType1 = recordTypeRepository.findByName(typeName).orElseGet(() -> {
            RecordType recordType = new RecordType();
            recordType.setName(typeName);
            RecordType save = recordTypeRepository.save(recordType);
            return save;
        });

        RecordValidValue recordValidValue = new RecordValidValue();
        recordValidValue.setName(itemName);
        RecordValidValue save1 = recordValidValueRepository.save(recordValidValue);
        recordType1.add(save1);

        RecordType save = recordTypeRepository.save(recordType1);
        return save;

    }*/

    @Transactional
    public RecordType createRecordValidValue(String typeName, String itemName) {
        RecordType recordType = recordTypeRepository.findByName(typeName)
                .orElseGet(() -> recordTypeRepository.save(RecordType.builder().name(typeName).build()));

        RecordValidValue recordValidValue = recordValidValueRepository.save(RecordValidValue.builder().recordType(recordType).name(itemName).build());
        recordType.add(recordValidValue);

        return recordTypeRepository.save(recordType);

    }


    public RecordingPaper createRecordingPaper(RecordingPaper input) {
        return recordingPaperRepository.save(input);
    }

    public List<RecordingPaper> recordingPapers() {
        return recordingPaperRepository.findAll();
    }


    @Transactional

    public List<RecordType> recordTypes() {
        return recordTypeRepository.findAll();
    }

    public RecordType createRecord(String name, String type) {
        RecordType recordType = RecordType.builder().name(name).type(type).build();
        return recordTypeRepository.save(recordType);

    }

    public List<RecordType> recordTypesTemplate(String templateName) {
//        if (true)
//            return recordTypeRepository.findByNameIn(Arrays.asList("부서", "직종", "이름", "장소", "행위", "장갑","수행시작시간","수행종료시간","수행여부"));

        ArrayList<RecordType> objects = new ArrayList<>();
        if (templateName.equals("성빈센트")) {
           /* recordTypeRepository.findByName("부서").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("직종").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("이름").ifPresent(recordType -> {
                objects.add(recordType);
            });*/

            recordTypeRepository.findByName("장소").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("행위").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("세부행위").ifPresent(recordType -> {
                objects.add(recordType);
            });
            recordTypeRepository.findByName("장갑").ifPresent(recordType -> {
                objects.add(recordType);
            });
            recordTypeRepository.findByName("수행시간").ifPresent(recordType -> {
                objects.add(recordType);
            });
            /*recordTypeRepository.findByName("수행종료시간").ifPresent(recordType -> {
                objects.add(recordType);
            });*/
            recordTypeRepository.findByName("수행여부").ifPresent(recordType -> {
                objects.add(recordType);
            });


        }


        if (templateName.equals("세일병원")) {
//            return recordTypeRepository.findByNameIn(Arrays.asList("부서", "직종", "이름", "장소", "행위", "장갑"));
            recordTypeRepository.findByName("부서").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("직종").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("이름").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("장소").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("행위").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("장갑").ifPresent(recordType -> {
                objects.add(recordType);
            });


        }

        return objects;
    }

    public List<RecordType> initDB() {
//        createRecord("부서", "displayonly");
//        createRecord("직종", "displayonly");
//        createRecord("이름", "displayonly");

        createRecord("장소", "select");
        createRecordValidValue("장소", "수술실");
        createRecordValidValue("장소", "채혈실");

        createRecord("행위", "select");
        createRecordValidValue("행위", "환자접촉 전");
        createRecordValidValue("행위", "환자접촉 후");

        createRecord("세부행위", "inputText");

        createRecord("수행시간", "chronometer");

        createRecord("장갑", "bool");
        createRecord("수행여부", "bool");

//        createRecord("세부행위", "text");
//        createRecordValidValue("세부행위", "환자접촉 전");
//        createRecordValidValue("세부행위", "환자접촉 후");




//         createRecordValidValue("");

        return recordTypeRepository.findAll();
    }


    //@Data


}

