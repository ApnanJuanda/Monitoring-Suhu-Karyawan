/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.repositories;

import com.exerciseSpringBoot.crudBootstrap.entities.Employee;
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
public interface EmployeeRepository extends JpaRepository<Employee, String>{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO employees(employees.id, employees.idjob, employees.name, employees.group) VALUES (?1,?2,?3,?4);",nativeQuery = true)
    void saveProfil(String idemployee, String idjob, String name, String group);
    
    @Query(value="SELECT COUNT(*) FROM employees where employees.id =?1", nativeQuery= true)
    int check(String idemployee);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM employees WHERE employees.id=?1",nativeQuery = true)
    void deleteEmployee(@Param("username") String username);
    
    @Transactional
    @Modifying
    @Query(value="UPDATE employees e SET e.idjob=?2, e.name=?3, e.group=?4 WHERE e.id=?1",nativeQuery = true)
    void UpdateProfil(String id, String idjobb, String name, String group);
}
