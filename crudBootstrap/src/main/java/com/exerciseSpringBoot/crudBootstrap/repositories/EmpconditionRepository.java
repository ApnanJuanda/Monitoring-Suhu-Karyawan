/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.repositories;

import com.exerciseSpringBoot.crudBootstrap.entities.EmpCondition;
import com.exerciseSpringBoot.crudBootstrap.entities.HealtyStatus;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
public interface EmpconditionRepository extends JpaRepository<EmpCondition, Integer>{
    @Modifying
    @Query(value = "SELECT*FROM emp_condition e INNER JOIN healty_status h ON e.idhealty=h.id WHERE e.idemployee =:username", nativeQuery = true)
    List<EmpCondition> findbyUSERNAME(@Param("username") String username);
//    
//    @Query(value = "select ec.date, ec.temperature, hs.information from emp_condition ec inner join healty_status hs ON ec.idhealty=hs.id AND ec.idemployee=?1", nativeQuery = true)
//    List<EmpCondition> findbyUSERNAME(String username);
//    @Query("from emp_condition ec inner join ec.empConditionList ecl  WHERE ec.idemployee =:username")
//    public List<EmpCondition> findbyUSERNAME(@Param("username") String username);
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO emp_condition(idemployee,date,temperature,idhealty) VALUES (?1,?2,?3,?4);",nativeQuery = true)
    void savedata(String idemployee, Date date, float temperature, String idhealty);
//  
    
     @Transactional
    @Modifying
    @Query(value = "INSERT INTO emp_condition(idemployee,date,temperature,idhealty) VALUES (?1,?2,?3,?4);",nativeQuery = true)
    void saveinput(String idemployee, LocalDate date, float temperature, String idhealty);
    
    @Query(value="SELECT COUNT(*) FROM emp_condition where emp_condition.date =?1 AND emp_condition.idemployee=?2", nativeQuery= true)
    int count(Date tanggal, String username);
    
    @Query(value="SELECT COUNT(*) FROM emp_condition where emp_condition.date =?1 AND emp_condition.idemployee=?2", nativeQuery= true)
    int hitung(LocalDate tanggal, String username);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM emp_condition WHERE idempcondition=?1",nativeQuery = true)
    void deletedata(@Param("idempcondition") int idempcondition);
    
//    @Modifying
//    @Query(value = "SELECT*FROM emp_condition WHERE idempcondition =:idempcondition", nativeQuery = true)
//    List<EmpCondition> findbyData(@Param("idempcondition") int idempcondition);
    

    @Transactional
    @Modifying
    @Query(value="UPDATE emp_condition u SET u.idemployee=?1, u.date=?2, u.temperature=?3, u.idhealty=?4 WHERE u.idempcondition=?5",nativeQuery = true)
    void update(String idemployee, Date date, float temperature, String idhealty, int idempcondition);
    
    @Modifying
    @Query(value = "SELECT*FROM emp_condition WHERE emp_condition.temperature > 37", nativeQuery = true)
    List<EmpCondition> findByIlness();
    
    @Modifying
    @Query(value = "SELECT*FROM emp_condition WHERE emp_condition.idemployee=?1 AND MONTH(emp_condition.date)=?2 AND (emp_condition.temperature BETWEEN ?3 AND ?4) ORDER BY emp_condition.date ASC" , nativeQuery = true)
    List<EmpCondition> findBySorting(String id, int month, float temp1, float temp2);
   
//    AND (emp_condition.temperature BETWEEN '?3' AND '?4')
}
