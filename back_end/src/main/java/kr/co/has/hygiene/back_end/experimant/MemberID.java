package kr.co.has.hygiene.back_end.experimant;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MemberID implements Serializable {
    String fk_name;
    String name;
}
