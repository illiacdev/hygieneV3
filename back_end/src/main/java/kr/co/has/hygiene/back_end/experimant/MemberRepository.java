package kr.co.has.hygiene.back_end.experimant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, MemberID> {
}