package kr.co.has.hygiene.back_end.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RecordTypeRepository extends JpaRepository<RecordType, String> {
    @Override
    @EntityGraph(attributePaths = {"recordValidValues"})
    List<RecordType> findAll();

    @EntityGraph(attributePaths = {"recordValidValues"})
    Optional<RecordType> findByName(String name);

    List<RecordType> findByNameIn(Collection<String> names);



}
