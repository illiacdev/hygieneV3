package kr.co.has.hygiene.back_end.graphql;

import kr.co.has.hygiene.back_end.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
}
