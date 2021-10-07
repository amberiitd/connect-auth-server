package com.namber.connect.auth.dao;

import com.namber.connect.auth.model.OAuthClientDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends CrudRepository<OAuthClientDetails, String> {

    @Query("UPDATE oauth_client_details " +
            "SET authorities = ?2 " +
            "WHERE client_id = ?1")
    public void updateClientDetails(String client_id, String authorities);
}
