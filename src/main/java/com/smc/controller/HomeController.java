package com.smc.controller;

import com.smc.model.Contact;
import com.smc.model.User;
import com.smc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        User user = new User ();
        user.setName("Viaksh");
        user.setEmail ("xyz@gmail.com");
        user.setPassword ("xyz");
        user.setEnabled (true);
        user.setImageUrl ("http://www.wallpaper.com");
        user.setRole ("Admin");
        user.setAbout ("This is user information");

        userRepository.save (user);

        return "Working";
    }
}
