package kr.co.has.hygiene.back_end.domain.소속;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class 이름 {
    @Id
    Long id;
    String name;
}
