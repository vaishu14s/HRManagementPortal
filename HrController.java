package com.hr.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hr.entity.Compose;
import com.hr.entity.CreatePost;
import com.hr.entity.Employee;
import com.hr.repository.ComposeRepo;
import com.hr.repository.CreatePostRepo;
import com.hr.repository.EmployeeRepo;
import com.hr.service.HrService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HrController {

    @Autowired
    private HrService hrService;

    @Autowired
    private CreatePostRepo createPostRepo;

    @Autowired
    private EmployeeRepo employeeRepo;
    
    @Autowired
    private ComposeRepo composeRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @GetMapping("/home")
    public String home(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        System.out.println("UserName and Password: " + username + "-" + password);

        String empId = username;

        if (empId.length() > 0) {
            try {
                Employee employee = employeeRepo.findByIdAndPassword(Integer.parseInt(empId), password);

                if (employee != null) {
                    session.setAttribute("userId", Integer.parseInt(empId));
                    session.setAttribute("name", employee.getEmpName());
                    session.setAttribute("desg", employee.getDesignation());
                    model.addAttribute("error", false);
                    
                    if(employee.getRole().equals("USER")) {
                    	return "user-dashboard";
                    }
                    else if(employee.getRole().equals("ADMIN")) {
                    	List<Compose> findAll = composeRepo.findAll();
        		    	System.out.println("List"+findAll);
        		    	findAll.stream()
        		    	.forEach(k->{
        		    		int id=k.getParentUkid();
        		    		String designation = employeeRepo.findById(id).get().getDesignation();
        		    		
        		    		k.setPosition(designation);
        		    	});
        		    	
        		    	model.addAttribute("statusList", findAll);
                    	return "dashboard";
                    	
                    }else {
                    	return "redirect:/login";
                    }
                   
                } else {
                    model.addAttribute("error", true);
                    return "login";
                }
            } catch (NumberFormatException e) {
                System.out.println("Error parsing empId: " + e.getMessage());
                model.addAttribute("error", true);
                return "login";
            }
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashBoard() {
    	
        return "dashboard";
    }

    @GetMapping("/add-employee")
    public String addEmployee() {
        return "add-employee";
    }

    @GetMapping("/all-employee")
    public String allEmployee(Model model) {
        List<Employee> allEmployee = hrService.getAllEmployee();
        model.addAttribute("allEmployee", allEmployee);
        return "all-employee";
    }

    @GetMapping("/create-post")
    public String createPost(Model model) {
        List<CreatePost> findAll = createPostRepo.findAll();
        model.addAttribute("post", findAll);
        return "create-post";
    }

    @GetMapping("/status")
    public String status(Model model) {
    	
    	List<Compose> findAll = composeRepo.findAll();
    	
    	findAll.stream()
    	.forEach(k->{
    		int id=k.getParentUkid();
    		String designation = employeeRepo.findById(id).get().getDesignation();
    		
    		k.setPosition(designation);
    	});
    	
    	
    	model.addAttribute("statusList", findAll);
        return "status";
    }

    @GetMapping("/my-profile")
    public String myProfile(HttpSession session, Model model) {
        Object attribute = session.getAttribute("userId");

        if (attribute == null) {
            System.out.println("User is not logged in. Redirecting to login.");
            return "redirect:/login";
        }

        try {
            int userId = Integer.parseInt(attribute.toString());
            Employee employee = employeeRepo.findById(userId).orElse(null);

            if (employee == null) {
                System.out.println("Employee not found for userId: " + userId);
                return "redirect:/login";
            }

            model.addAttribute("employee", employee);
            return "my-profile";

        } catch (NumberFormatException e) {
            System.out.println("Invalid userId format: " + e.getMessage());
            return "redirect:/login";
        }
    }

    

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employee.setPassword(employee.getDob());
        hrService.addEmployee(employee);
        return "redirect:/all-employee";
    }

    @PostMapping("/save-post")
    public String savePost(@ModelAttribute CreatePost createPost, Model model) {
        createPost.setCreatedDate(new Date());
        hrService.addPost(createPost);
        return "redirect:/create-post?success";
    }

    @PostMapping("/update-password")
    public String updatePassword(
            @RequestParam("password") String password,
            @RequestParam("newPassword1") String newPassword1,
            @RequestParam("newPassword2") String newPassword2,
            Model model,HttpSession session) {

    	System.out.println(password+" - "+newPassword1+" - "+newPassword2);
        Object attribute = session.getAttribute("userId");
        int userId = Integer.parseInt(attribute.toString());
        Employee employee = employeeRepo.findByIdAndPassword(userId,password);


        // Check if new passwords match
        if (employee != null && newPassword1.equals(newPassword2)) {
        	
        	employee.setPassword(newPassword2);
        	employeeRepo.save(employee);
        	 model.addAttribute("error", false);
        }else {
        	
        	model.addAttribute("error", true);
        	return "setting";
        }
        
        return "redirect:/login";
    }

    @GetMapping("/setting")
    public String showSettingsPage() {
        return "setting"; // This points to the Thymeleaf template "settings.html"
    }

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/edit-record")
    public String editRecord(@RequestParam("id") int id, Model model) {

    	System.out.println("Id: "+id);
    	Employee employee = employeeRepo.findById(id).get();
    	model.addAttribute("employee", employee);
    	
        return "edit-record";  
    }



    @PostMapping("/edit-employee")
    public String updateRecord(@ModelAttribute Employee employee) {
    	
    	int id = employee.getId();
    	Employee getEmp = employeeRepo.findById(id).get();
    	
    	if(getEmp != null) {
    		employeeRepo.save(employee);
    		
    	}
		return "redirect:/all-employee"; 
    	
    }

    @DeleteMapping("/deleteRecord-byId")
    public ResponseEntity<String> deleteRecordById(@RequestParam("id") int id) {
        try {
            employeeRepo.deleteById(id);
            return ResponseEntity.ok("Record deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting record");
        }
    }
    
    
// User-Account...
    
    @GetMapping("/user-dashboard")
    public String userDashboard(Model model,HttpSession session) {
    	
    	Object attribute = session.getAttribute("userId");
        int userId = Integer.parseInt(attribute.toString());

    	
		List<Compose> findAll = composeRepo.findByParentUkid(userId);
		    	
		    	findAll.stream()
		    	.forEach(k->{
		    		int id=k.getParentUkid();
		    		String designation = employeeRepo.findById(id).get().getDesignation();
		    		
		    		k.setPosition(designation);
		    	});
		    	
		    	model.addAttribute("statusList", findAll);
		    	return "user-dashboard";
    }

    @GetMapping("/user-profile")
    public String userProfile(HttpSession session, Model model) {
        Object attribute = session.getAttribute("userId");

        if (attribute == null) {
            System.out.println("User is not logged in. Redirecting to login.");
            return "redirect:/login";
        }

        try {
            int userId = Integer.parseInt(attribute.toString());
            Employee employee = employeeRepo.findById(userId).orElse(null);

            if (employee == null) {
                System.out.println("Employee not found for userId: " + userId);
                return "redirect:/login";
            }

            model.addAttribute("employee", employee);
            return "user-profile";

        } catch (NumberFormatException e) {
            System.out.println("Invalid userId format: " + e.getMessage());
            return "redirect:/login";
        }
    }
    
    @GetMapping("/user-setting")
    public String userSetting() {
        return "user-setting"; 
    }

    @GetMapping("/user-compose")
    public String compose() {
        return "user-compose"; 
    }

    @PostMapping("/compose")
    public String addCompose(@RequestParam("subject")String subject, @RequestParam("text") String text,HttpSession session) {
    	
    	 try {
			Object attribute = session.getAttribute("userId");
			 int userId = Integer.parseInt(attribute.toString());
			 
			Employee employee = employeeRepo.findById(userId).get();
			
			Compose com = new Compose();
			
			com.setEmpName(employee.getEmpName());
			com.setSubject(subject);
			com.setText(text);
			com.setParentUkid(userId);
			com.setAddedDate(new Date().toString());
	        com.setCreatedAt(new java.sql.Date(System.currentTimeMillis())); // Set createdAt
			com.setStatus("PENDING");
			
			Compose save = composeRepo.save(com);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return "redirect:/user-compose";
    	
    }
    
    @GetMapping("/approve-byId")
    public String approve(@RequestParam("id") int id,@RequestParam("type") String type) {
		
    	System.out.println(id+" === "+type);
    	Compose compose=composeRepo.findById(id).get();
    	compose.setStatus(type);
    	
    	composeRepo.save(compose);
    	
    	return "redirect:/status";    	
    }
}
