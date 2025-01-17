package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.modal.ReqEquipment;
import com.repository.ReqEquipmentDatabase;

@Controller
public class ReqEquipmentController {

    // Display the list of equipments
    @GetMapping("/requestequipment")
    public ModelAndView listEvents(ModelMap model, HttpServletRequest request) {
        List<ReqEquipment> reqEquipments = ReqEquipmentDatabase.getReqEquipmentDatabase();
        model.addAttribute("reqEquipments", reqEquipments);
        return new ModelAndView("/requestequipment",model);
    }

    // Show the create equipment form
    @GetMapping("/createReqEquipment")
    public String createEquipmentForm() {
        return "createEquipment";
    }


    // Handle equipment creation
    @PostMapping("/createReqEquipment")
    public ModelAndView createReqEquipment(HttpServletRequest request) {
    	ModelAndView mdv = new ModelAndView("ReqEquipment");
    	
        ReqEquipment newReqEquipment = new ReqEquipment();
        newReqEquipment.setEquipmentId(request.getParameter("equipmentId"));
        newReqEquipment.setName(request.getParameter("reqEquipmentName"));
        newReqEquipment.setBrand(request.getParameter("reqEquipmentBrand"));
        newReqEquipment.setAmount(Integer.parseInt(request.getParameter("reqEquipmentAmount")));

        ReqEquipmentDatabase.addReqEquipment(newReqEquipment);
        mdv.addObject("events",ReqEquipmentDatabase.getReqEquipmentDatabase());
        mdv.addObject("success_msg","Request Equipment has been successfully created.");
        return mdv;
    }

    // Show the event details for editing
    @GetMapping("/requestEquipment/{id}")
    public String reqEquipmentDetails(@PathVariable String id, Model model) {
        ReqEquipment reqEquipment = ReqEquipmentDatabase.getReqEquipmentById(id);
        model.addAttribute("reqEquipment", reqEquipment);
        return "requestEquipment";
    }

 // Handle event update
    @PostMapping("/editRequestEquipment")
    public ModelAndView editReqEquipment(ModelMap model, HttpServletRequest request) {
        String id = request.getParameter("id");
        ReqEquipment updatedReqEquipment = new ReqEquipment();
        updatedReqEquipment.setId(id);
        updatedReqEquipment.setEquipmentId(request.getParameter("equipmentId"));
        updatedReqEquipment.setName(request.getParameter("reqEquipmentName"));
        updatedReqEquipment.setBrand(request.getParameter("reqEquipmentBrand"));
        updatedReqEquipment.setAmount(Integer.parseInt(request.getParameter("reqEquipmentAmount")));

        boolean isUpdated = ReqEquipmentDatabase.updateReqEquipment(id, updatedReqEquipment);
        
        List<ReqEquipment> reqEquipments = ReqEquipmentDatabase.getReqEquipmentDatabase();
        model.addAttribute("reqEquipments", reqEquipments);

        ModelAndView modelAndView = new ModelAndView("ReqEquipment");
        if (isUpdated) {
            modelAndView.addObject("success_msg", "Request Equipment updated successfully!");
        } else {
            modelAndView.addObject("error_msg", "Failed to update request equipment.");
        }
        return modelAndView;
    }


 /// Handle event deletion
    @PostMapping("/deleteRequestEquipment")
    public ModelAndView deleteEquipment(@RequestParam String id) {
        // Create a ModelAndView instance
        ModelAndView modelAndView = new ModelAndView("ReqEquipment");

        // Delete the event
        boolean success = ReqEquipmentDatabase.deleteReqEquipment(id);

        // Add attributes to the model
        if (success) {
            modelAndView.addObject("message", "Request equipment deleted successfully.");
        } else {
            modelAndView.addObject("message", "Failed to delete the request equipment.");
        }

        // Fetch the updated event data to pass back to the view
        List<ReqEquipment> reqEquipments = ReqEquipmentDatabase.getReqEquipmentDatabase();
        modelAndView.addObject("reqEquipments", reqEquipments);

        // Return the ModelAndView object
        return modelAndView;
    }



}
