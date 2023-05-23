package ptp.myboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Image;

public interface ImageRepository extends JpaRepository<Image, String> {
}
