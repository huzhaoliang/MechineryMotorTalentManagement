package com.mmt.management.support;

import com.mmt.management.config.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

public class Helper {

    private final static String superUser = "admin";

    public static boolean isSuperUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if(superUser.equals(username)){
            return true;
        }else{
            return false;
        }
    }
}
