package com.smc.controller;

import com.smc.helper.Message;
import com.smc.model.User;
import com.smc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model m) {
        m.addAttribute ("title", "Home - Smart Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model m) {
        m.addAttribute ("title", "About - Smart Contact Manager");
        return "about";
    }


    @RequestMapping("/signup")
    public String singUp(Model m) {
        m.addAttribute ("title", "Register - Smart Contact Manager");
        m.addAttribute ("user", new User ());
        return "signUp";
    }


    @RequestMapping("/login")
    public String login(Model m) {
        m.addAttribute ("title", "Login - Smart Contact Manager");
        return "login";
    }

    // this handler for registering user:
    @RequestMapping(value="/do_register", method= RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m, HttpSession session){

        try{
            if(!agreement){
                System.out.println ("You have not agreed the terms and conditions");
                throw new Exception("You have not agreed the terms and conditions");
            }


            if(result1.hasErrors()){
                System.out.println ("ERROR "+ result1);
                m.addAttribute ("user", user);
                return "signUp";
            }

            user.setRole ("ROLE_USER");
            user.setEnabled (true);
            user.setImageUrl ("Not yet Set the image!");
            user.setPassword (passwordEncoder.encode (user.getPassword ()));



            System.out.println ("agreement "+ agreement);
            System.out.println ("USER "+ user);

            // by this line we need to save user info to our database:
            User result = this.userRepository.save (user);

            m.addAttribute ("user", new User());


            session.setAttribute ("message", new Message ("Successfully Registered !! ", "alert-success"));
            return "signUp";

        }catch(Exception e){
            e.printStackTrace ();
            m.addAttribute ("user", user);
            session.setAttribute ("message", new Message ("Something went wrong !! " + e.getMessage (), "alert-danger"));
            return "signUp";
        }




    }
}
