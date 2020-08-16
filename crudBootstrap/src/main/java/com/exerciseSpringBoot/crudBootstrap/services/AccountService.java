/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.services;

import com.exerciseSpringBoot.crudBootstrap.entities.Account;
import com.exerciseSpringBoot.crudBootstrap.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class AccountService {
    
    @Autowired
    AccountRepository account;
    
     public String getpass(String username){
        return account.findById(username).get().getPassword();
    }
    
    public String getrole(String username){
        return account.findById(username).get().getRole();
    }
    
    public boolean checkusername(String username){
        return account.existsById(username);
    }
    
    public Account getbyUsername(String username){
        return account.findById(username).get();
    }
}
