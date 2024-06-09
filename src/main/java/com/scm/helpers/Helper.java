package com.scm.helpers;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.security.Principal;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication){

        if(authentication instanceof OAuth2AuthenticationToken)
        {
            // sign with google,github

            OAuth2AuthenticationToken oAuth2AuthenticationToken =(OAuth2AuthenticationToken) authentication;
            var clientId= oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oAuthUser = (OAuth2User)authentication.getPrincipal();
            String username="";
            if (clientId.equalsIgnoreCase("google"))
            {
                System.out.println("Getting email from Google auth");
                username = oAuthUser.getAttribute("email").toString();
            } else if (clientId.equalsIgnoreCase("github")) {

                System.out.println("Getting email from GitHub");
                username= oAuthUser.getAttribute("email") != null ? oAuthUser.getAttribute("email").toString()
                        : oAuthUser.getAttribute("login").toString() + "@gmail.com";
                System.out.println(username);
            }
            return username;

        }
        else {
            // login by normal process
            System.out.println("Getting email from standard registration process::");
            return authentication.getName();
        }

    }
}
