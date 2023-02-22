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
    String observeDepartment;//부서
    String observeOccupation;//직종
    String observeName; //성명
    String location; //관찰장소
    String action; //행위
    String subAction; //세부행위
    Boolean glove; //장갑
    LocalDateTime actionStartTime; //시작시간
//    LocalDateTime actionEndTime;
    Integer actionDuration; //수행시간
    String actionType; //수행여부 - 손소독,손세척,미수행.
    Boolean passFail; //수행적합성
    String observer; //관찰자

}
