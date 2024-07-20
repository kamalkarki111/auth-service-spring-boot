package ecomm.com.example.ecomm.Routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecomm.com.example.ecomm.Modals.User;
import ecomm.com.example.ecomm.Repos.UserRepo;
import ecomm.com.example.ecomm.requests.SigninRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*; 

@RestController
@RequestMapping
public class AuthRoute {

    @Autowired 
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("signup")
    public User signUp(@Valid @RequestBody User user) {

        boolean exists = userRepo.existsByEmail(user.getEmail()) ||  userRepo.existsById(user.getUserName());

        if(!exists) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        } else {
            return null;
        }
    }

    @PostMapping("signin")
    public String signIn(@Valid @RequestBody SigninRequest user) {

        User foundUser = userRepo.findUserByEmail(user.getEmail());

        if(foundUser != null) {

            if(passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                return new String("Hola sign in Success "+ foundUser.getPassword() );
            } else {
                return new String("Hola sign in Failed");
            }
            
        } else {
            return new String("Hola sign in Failed");
        }
    }
    
    
}
