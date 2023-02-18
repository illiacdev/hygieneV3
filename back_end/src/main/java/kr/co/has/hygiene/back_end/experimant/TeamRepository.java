package kr.co.has.hygiene.back_end.experimant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findByName(String name);
//    Optional<Team> findByMembers_Name(String name);


}