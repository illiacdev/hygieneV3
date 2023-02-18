package kr.co.has.hygiene.back_end.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecordTypeRepository extends JpaRepository<RecordType, Long> {
    Optional<RecordType> findByName(String name);
}
