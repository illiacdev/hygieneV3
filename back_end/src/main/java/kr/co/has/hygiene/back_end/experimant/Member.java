package kr.co.has.hygiene.back_end.experimant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@IdClass(MemberID.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    //    @EmbeddedId
//    MemberID memberID;
    @Id
    String name;

    @ManyToOne
    @Id
//    @MapsId("fk_name")
    Team team;
}
