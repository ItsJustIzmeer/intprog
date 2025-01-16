package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.modal.Equipment;
import com.repository.EquipmentDatabase;

@Controller
public class EquipmentController {

    // Display the list of equipments
    @GetMapping("/equipment")
    public ModelAndView listEvents(ModelMap model, HttpServletRequest request) {
        List<Equipment> equipments = EquipmentDatabase.getEquipmentDatabase();
        model.addAttribute("equipments", equipments);
        return new ModelAndView("/equipment",model);
    }

    // Show the create equipment form
    @GetMapping("/createEquipment")
    public String createEquipmentForm() {
        return "createEquipment";
    }


    // Handle equipment creation
    @PostMapping("/createEquipment")
    public ModelAndView createEquipment(HttpServletRequest request) {
    	ModelAndView mdv = new ModelAndView("Equipment");
    	
        Equipment newEquipment = new Equipment();
        newEquipment.setEquipmentId(request.getParameter("equipmentId"));
        newEquipment.setName(request.getParameter("equipmentName"));
        newEquipment.setBrand(request.getParameter("equipmentBrand"));
        newEquipment.setAmount(Integer.parseInt(request.getParameter("equipmentAmount")));

        EquipmentDatabase.addEquipment(newEquipment);
        mdv.addObject("events",EquipmentDatabase.getEquipmentDatabase());
        mdv.addObject("success_msg","Equipment has been successfully created.");
        return mdv;
    }

    // Show the event details for editing
    @GetMapping("/detailEquipment/{id}")
    public String equipmentDetails(@PathVariable String id, Model model) {
        Equipment equipment = EquipmentDatabase.getEquipmentById(id);
        model.addAttribute("equipment", equipment);
        return "detailEquipment";
    }

 // Handle event update
    @PostMapping("/editEquipment")
    public ModelAndView editEquipment(ModelMap model, HttpServletRequest request) {
        String id = request.getParameter("id");
        Equipment updatedEquipment = new Equipment();
        updatedEquipment.setId(id);
        updatedEquipment.setEquipmentId(request.getParameter("equipmentId"));
        updatedEquipment.setName(request.getParameter("equipmentName"));
        updatedEquipment.setBrand(request.getParameter("equipmentBrand"));
        updatedEquipment.setAmount(Integer.parseInt(request.getParameter("equipmentAmount")));

        boolean isUpdated = EquipmentDatabase.updateEquipment(id, updatedEquipment);
        
        List<Equipment> equipments = EquipmentDatabase.getEquipmentDatabase();
        model.addAttribute("equipments", equipments);

        ModelAndView modelAndView = new ModelAndView("Equipment");
        if (isUpdated) {
            modelAndView.addObject("success_msg", "Equipment updated successfully!");
        } else {
            modelAndView.addObject("error_msg", "Failed to update equipment.");
        }
        return modelAndView;
    }


 /// Handle event deletion
    @PostMapping("/deleteEquipment")
    public ModelAndView deleteEquipment(@RequestParam String id) {
        // Create a ModelAndView instance
        ModelAndView modelAndView = new ModelAndView("Equipment");

        // Delete the event
        boolean success = EquipmentDatabase.deleteEquipment(id);

        // Add attributes to the model
        if (success) {
            modelAndView.addObject("message", "Equipment deleted successfully.");
        } else {
            modelAndView.addObject("message", "Failed to delete the equipment.");
        }

        // Fetch the updated event data to pass back to the view
        List<Equipment> equipments = EquipmentDatabase.getEquipmentDatabase();
        modelAndView.addObject("equipments", equipments);

        // Return the ModelAndView object
        return modelAndView;
    }



}
