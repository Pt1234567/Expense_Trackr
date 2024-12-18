package com.expenseTracker.controller;

import com.expenseTracker.Config.JwtProvider;
import com.expenseTracker.entity.User;
import com.expenseTracker.repository.UserRepository;
import com.expenseTracker.response.AuthResponse;
import com.expenseTracker.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;


    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {
       User isUserEmailExist=userRepository.findByEmail(user.getEmail());
       if(isUserEmailExist!=null){
           throw new Exception("User already exist");
       }

       User newUser=new User();
       newUser.setName(user.getName());
       newUser.setEmail(user.getEmail());
       newUser.setPassword(user.getPassword());

       User savedUser=userRepository.save(user);

        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= JwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setStatus(true);
        authResponse.setMessage("Resister success");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> login(@RequestBody User user){
         String userName= user.getEmail();
         String password=user.getPassword();

         Authentication authentication=authenicate(userName,password);

         SecurityContextHolder.getContext().setAuthentication(authentication);

         String jwt=JwtProvider.generateToken(authentication);

         AuthResponse authResponse=new AuthResponse();
         authResponse.setMessage("Login Success");
         authResponse.setStatus(true);
         authResponse.setJwt(jwt);

         return new ResponseEntity<>(authResponse,HttpStatus.CREATED);
    }

    private Authentication authenicate(String userName,String password){

        UserDetails userDetails= customUserDetailService.loadUserByUsername(userName);

        if(userDetails==null){
            throw  new BadCredentialsException("Invalid");
        }
        if(!password.equals(userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }
}
