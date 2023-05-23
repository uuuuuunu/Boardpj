package ptp.myboard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
@Getter
@Setter
public class Image {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "image_id")
    //private Long imageId;
    //@ManyToOne
    //@JoinColumn(name = "board_id")
    //private

    @Column(nullable = false)
    @Id
    private String OrgImageName;
    @Column(nullable = false)
    private String imagePath;
}
