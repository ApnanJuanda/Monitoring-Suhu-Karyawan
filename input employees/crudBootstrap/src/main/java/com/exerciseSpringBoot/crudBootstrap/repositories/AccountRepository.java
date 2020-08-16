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
}
