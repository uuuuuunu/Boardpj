package ptp.myboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptp.myboard.domain.Member;
import ptp.myboard.repository.MemberReposirory;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberReposirory memberReposirory;
    public Member memberSave(Member member) {
        return memberReposirory.save(member);
    }

    public List<Member> Findmember(Member member) {
        List<Member> findmem = memberReposirory.findAll();
        return findmem;
    }

    public Member findById(String username) {
        return memberReposirory.findById(username).orElse(null);
    }

    public Member login(String id, String password) {
        return memberReposirory.findById(id)
                .filter(e -> e.getPassword().equals(password))
                .orElse(null);
    }
}
