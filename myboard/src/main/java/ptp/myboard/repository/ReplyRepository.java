package ptp.myboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptp.myboard.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
