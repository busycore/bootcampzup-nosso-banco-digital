package com.zupchallenge.SpringBootChallenge.shared.container;

import com.zupchallenge.SpringBootChallenge.shared.providers.CPFValidationProvider.ICPFValidationProvider;
import com.zupchallenge.SpringBootChallenge.shared.providers.CPFValidationProvider.implementations.FakeCPFValidationProvider;
import com.zupchallenge.SpringBootChallenge.shared.providers.FileUploadProvider.IFileUploadProvider;
import com.zupchallenge.SpringBootChallenge.shared.providers.FileUploadProvider.implementations.LocalFileUploadProvider;
import com.zupchallenge.SpringBootChallenge.shared.providers.MailSendProvider.IMailSendProvider;
import com.zupchallenge.SpringBootChallenge.shared.providers.MailSendProvider.implementations.FakeMailSendProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class InjectionTokenConfiguration {
//This configuration should be used as a global injection point. As we might have different implementations according
// ...the needs, we set here what should be injected

    @Bean
    @Scope("singleton")
    public IFileUploadProvider fileUploadProvider(){
        //LocalFileUploadProvider -> Uploads to the system's default /uploads Folder
        //S3FileUploadProvider -> Supposed to upload the file's to Amazon's S3 CDN
        return new LocalFileUploadProvider();
    }

    @Bean
    @Scope("singleton")
    public IMailSendProvider mailSendProvider(){
        //FakeMailSendProvider -> A fake mail provider, it only prints to the console
        //AmazonSESMailProvider -> Supposed to be amazonSES mail provider, it only prints to the console
        return new FakeMailSendProvider();
    }

    @Bean
    @Scope("singleton")
    public ICPFValidationProvider CPFValidationProvider(){
        return new FakeCPFValidationProvider();
    }

}
