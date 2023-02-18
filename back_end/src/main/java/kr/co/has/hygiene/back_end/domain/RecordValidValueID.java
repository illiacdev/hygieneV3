package kr.co.has.hygiene.back_end.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RecordValidValueID implements Serializable {
    String name;
    String fk_name;
}
