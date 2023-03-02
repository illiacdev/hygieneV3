package kr.co.has.hygiene.back_end.graphql;

import com.google.gson.Gson;
import kr.co.has.hygiene.back_end.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.graphql.data.method.annotation.Argument;
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
        if (templateName.equals("성빈센트병원") || templateName.equals("서울백병원")) {
//            recordTypeRepository.findByName("부서").ifPresent(recordType -> {
//                objects.add(recordType);
//            });

//            recordTypeRepository.findByName("직종").ifPresent(recordType -> {
//                objects.add(recordType);
//            });

            recordTypeRepository.findByName("성명").ifPresent(recordType -> {
                objects.add(recordType);
            });

//            recordTypeRepository.findByName("장소").ifPresent(recordType -> {
//                objects.add(recordType);
//            });

            //접촉전/후
            recordTypeRepository.findByName("행위").ifPresent(recordType -> {
                objects.add(recordType);
            });

            //input text
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

            //손소독,손세척,마수행
            recordTypeRepository.findByName("수행여부").ifPresent(recordType -> {
                objects.add(recordType);
            });

            // pass /fail
            recordTypeRepository.findByName("수행적합성").ifPresent(recordType -> {
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

            //손소독,손세척,마수행
            recordTypeRepository.findByName("수행여부").ifPresent(recordType -> {
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
        createRecordValidValue("행위", "무균적 시술전");
        createRecordValidValue("행위", "체액 및 분비물 접촉 후");
        createRecordValidValue("행위", "주변환경 접촉 후");
        createRecordValidValue("행위", "환자접촉 후");

//        createRecord("세부행위", "inputText");
        createRecord("세부행위", "select");
        createRecordValidValue("세부행위", "지지적 행동");
        createRecordValidValue("세부행위", "직접접촉");
        createRecordValidValue("세부행위", "신체사정");
        createRecordValidValue("세부행위", "투약시");
        createRecordValidValue("세부행위", "수술 및 시술 전/후");
        createRecordValidValue("세부행위", "점막접촉");
        createRecordValidValue("세부행위", "피부 병변과 접촉");
        createRecordValidValue("세부행위", "침습적 시술");
        createRecordValidValue("세부행위", "폐쇄된 시스템 개방");
        createRecordValidValue("세부행위", "점막 또는 피부 병변 접촉 후");
        createRecordValidValue("세부행위", "혈액 및 체액 체액 등");
        createRecordValidValue("세부행위", "소변, 대변 정리");
        createRecordValidValue("세부행위", "의료기구 세척 및 정리");
        createRecordValidValue("세부행위", "침상정리 및 의료기구 접촉후");


        createRecord("장갑", "bool");

        createRecord("수행시간", "chronometer");

        createRecord("수행여부", "select");
        createRecordValidValue("수행여부", "손소독");
        createRecordValidValue("수행여부", "손세척");
        createRecordValidValue("수행여부", "미수행");

        createRecord("수행적합성", "bool");

//        createRecord("세부행위", "text");
//        createRecordValidValue("세부행위", "환자접촉 전");
//        createRecordValidValue("세부행위", "환자접촉 후");


//         createRecordValidValue("");

        return recordTypeRepository.findAll();
    }

    public List<RecordingPaper> createRecordingPapers(List<RecordingPaper> paperList) {
        paperList.forEach(recordingPaper -> {
            recordingPaperRepository.save(recordingPaper);
        });
        return null;
    }

    public String deleteRecordingPaper(Long id) {
        try {
            recordingPaperRepository.deleteById(id);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            return e.getLocalizedMessage();
        }
        return "OK!";
    }

    public RecordingPaper updateRecordingPaper(Long id, RecordingPaper input) {
        input.setId(id);
        RecordingPaper save = null;
        try {
            save = recordingPaperRepository.save(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return save;
    }

}

