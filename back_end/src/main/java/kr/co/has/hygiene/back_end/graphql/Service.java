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
//            return recordTypeRepository.findByNameIn(Arrays.asList("??????", "??????", "??????", "??????", "??????", "??????","??????????????????","??????????????????","????????????"));

        ArrayList<RecordType> objects = new ArrayList<>();
        if (templateName.equals("????????????") || templateName.equals("???????????????")) {
           /* recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });*/

            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            //?????????/???
            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            //input text
            recordTypeRepository.findByName("????????????").ifPresent(recordType -> {
                objects.add(recordType);
            });
            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });
            recordTypeRepository.findByName("????????????").ifPresent(recordType -> {
                objects.add(recordType);
            });
            /*recordTypeRepository.findByName("??????????????????").ifPresent(recordType -> {
                objects.add(recordType);
            });*/

            //?????????,?????????,?????????
            recordTypeRepository.findByName("????????????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            // pass /fail
            recordTypeRepository.findByName("???????????????").ifPresent(recordType -> {
                objects.add(recordType);
            });

        }


        if (templateName.equals("????????????")) {
//            return recordTypeRepository.findByNameIn(Arrays.asList("??????", "??????", "??????", "??????", "??????", "??????"));
            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            recordTypeRepository.findByName("??????").ifPresent(recordType -> {
                objects.add(recordType);
            });

            //?????????,?????????,?????????
            recordTypeRepository.findByName("????????????").ifPresent(recordType -> {
                objects.add(recordType);
            });

        }

        return objects;
    }

    public List<RecordType> initDB() {
//        createRecord("??????", "displayonly");
//        createRecord("??????", "displayonly");
//        createRecord("??????", "displayonly");

        createRecord("??????", "select");
        createRecordValidValue("??????", "?????????");
        createRecordValidValue("??????", "?????????");

        createRecord("??????", "select");
        createRecordValidValue("??????", "???????????? ???");
        createRecordValidValue("??????", "???????????? ???");

        createRecord("????????????", "inputText");

        createRecord("??????", "bool");

        createRecord("????????????", "chronometer");

        createRecord("????????????", "select");
        createRecordValidValue("????????????", "?????????");
        createRecordValidValue("????????????", "?????????");
        createRecordValidValue("????????????", "?????????");

        createRecord("???????????????", "bool");

//        createRecord("????????????", "text");
//        createRecordValidValue("????????????", "???????????? ???");
//        createRecordValidValue("????????????", "???????????? ???");


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

