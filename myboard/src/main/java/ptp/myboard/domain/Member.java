package ptp.myboard.domain;


import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class Member {

    @Id
    @Column(name = "member_id")
    @NotEmpty(message = "아이디는 필수 입력사항 입니다.")
    @NotNull(message = "이미 사용하고 있는 ID입니다.")
    @Length(min = 4, max = 12,message = "아이디 설정 범위는 4~12글자 입니다.")
    private String username;

    @NotEmpty(message = "패스워드는 필수 입력사항 입니다.")
    @Length(min = 8 ,message = "비밀번호는 최소 8글자를 입력해주세요.")
    private String password;


    @NotEmpty(message = "이름은 필수 입력사항 입니다.")
    private String name;
    @NotEmpty(message = "닉네임은 필수 입력사항 입니다.")
    private String nickname;

    private String phnum;

    private String orgpassword;

    private String email;

    private String role;

}
