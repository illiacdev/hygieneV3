package kr.co.has.hygiene.back_end.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecordValidValue {

//    @EmbeddedId
//    RecordValidValueID recordValidValueID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
//    @MapsId("name")
//    @Column(name = "name")
    String name;

    @ManyToOne
//    @MapsId("fk_name")
    RecordType recordType;
}
