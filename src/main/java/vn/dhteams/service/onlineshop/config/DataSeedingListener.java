package vn.dhteams.service.onlineshop.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import vn.dhteams.service.onlineshop.domain.OauthClientDetails;
import vn.dhteams.service.onlineshop.domain.Role;
import vn.dhteams.service.onlineshop.domain.User;
import vn.dhteams.service.onlineshop.repository.OauthClientDetailsRepository;
import vn.dhteams.service.onlineshop.repository.RoleRepository;
import vn.dhteams.service.onlineshop.repository.UserRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
        
        if (roleRepository.findByName("ROLE_CLIENT") == null) {
            roleRepository.save(new Role("ROLE_CLIENT"));
        }

        if (roleRepository.findByName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_CUSTOMER"));
        }
        
        if (roleRepository.findByName("ROLE_GUEST") == null) {
            roleRepository.save(new Role("ROLE_GUEST"));
        }

        // Admin account
        if (userRepository.findByUserName("admin") == null) {
            User admin = new User();
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("One23456!"));
            admin.setFullName("Administrator");
            admin.setTrangThai("active");
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            roles.add(roleRepository.findByName("ROLE_CUSTOMER"));
            roles.add(roleRepository.findByName("ROLE_CLIENT"));
            roles.add(roleRepository.findByName("ROLE_GUEST"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // Member account
        if (userRepository.findByUserName("member") == null) {
            User user = new User();
            user.setUserName("member");
            user.setPassword(passwordEncoder.encode("One23456!"));
            user.setFullName("Member");
            user.setTrangThai("active");
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_CUSTOMER"));
            user.setRoles(roles);
            userRepository.save(user);
        }
        
        // Oauth2 client
        if(oauthClientDetailsRepository.findByClientId("devclient") == null) {
        	OauthClientDetails oauthClientDetails = new OauthClientDetails();
        	oauthClientDetails.setClientId("devclient");
        	oauthClientDetails.setClientSecret(passwordEncoder.encode("One23456!"));
        	oauthClientDetails.setScope("read,write,trust");
        	oauthClientDetails.setAuthorizedGrantTypes("password,refresh_token");
        	oauthClientDetails.setAuthorities("ROLE_CLIENT,ROLE_TRUSTED_CLIENT");
        	oauthClientDetails.setAccessTokenValidity(900);
        	oauthClientDetails.setRefreshTokenValidity(2592000);
        	oauthClientDetailsRepository.save(oauthClientDetails);
        }
        if(oauthClientDetailsRepository.findByClientId("client") == null) {
        	OauthClientDetails oauthClientDetails = new OauthClientDetails();
        	oauthClientDetails.setClientId("client");
        	oauthClientDetails.setClientSecret(passwordEncoder.encode("One23456!"));
        	oauthClientDetails.setScope("read,write,trust");
        	oauthClientDetails.setAuthorizedGrantTypes("password,client_credentials");
        	oauthClientDetails.setAuthorities("ROLE_CLIENT,ROLE_TRUSTED_CLIENT");
        	oauthClientDetails.setAccessTokenValidity(900);
        	oauthClientDetails.setRefreshTokenValidity(2592000);
        	oauthClientDetailsRepository.save(oauthClientDetails);
        }
    }

}
