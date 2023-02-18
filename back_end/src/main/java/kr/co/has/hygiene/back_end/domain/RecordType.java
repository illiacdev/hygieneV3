package kr.co.has.hygiene.back_end.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class RecordType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;

    @OneToMany
    List<RecordPredefValue> predefValues = new ArrayList<>();

    public void add(RecordPredefValue value) {
        predefValues.add(value);
    }

}
