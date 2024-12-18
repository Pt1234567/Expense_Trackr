package com.expenseTracker.service;

import com.expenseTracker.entity.User;

import java.util.UUID;

public interface UserService {

     User findUserByJwt(String jwt) throws Exception;

     User findUserByEmail(String email) throws Exception;

     User findUserByUserId(Long userId) throws Exception;

     User updateProfile(Long userId,String userName,String userEmail) throws Exception;
}
