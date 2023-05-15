package ptp.myboard.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ptp.myboard.domain.Member;
import ptp.myboard.service.MemberService;

@Service
@Slf4j
public class PrincipalDetailService implements UserDetailsService {

    private MemberService memberService;

    @Autowired
    public PrincipalDetailService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal=memberService.findById(username);
        if(principal!=null){
            return new PrincipalDetail(principal);
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}
