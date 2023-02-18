package kr.co.has.hygiene.back_end.graphql;

import kr.co.has.hygiene.back_end.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {
    private final RecordingPaperRepository recordingPaperRepository;
    private final RecordTypeRepository recordTypeRepository;
    private final RecordPredefValueRepository recordPredefValueRepository;


    @Transactional
    public List<String> input_items(String typeName) {
        return recordTypeRepository.findByName(typeName)
                .map(recordType -> recordType.getPredefValues())
                .map(recordPredefValues -> recordPredefValues.stream()
                        .map(recordPredefValue -> recordPredefValue.getName())
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }


    @Transactional
    public RecordType addTypeItem(String typeName, String itemName) {
        RecordType recordType1 = recordTypeRepository.findByName(typeName).orElseGet(() -> {
            RecordType recordType = new RecordType();
            recordType.setName(typeName);
            RecordType save = recordTypeRepository.save(recordType);
            return save;
        });

        RecordPredefValue recordPredefValue = new RecordPredefValue();
        recordPredefValue.setName(itemName);
        RecordPredefValue save1 = recordPredefValueRepository.save(recordPredefValue);
        recordType1.add(save1);

        RecordType save = recordTypeRepository.save(recordType1);
        return save;

    }

    @Transactional
    public RecordType addType(String name) {
        RecordType recordType = new RecordType();
        recordType.setName(name);

        RecordType save = recordTypeRepository.save(recordType);

        return save;
    }

    public RecordingPaper addRecordingPaper(RecordingPaper input) {
        return recordingPaperRepository.save(input);
    }

    public List<RecordingPaper> recordingPapers() {
        return recordingPaperRepository.findAll();
    }
}
