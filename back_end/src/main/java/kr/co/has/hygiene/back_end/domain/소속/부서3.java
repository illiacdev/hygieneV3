package kr.co.has.hygiene.back_end.domain.소속;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class 부서3 {
    @Id
    Long id;
    String name;

    @OneToMany
    List<부서2> 부서2s = new ArrayList<>();
 }
