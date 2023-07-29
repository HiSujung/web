package com.hisujung.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@PropertySource("classpath:application.properties")
@Configuration
public class EmailConfig {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String id;

    @Value("${spring.mail.password}")
    private String password;

//    @Value("${spring.mail.properties.mail.smtp.auth}")
//    private boolean auth;
//
//    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
//    private boolean starttlsEnable;
//
//    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
//    private boolean starttlsRequired;
//
//    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
//    private int connectionTimeout;
//
//    @Value("${spring.mail.properties.mail.smtp.timeout}")
//    private int timeout;
//
//    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
//    private int writeTimeout;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host); //smtp 서버 주소
        mailSender.setPort(port); //설정(발신) 메일 아이디
        mailSender.setUsername(id); //설정(발신) 메일 패스워드
        mailSender.setPassword(password); //smtp port
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(getMailProperties()); //메일 인증서버 정보

        return mailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp"); //프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true"); //smtp인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); //smtp starttls 사용
        properties.setProperty("mail.debug", "true"); //디버그 사용
        properties.setProperty("mail.smtp.ssl.trust", host); //ssl 인증 서버 주소
        properties.setProperty("mail.smtp.ssl.enable", "true"); //ssl 사용

        return properties;
    }
}
