package com.namber.connect.auth.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name ="oauth_user_details")
@Data
public class OAuthUserDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String userId;
    private String username;
    private String password;
    private String authorities;
}
