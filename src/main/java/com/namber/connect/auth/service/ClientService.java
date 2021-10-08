package com.namber.connect.auth.service;

import com.namber.connect.auth.dao.ClientDetailsRepository;
import com.namber.connect.auth.model.OAuthClientDetails;
import com.namber.connect.auth.model.request.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    public void register(ClientDTO client) {
        if (!clientDetailsRepository.findById(client.getClientId()).isPresent()) {
            client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
            clientDetailsRepository.save(mapper.map(client, OAuthClientDetails.class));
        }
    }

    public void update(ClientDTO client) {
        OAuthClientDetails clientDetails = clientDetailsRepository.findById(client.getClientId()).orElse(null);

        if (clientDetails != null) {
            if(client.getAccessTokenValidity() != null) {
                clientDetails.setAccessTokenValidity(client.getAccessTokenValidity());
            }
            clientDetails.setAdditionalInformation(
                    jointTokens(clientDetails.getAdditionalInformation(),
                            client.getAdditionalInformation())
            );
            clientDetails.setAuthorities(
                    jointTokens(clientDetails.getAuthorities(),
                            client.getAuthorities()) );
            clientDetails.setAuthorizedGrantTypes(
                    jointTokens(clientDetails.getAuthorizedGrantTypes(),
                    client.getAuthorizedGrantTypes())
            );
            if(client.getAccessTokenValidity() != null) {
                clientDetails.setRefreshTokenValidity(client.getRefreshTokenValidity());
            }
            clientDetails.setResourceIds(
                    jointTokens(clientDetails.getResourceIds(),
                    client.getResourceIds())
            );
            clientDetails.setScope(
                    jointTokens(clientDetails.getScope(),
                    client.getScope())
            );
            if(client.getAccessTokenValidity() != null) {
                clientDetails.setWebServerRedirectUri(client.getWebServerRedirectUri());
            }

            clientDetailsRepository.save(clientDetails);
        }
    }

    public void removeClient(String clientId) {
        if(clientDetailsRepository.findById(clientId).isPresent()){
            clientDetailsRepository.deleteById(clientId);
        }
    }

    private String jointTokens(String str1, String str2) {
        String DefaultDelim = ",";
        return jointTokens(str1, str2, DefaultDelim);
    }
    private String jointTokens(String str1, String str2, String delim) {
        if (str1 == null ){
            return str2;
        }else if(str2 == null){
            return str1;
        }else{
            return str1+ delim +str2;
        }
    }
}
