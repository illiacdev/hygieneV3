package kr.co.has.hygiene.back_end.graphql;

import kr.co.has.hygiene.back_end.experimant.Member;
import kr.co.has.hygiene.back_end.experimant.MemberRepository;
import kr.co.has.hygiene.back_end.experimant.Team;
import kr.co.has.hygiene.back_end.experimant.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ControllerExperiment {
    final TeamRepository teamRepository;
    final MemberRepository memberRepository;

    @QueryMapping
    List<Team> teams(){
        return teamRepository.findAll();
    }

    @MutationMapping
    Team createTeam(@Argument("name")String name){
        Team team = Team.builder().name(name).build();
        return teamRepository.save(team);
    }

    @MutationMapping
    Team createMember(
            @Argument("teamName")String teamName,
            @Argument("memberName")String memberName){
        Team team1 = teamRepository.findByName(teamName).orElseGet(() -> {
            return teamRepository.save(Team.builder().name(teamName).build());
        });

        Member build = Member.builder().name(memberName).team(team1).build();
        team1.getMembers().add(build);
        teamRepository.save(team1);


        Member member = memberRepository.save(build);
        team1.getMembers().add(member);
        return teamRepository.save(team1);

        /*Optional<Team> byMembersName = teamRepository.findByMembers_Name(teamName);
        byMembersName.ifPresent(team -> {
        Member member = memberRepository.save(Member.builder().name(memberName).team(team).build());
            team.getMembers().add(member);
            teamRepository.save(team);
        });*/
    }
}
