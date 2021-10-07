package com.namber.connect.auth.model.request;

import lombok.Data;

@Data
public class ClientDTO {
    private String clientId;
    private String clientSecret;

    private String resourceIds;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private String accessTokenValidity;
    private String refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
}
