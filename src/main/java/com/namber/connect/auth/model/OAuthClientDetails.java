package com.namber.connect.auth.model;

import lombok.Data;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name ="oauth_client_details")
@Data
public class OAuthClientDetails{

    @Id
    private String clientId;

    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private String accessTokenValidity;
    private String refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
}
