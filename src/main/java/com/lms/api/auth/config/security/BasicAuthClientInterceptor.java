package com.lms.api.auth.config.security;

import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpUrlConnection;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthClientInterceptor implements ClientInterceptor {

    private final String username;
    private final String password;

    public BasicAuthClientInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean handleRequest(MessageContext messageContext) {
        TransportContext context = TransportContextHolder.getTransportContext();
        WebServiceConnection connection = context.getConnection();

        if (connection instanceof HttpUrlConnection httpConnection) {
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            httpConnection.getConnection().setRequestProperty("Authorization", "Basic " + encodedAuth);
        }

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) {
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) {
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) {
        // No implementation needed
    }
}

