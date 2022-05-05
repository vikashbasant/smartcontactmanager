package com.smc.controller;

import com.smc.model.Contact;
import com.smc.model.User;
import com.smc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // method for adding common data to response:
    @ModelAttribute
    public void addCommentData(Model m, Principal principal){
        String userName = principal.getName ();
        System.out.println ("USERNAME: "+userName);

        // get the user using username(Email)
        User user = userRepository.getUserByUserName (userName);
        System.out.println ("User"+user);

        m.addAttribute ("user", user);
    }


    // dashboard home:
    @RequestMapping("/index")
    public String dashboard(Model m, Principal principal){

        m.addAttribute("title", "User Dashboard");
        return "normal/user_dashboard";

    }


    // open add form handler:
    @GetMapping("/add-contact")
    public String openAddContactForm(Model m){
        m.addAttribute("title", "Add Contact");
        m.addAttribute ("contact", new Contact ());
        return "normal/add_contact";
    }


    // Processing and contact form
    @PostMapping("/process-contact")
    public String processContact(@ModelAttribute Contact contact, Principal principal){
        // here we need to find the user:
        String name = principal.getName ();

        // then we need to find the user name:
        User user = userRepository.getUserByUserName (name);

        // set user:
        contact.setUser (user);

        // then add into same user:
        user.getContacts ().add (contact);
        // now simply save the user:
        userRepository.save (user);

        System.out.println ("DATA "+ contact);

        System.out.println ("==========Added to data base==========");

        return "normal/add_contact";
    }
}
