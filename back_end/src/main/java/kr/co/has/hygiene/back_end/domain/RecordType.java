package kr.co.has.hygiene.back_end.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecordType {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Long id;
    String name;

    @Builder.Default
    String type = "text";//text , bool, dateTime...

    @Builder.Default
    @OneToMany
    List<RecordValidValue> recordValidValues = new ArrayList<>();

    public void add(RecordValidValue value) {
        recordValidValues.add(value);
    }

}
