package com.zupchallenge.SpringBootChallenge.shared.providers.MailSendProvider.implementations;

import com.zupchallenge.SpringBootChallenge.shared.providers.MailSendProvider.IMailSendProvider;

public class AmazonSESMailSendProvider implements IMailSendProvider {

    @Override
    public void sendMail(String from, String to, String message) {
        System.out.println("Email enviado[from:"+from+"][to:"+to+"][message:"+message+"]" );
    }
}
