package com.lms.api.core.config.global;

import com.lms.api.auth.config.security.BasicAuthClientInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@Configuration
public class SoapConfig {

    @Value("${credable.username}")
    private String username;

    @Value("${credable.password}")
    private String password;

    @Bean
    public Jaxb2Marshaller transactionMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.lms.generated");
        return marshaller;
    }

    @Bean
    public WebServiceTemplate transactionWebServiceTemplate() {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(transactionMarshaller());
        template.setUnmarshaller(transactionMarshaller());
        template.setDefaultUri("https://trxapitest.credable.io/service/transaction-data");
        template.setInterceptors(new ClientInterceptor[]{
                new BasicAuthClientInterceptor(username, password)
        });
        return template;
    }

    @Bean
    public Jaxb2Marshaller customerMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.lms.generated");
        return marshaller;
    }

    @Bean
    public WebServiceTemplate customerWebServiceTemplate() {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(customerMarshaller());
        template.setUnmarshaller(customerMarshaller());
        template.setDefaultUri("https://kycapitest.credable.io/service/customer");
        template.setInterceptors(new ClientInterceptor[]{
                new BasicAuthClientInterceptor(username, password)
        });
        return template;
    }
}
