package ptp.myboard.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
@Getter
@Setter
public class Member {

    @Id
    @Column(name = "member_id")
    @NotEmpty(message = "아이디는 필수 입력사항 입니다.")
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


    private String email;

    private String role;

}
