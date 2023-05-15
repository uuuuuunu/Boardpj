package ptp.myboard.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginMermber {
    @NotEmpty(message = "아이디를 입력해주세요.")
    String loginId;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    String password;
}
