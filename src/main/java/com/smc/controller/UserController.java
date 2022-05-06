package com.smc.controller;

import com.smc.model.Contact;
import com.smc.model.User;
import com.smc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public String processContact(@ModelAttribute Contact contact, @RequestParam("profileImage") MultipartFile file, Principal principal){
        try{
            // here we need to find the user:
            String name = principal.getName ();

            // then we need to find the user name:
            User user = userRepository.getUserByUserName (name);

            // processing and uploading file...
            if(file.isEmpty ()){
                // if the file is empty then try our message
                System.out.println ("The File is Empty");
            }else{
                // upload the file to folder and update the name to contact:
                contact.setImage(file.getOriginalFilename ());

                File saveFile = new ClassPathResource ("static/img").getFile ();

                Path path = Paths.get (saveFile.getAbsolutePath () + File.separator + file.getOriginalFilename ());


                Files.copy (file.getInputStream (), path, StandardCopyOption.REPLACE_EXISTING);

                System.out.println ("Image is uploaded!");
            }

            // then add into same user:
            user.getContacts ().add (contact);

            // set user:
            contact.setUser (user);


            // now simply save the user:
            userRepository.save (user);

            System.out.println ("DATA "+ contact);

            System.out.println ("==========Added to data base==========");


        }catch(Exception e){
            System.out.println ("ERROR: "+ e.getMessage ());
            e.printStackTrace ();
        }
        return "normal/add_contact";
    }
}
