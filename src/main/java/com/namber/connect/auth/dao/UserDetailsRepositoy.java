package com.namber.connect.auth.dao;

import com.namber.connect.auth.model.OAuthUserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepositoy extends CrudRepository<OAuthUserDetails, String> {
    public OAuthUserDetails findByUsername(String username);

    @Query("UPDATE oauth_user_details " +
            "SET authorities = ?2 " +
            "WHERE user_id = ?1")
    public void updateUserDetails(String userId, String authorities);
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public User fetchByUserName(String username){
//        if (username == null){
//            return null;
//        }
//        List<User> users = new ArrayList<>();
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("USER"));
//
//        users.add(new User("amber", passwordEncoder.encode("azu"), authorities));
//        users.add(new User("user", passwordEncoder.encode("password"), authorities));
//        users = users.stream().filter(user -> username.equals(user.getUsername())).collect(Collectors.toList());
//        if (users.size() > 0){
//            return users.get(0);
//        }else{
//            return null;
//        }
//    }
}
