package ptp.myboard.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @NotEmpty(message = "제목을 입력하세요")
    @Column(length = 30)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Image> image;
    @Min(value = 1000, message = "최소 입력금액은 1000원입니다.")
    private int price;
    @NotEmpty(message = "내용을 입력하세요")
    @Column(length = 200)
    private String cont;


    @CreationTimestamp
    @Column(name = "time")
    private LocalDate creatAt;

    private String sts;

    public Integer hit=0;

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", member=" + member +
                ", price=" + price +
                ", cont='" + cont + '\'' +
                ", creatAt=" + creatAt +
                ", sts='" + sts + '\'' +
                ", hit=" + hit +
                '}';
    }
}
