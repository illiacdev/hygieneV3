package kr.co.has.hygiene.back_end.experimant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberID implements Serializable {
    String team;
    String name;
}
