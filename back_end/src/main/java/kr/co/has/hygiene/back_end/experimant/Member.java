package kr.co.has.hygiene.back_end.experimant;

import jakarta.persistence.*;

@Entity
public class Member {
    @EmbeddedId
    MemberID memberID;
//    @Id
//    String name;

    @ManyToOne
    @MapsId("fk_name")
    Team team;
}
