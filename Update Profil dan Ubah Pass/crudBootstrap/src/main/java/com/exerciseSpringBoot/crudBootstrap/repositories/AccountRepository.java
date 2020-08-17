/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.repositories;

import com.exerciseSpringBoot.crudBootstrap.entities.Account;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO accounts(username,password,role) VALUES (?1,?2,?3);",nativeQuery = true)
    void saveAccount(String idemployee, String password, String role);
    
    @Transactional
    @Modifying
    @Query(value="UPDATE accounts u SET u.role=?2 WHERE u.username=?1",nativeQuery = true)
    void updateAccount(String username, String role);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM accounts WHERE username=?1",nativeQuery = true)
    void deleteAccount(@Param("username") String username);
    
    @Transactional
    @Modifying
    @Query(value="UPDATE accounts u SET u.password=?2 WHERE u.username=?1",nativeQuery = true)
    void UbahPass(String username, String password);
    
}
