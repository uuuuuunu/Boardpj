package ptp.myboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptp.myboard.domain.Board;


public interface BoardRepository extends JpaRepository<Board,Long> {



}
