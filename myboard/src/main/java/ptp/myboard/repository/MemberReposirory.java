package ptp.myboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptp.myboard.domain.Member;

public interface MemberReposirory extends JpaRepository<Member,String> {
}
