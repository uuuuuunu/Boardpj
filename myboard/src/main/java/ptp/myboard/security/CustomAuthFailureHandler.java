package ptp.myboard.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errormsg;
        if (exception instanceof BadCredentialsException) {
            errormsg = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
        } else {
            errormsg = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의해주세요.";
        }
        errormsg = URLEncoder.encode(errormsg, "UTF-8");
        setDefaultFailureUrl("/yw/login??error=true&exception=" + errormsg);
        super.onAuthenticationFailure(request,response,exception);
    }
}
