package kr.co.has.hygiene.back_end.graphql;

import kr.co.has.hygiene.back_end.domain.RecordType;
import kr.co.has.hygiene.back_end.domain.RecordTypeRepository;
import kr.co.has.hygiene.back_end.experimant.Member;
import kr.co.has.hygiene.back_end.experimant.MemberRepository;
import kr.co.has.hygiene.back_end.experimant.Team;
import kr.co.has.hygiene.back_end.experimant.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ControllerExperimentTest {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RecordTypeRepository recordTypeRepository;
    @Transactional
    @Test
    void name() {

        String teamName = "Dev";
        String memberName = "illiac";


        Team team1 = teamRepository.findByName(teamName).orElseGet(() -> {
            return teamRepository.save(Team.builder().name(teamName).build());
        });

        Member build = Member.builder().name(memberName).team(team1).build();
//        team1.getMembers().add(build);
//        teamRepository.save(team1);


        Member member = memberRepository.save(build);
        team1.getMembers().add(member);
        Team save = teamRepository.save(team1);

        teamRepository.findByName(teamName).ifPresent(team -> {
//            System.out.println(team.getMembers());
        });

//        System.out.println(save);
//        System.out.println(save.getMembers());

    }

    @Test
    void nameTypes() {
        List<RecordType> byNameIn = recordTypeRepository.findByNameIn(Arrays.asList("부서", "직종", "이름", "장소", "행위", "장갑", "수행시작시간", "수행종료시간", "수행여부"));
        System.out.println(byNameIn);
    }
}