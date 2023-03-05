package kr.co.has.hygiene.back_end.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entity조직도 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(columnDefinition = "TEXT")
    String stringfyNodeJson;


    String fileName;

    String description;

}
