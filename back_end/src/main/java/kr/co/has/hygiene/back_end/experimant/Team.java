package kr.co.has.hygiene.back_end.experimant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    String name;

    @OneToMany
    List<Member> members = new ArrayList<>();

}
