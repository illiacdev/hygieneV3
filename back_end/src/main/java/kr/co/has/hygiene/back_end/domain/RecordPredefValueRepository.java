package kr.co.has.hygiene.back_end.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordPredefValueRepository extends JpaRepository<RecordPredefValue, Long> {
    Optional<RecordPredefValue> findByName(String name);

}
