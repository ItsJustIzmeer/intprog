package com.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.modal.School;
import com.repository.SchoolDatabase;

@Controller
public class SchoolController {

    //Display all schools
    @GetMapping("/school")
    public ModelAndView listSchool(ModelMap model, HttpServletRequest request) {
    	List<School> schools = SchoolDatabase.getSchoolDatabase();
        model.addAttribute("schools", schools);
        return new ModelAndView("/school",model);
    }
    
    // Show the create school form
    @GetMapping("/createSchool")
    public String createSchoolForm() {
        return "createSchool";
    }
    
//    @GetMapping("/school")
//    public String schoolDetails(@PathVariable String id, Model model) {
//        School school = SchoolDatabase.getSchoolById(id);
//        model.addAttribute("school", school);
//        return "school";
//    }
    
    // handle school update
    @PostMapping("/editSchool")
    public ModelAndView editSchool(ModelMap model, HttpServletRequest request) {
    	String id = request.getParameter("id");
        School updatedSchool = new School();
        updatedSchool.setId(id);
        updatedSchool.setName(request.getParameter("schoolName"));
        updatedSchool.setCode(request.getParameter("schoolCode"));
        updatedSchool.setType(request.getParameter("schoolType"));
        updatedSchool.setVersion(Integer.parseInt(request.getParameter("schoolVersion")));
        updatedSchool.setTotalStudent(Integer.parseInt(request.getParameter("schoolTotalStudent")));
        updatedSchool.setTotalTeacher(Integer.parseInt(request.getParameter("schoolTotalTeacher")));
        updatedSchool.setHead(request.getParameter("schoolHead"));
        updatedSchool.setPhoneNum(request.getParameter("schoolPhoneNum"));
        updatedSchool.setDistrict(request.getParameter("schoolDistrict"));
        updatedSchool.setState(request.getParameter("schoolState"));
        
        boolean isUpdated = SchoolDatabase.updateSchool(id, updatedSchool);
        
        List<School> schools = SchoolDatabase.getSchoolDatabase();
        model.addAttribute("schools", schools);
        
        ModelAndView modelAndView = new ModelAndView("School");
        if (isUpdated) {
            modelAndView.addObject("success_msg", "School information updated successfully!");
        } else {
            modelAndView.addObject("error_msg", "Failed to update school information.");
        }
        
        return modelAndView;
    }
    
    // handle school delete
    @PostMapping("/deleteSchool")
    public ModelAndView deleteSchool(@RequestParam String id) {
        // Create a ModelAndView instance
        ModelAndView modelAndView = new ModelAndView("School");

        // Delete the school
        boolean success = SchoolDatabase.deleteSchool(id);

        // Add attributes to the model
        if (success) {
            modelAndView.addObject("message", "School deleted successfully.");
        } else {
            modelAndView.addObject("message", "Failed to delete the school.");
        }

        // Fetch the updated event data to pass back to the view
        List<School> schools = SchoolDatabase.getSchoolDatabase();
        modelAndView.addObject("schools", schools);

        // Return the ModelAndView object
        return modelAndView;
    }
    
}

