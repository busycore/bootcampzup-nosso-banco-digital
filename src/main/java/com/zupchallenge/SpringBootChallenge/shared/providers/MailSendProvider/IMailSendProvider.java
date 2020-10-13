package com.zupchallenge.SpringBootChallenge.shared.providers.MailSendProvider;

public interface IMailSendProvider {
    void sendMail(String from,String to,String message);
}
