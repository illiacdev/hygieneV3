package kr.co.has.hygiene.back_end.domain.소속;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class 직종 {
    @Id
    Long id;
    String name;
    @OneToMany
    List<이름> 이름s = new ArrayList<>();
}
