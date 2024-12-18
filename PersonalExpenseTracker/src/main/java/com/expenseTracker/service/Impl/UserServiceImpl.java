package com.expenseTracker.service.Impl;

import com.expenseTracker.Config.JwtProvider;
import com.expenseTracker.entity.User;
import com.expenseTracker.repository.UserRepository;
import com.expenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email=jwtProvider.generateUserEmailFromToken(jwt);
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw  new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserByUserId(Long userId) throws Exception {
        User user=userRepository.findById(userId).orElse(null);
        if(user==null){
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User updateProfile(Long userId, String userName, String userEmail) throws Exception {
        User user=userRepository.findById(userId).orElse(null);
        if(user==null) throw new Exception("user not found");
        user.setName(userName);
        user.setEmail(userEmail);
        return userRepository.save(user);
    }
}
