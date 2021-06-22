package com.koreait.facebook;

import com.koreait.facebook.common.MySecurityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 이게 있어야지 톰캣이 자동 실행된다
public class FacebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacebookApplication.class, args);
    }
}
