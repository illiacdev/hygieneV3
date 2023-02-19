package kr.co.has.hygiene.back_end.domain.소속;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class 부서1 {
    @Id
    Long id;
    String name;

    @OneToMany
    List<직종> 직종s = new ArrayList<>();
 }
