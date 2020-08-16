/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.controllers;

import com.exerciseSpringBoot.crudBootstrap.entities.Account;
import com.exerciseSpringBoot.crudBootstrap.entities.EmpCondition;
import com.exerciseSpringBoot.crudBootstrap.entities.Employee;
import com.exerciseSpringBoot.crudBootstrap.entities.HealtyStatus;
import com.exerciseSpringBoot.crudBootstrap.entities.Profile;
import com.exerciseSpringBoot.crudBootstrap.entities.Sorting;
import com.exerciseSpringBoot.crudBootstrap.services.AccountService;
import com.exerciseSpringBoot.crudBootstrap.services.EmpConditionService;
import com.exerciseSpringBoot.crudBootstrap.services.EmployeeService;
import com.exerciseSpringBoot.crudBootstrap.services.JobService;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class AppsController {
    @Autowired
    private EmpConditionService empConditionService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private JobService jobService;
    
    //start of pageLogin
    @GetMapping("")
    public String awal(Model model) {
        model.addAttribute("account", new Account());
        return "index";
    }
    
    @GetMapping("/index")
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "index";
    }
    
    //mengecek isi dari form login, apakah sesuai di database 
    @RequestMapping("/check")
    public String checkLogin(@ModelAttribute(name = "account") Account account, Model model) {

        String username = account.getUsername();
        String password = account.getPassword();

        if (accountService.checkusername(username)) {
            if (password.equalsIgnoreCase(accountService.getpass(username))) {
                String role = accountService.getrole(username);
                if (role.equalsIgnoreCase("Karyawan")) {
                    return "redirect:/pagekaryawan/" + username; 
                } else {
                    return "redirect:/pageadmin";
                }
            } else {
                model.addAttribute("loginError", true);
                return "index";
            }
        } else {
            model.addAttribute("loginError", true);
            return "index";
        }
    }
    
    //end of pageLogin
    
    
    //start of pageadmin
    @GetMapping("/pageadmin")
    public String kondisi(Model model) {
        model.addAttribute("sorting", new Sorting());
        model.addAttribute("conditions", empConditionService.getAll());
        model.addAttribute("data", employeeService.getAll());
        return "pageadmin";
    }
    
    @GetMapping("/pageilness")
    public String ilness(Model model){
        model.addAttribute("ilness", empConditionService.getByIlness());
        return "pageilness";
    }
    
    @GetMapping("/pageprofil")
    public String profil(Model model){
        model.addAttribute("profile", new Profile());
        model.addAttribute("job", jobService.getAll());
        model.addAttribute("profil", employeeService.getAll());
        return "pageprofil";
    }
    
    @PostMapping("/saveProfil")
    public String saveProfil(@ModelAttribute(name = "profile") Profile profile, Model model){
        String idemployee = profile.getIdemployee();
        String idjob = profile.getIdjob();
        String name = profile.getName();
        String group = profile.getGroup();
        String role = profile.getRole();
        String password = "12345";
       
        
//        if(employeeService.check(idemployee)){
//            model.addAttribute("profil", employeeService.getAll());
//            return "redirect:/pageprofil";
//        }else{
//            employeeService.saveProfil(idemployee, idjob, name, group);
//            accountService.saveAccount(idemployee, password, role);
//            
//        }
      employeeService.saveProfil(idemployee, idjob, name, group);
      accountService.saveAccount(idemployee, password, role);
      return "redirect:/pageprofil";
    }
    
    @RequestMapping("/pageakun")
    public ModelAndView pageakun(Model model){
        ModelAndView mav = new ModelAndView("pageakun");
        mav.addObject("accountt", new Account());
        model.addAttribute("akun", accountService.getAll());
        return mav;
    }
    
    @GetMapping("/sort")
    public String sort(@ModelAttribute(name = "sorting") Sorting sorting, Model model){
        String id = sorting.getIdemployee();
        int month = sorting.getMonth();
        float temp1 = sorting.getTemp1();
        float temp2 = sorting.getTemp2();
        model.addAttribute("sorting", new Sorting());
        model.addAttribute("conditions", empConditionService.getAll());
        model.addAttribute("data", employeeService.getAll());
        model.addAttribute("sortt", empConditionService.getBySorting(id, month, temp1, temp2));
        return "pagesorting";
    }
    
    @GetMapping("/pagesorting")
    public String pagesorting(Model model) {
        model.addAttribute("sorting", new Sorting());
        model.addAttribute("conditions", empConditionService.getAll());
        model.addAttribute("data", employeeService.getAll());
        return "pagesorting";
    }
    
   
    
//    @PostMapping("/saveAccount")
//    public String saveAcoount(@Valid Account accountt){
//        accountService.save(accountt);
//        return "redirect:/pageakun";
//    }
    

//end of pageadmin
    
    
    //start of pagekaryawan
    @RequestMapping("/pagekaryawan/{username}")
    public ModelAndView pagekaryawan(Model model, @PathVariable(name = "username") String username){
        ModelAndView mav = new ModelAndView("pagekaryawan");
        mav.addObject("empCondition", new EmpCondition());
        mav.addObject("dAccount", new Account());
        mav.addObject("dEmployee", new Employee());
        mav.addObject("emp", employeeService.getbyusername(username)); //untuk keperluan profil
        //mav.addObject("akun", accountService.getbyUsername(username));//untuk keperluan ubah password
        mav.addObject("empcondition", empConditionService.getByUsername(username)); //untuk keperluan datatables
        return mav;
    }
    
    @PostMapping("/saveinput/{username}")
    public String saveinput(@ModelAttribute(name = "empCondition") EmpCondition empCondition, @PathVariable(name = "username") String username, Model model){
        float temperature = empCondition.getTemperature();
        LocalDate tanggal = java.time.LocalDate.now();
          if(empConditionService.checktanggal2(tanggal, username)){
              model.addAttribute("empcondition", empConditionService.getByUsername(username));
              return "redirect:/pagegagal/" + username;
          }
          else{
           if(temperature<36.5){
               String idkes = "S01";
               empConditionService.saveinput(username, tanggal, temperature, idkes);
           }
           else if(temperature<37.5 && temperature>=36.5){
               String idkes = "S02";
               empConditionService.saveinput(username, tanggal, temperature, idkes);
           }
           else{
               String idkes = "S03";
               empConditionService.saveinput(username, tanggal, temperature, idkes);
           }
          } 

      return "redirect:/pagekaryawan/" + username;
    }
    
//    @PostMapping("/saveinput/{username}")
//    public String saveinput(@ModelAttribute(name = "empCondition") EmpCondition empCondition, @PathVariable(name = "username") String username, Model model){
//        float temperature = empCondition.getTemperature();
//        LocalDate tanggal = java.time.LocalDate.now();
//          
//           if(temperature<36.5){
//               String idkes = "S01";
//               empConditionService.saveinput(username, tanggal, temperature, idkes);
//           }
//           else if(temperature<37.5 && temperature>=36.5){
//               String idkes = "S02";
//               empConditionService.saveinput(username, tanggal, temperature, idkes);
//           }
//           else{
//               String idkes = "S03";
//               empConditionService.saveinput(username, tanggal, temperature, idkes);
//           }
//          
//
//      return "redirect:/pagekaryawan/" + username;
//    }
    @RequestMapping("/pagegagal/{username}")
    public ModelAndView pagegagal(Model model, @PathVariable(name = "username") String username){
        ModelAndView mav = new ModelAndView("pagegagal");
        mav.addObject("empCondition", new EmpCondition());
        mav.addObject("dAccount", new Account());
        mav.addObject("dEmployee", new Employee());
        mav.addObject("emp", employeeService.getbyusername(username)); //untuk keperluan profil
        mav.addObject("akun", accountService.getbyUsername(username));//untuk keperluan ubah password
        mav.addObject("empcondition", empConditionService.getByUsername(username)); //untuk keperluan datatables
        return mav;
    }
    
    @RequestMapping("/pageedit/{idempcondition}/{username}")
     public ModelAndView pageedit(@PathVariable(name = "idempcondition") int idempcondition, @PathVariable(name = "username") String username){
         ModelAndView mav = new ModelAndView("pageedit");
         mav.addObject("emp", employeeService.getbyusername(username));
         EmpCondition empCondition = empConditionService.get(idempcondition);
         mav.addObject("empCondition", empCondition);
         return mav;
     }
    
    @PostMapping("/update/{username}/{idempcondition}")
    public String update(@ModelAttribute(name = "empCondition") EmpCondition empCondition,@PathVariable(name = "username") String username, @PathVariable(name = "idempcondition") int idempcondition, Model model) {

       Date date = empCondition.getDate();
       Date tanggal = new Date(date.getTime()+ TimeUnit.DAYS.toMillis(1));
       float temperature = empCondition.getTemperature();
       
        if(empConditionService.checktanggal(tanggal, username)){
            //model.addAttribute("emp", empConditionService.getByUsername(username));
            //return "gagal";
            model.addAttribute("Error", true);
            return "gagal";
       }else{
           if(temperature<36.5){
               String idkes = "S01";
               empConditionService.update(username, tanggal, temperature, idkes, idempcondition);
           }
           else if(temperature<37.5 && temperature>=36.5){
               String idkes = "S02";
               empConditionService.update(username, tanggal, temperature, idkes, idempcondition);
           }
           else{
               String idkes = "S03";
               empConditionService.update(username, tanggal, temperature, idkes, idempcondition);
           }
           
       }
      return "redirect:/pagekaryawan/" + username;
       
    }
    
    
    
    @GetMapping("/delete/{idempcondition}/{idemployee}")
    public String delete(@PathVariable(name = "idempcondition") int idempcondition, @PathVariable(name = "idemployee") String idemployee) {
        String username = idemployee;
        empConditionService.deletedata(idempcondition);
        return "redirect:/pagekaryawan/" + username;
    }
    
    //end of pagekaryawan
    
    
    
}
