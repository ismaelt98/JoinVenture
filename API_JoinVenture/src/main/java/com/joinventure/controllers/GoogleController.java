package com.joinventure.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.joinventure.entities.RoleUser;
import com.joinventure.entities.User;
import com.joinventure.repositories.UserRepository;
import com.joinventure.services.RoleUserService;
import com.joinventure.services.UserService;

@RestController
@RequestMapping("/google")
public class GoogleController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
	private RoleUserService roleUserService;
    
    @GetMapping
    public RedirectView redirectToGoogleAuthorization() {
        return new RedirectView("/oauth2/authorization/google");
    }

    @GetMapping("/callback")
    public RedirectView currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        Map<String, Object> attributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
        String email = (String) attributes.get("email");
        String firstName = (String) attributes.get("given_name");
        String lastName = (String) attributes.get("family_name");
        
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            //User user = existingUser.get();
            return new RedirectView("http://localhost:8080/users");
        } else {
            User user = new User();
            user.setUsername(firstName);
            user.setAlias(lastName);
            user.setEmail(email);
            user.setPassword(userService.hashSHA256("changethispassword"));
            user.setPhone("999999999");
            RoleUser role = roleUserService.findRoleById((long) 5);
            user.setRoleuser(role);

            userRepository.save(user);
            
            return new RedirectView("http://localhost:8080/users");
        }
    }
}
