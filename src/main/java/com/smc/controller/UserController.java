package com.smc.controller;

import com.smc.model.User;
import com.smc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public String dashboard(Model m, Principal principal){
        String userName = principal.getName ();
        System.out.println ("USERNAME: "+userName);

        // get the user using username(Email)
        User user = userRepository.getUserByUserName (userName);
        System.out.println ("User"+user);

        m.addAttribute ("user", user);
        return "normal/user_dashboard";

    }
}
