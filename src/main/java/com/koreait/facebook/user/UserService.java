package com.koreait.facebook.user;

import com.koreait.facebook.common.EmailServiceImpl;
import com.koreait.facebook.common.MySecurityUtils;
import com.koreait.facebook.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private EmailServiceImpl email;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private MySecurityUtils secUtils;

    public int join(UserEntity param) {
        String authCd = secUtils.getRandomDigit(5);

        //비밀번호 암호화
        String hashedPw = BCrypt.hashpw(param.getPw(), BCrypt.gensalt());
        param.setPw(hashedPw);
        param.setAuthCd(authCd);
        int result = mapper.join(param);
        if(result == 1 ) { // 메일 전송 (id,authcd값을 메일로 전송)
            // 무언갈 누르면 다시 서버에서 id,authcd값을 되돌려줌
            String subject = "인증 메일입니다";
            String txt = String.format("<a href=\"http://localhost:8090/user/auth?email=%s&authCd=%s\">인증하기</a>", param.getEmail(), authCd);
            email.sendMimeMessage(param.getEmail(), subject, txt);
        }

        return result;
    }
    // email 인증 처리
    public int auth(UserEntity param) {
        return mapper.auth(param);
    }
    public void sendEamil() {
        String to = "durk3501@gmail.com";
        String subject = "제목입니다.";
        String txt = "내용입니다.";
        email.sendSimpleMessage(to,subject,txt);
    }
}
