package kr.co.has.hygiene.back_end.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class RecordingPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String observeDepartment;
    String observeOccupation;
    String observeName;
    String location;
    String action;
    String subAction;
    String glove;
    LocalDateTime actionStartTime;
    LocalDateTime actionEndTime;
    String actionType;
    Boolean passFail;
    String observer;

}
