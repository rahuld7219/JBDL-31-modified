package com.example;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    // This method returns name and company fields from the logged in user details
    @RequestMapping("/user")
    public Map<String, Object> user() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> answer = new HashMap<>();
        answer.put("name", principal.getAttribute("name"));
        answer.put("company", principal.getAttribute("company"));
        return answer;
//		return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    // This method returns all the user details(logged in user) that the application(client)
    // is able to access from authorization server(Github, etc.). We can put these details in database also.
    @RequestMapping("/user_details")
    public OAuth2User getUserDetails(@AuthenticationPrincipal OAuth2User principal){
        return principal;
    }
    // @AuthenticationPrincipal is same as using SecurityContextHolder.getContext().getAuthentication()
    // to get the logged in user context(session)
}
