package ptp.myboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ptp.myboard.domain.Member;
import ptp.myboard.repository.MemberReposirory;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberReposirory memberReposirory;
    public Member memberSave(Member member) {
        if(findByIdBoolean(member.getUsername())==true){
            return memberReposirory.save(member);
        }else{
            member.setUsername(null);
            return null;
        }
    }

    public List<Member> Findmember(Member member) {
        List<Member> findmem = memberReposirory.findAll();
        return findmem;
    }

    public Member findById(String username) {
        return memberReposirory.findById(username).orElse(null);
    }


    public boolean findByIdBoolean(String username){
        if(findById(username)==null){
            return true;
        }
        else{
            return false;
        }
    }

    public Member login(String id, String password) {
        return memberReposirory.findById(id)
                .filter(e -> e.getPassword().equals(password))
                .orElse(null);
    }

}
