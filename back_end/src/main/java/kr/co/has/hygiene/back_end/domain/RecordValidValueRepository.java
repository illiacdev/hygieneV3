package kr.co.has.hygiene.back_end.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordValidValueRepository extends JpaRepository<RecordValidValue, Long> {
    Optional<RecordValidValue> findByName(String name);

}
