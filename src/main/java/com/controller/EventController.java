package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.modal.Event;
import com.repository.EventDatabase;

@Controller
public class EventController {

    // Display the list of events
    @GetMapping("/event")
    public ModelAndView listEvents(ModelMap model, HttpServletRequest request) {
        List<Event> events = EventDatabase.getEventDatabase();
        model.addAttribute("events", events);
        return new ModelAndView("/event",model);
    }

    // Show the create event form
    @GetMapping("/createEvent")
    public String createEventForm() {
        return "createEvent";
    }


    // Handle event creation
    @PostMapping("/createEvent")
    public ModelAndView createEvent(HttpServletRequest request) {
    	ModelAndView mdv = new ModelAndView("Event");
    	
    	
        Event newEvent = new Event();
        newEvent.setName(request.getParameter("eventName"));
        newEvent.setStartDate(request.getParameter("startDate"));
        newEvent.setEndDate(request.getParameter("endDate"));
        newEvent.setTime(request.getParameter("eventTime"));
        newEvent.setOrganizer(request.getParameter("organizer"));
        newEvent.setSpeaker(request.getParameter("speaker"));
        newEvent.setEmail(request.getParameter("email"));
        newEvent.setPhoneNumber(request.getParameter("phoneNumber"));
        newEvent.setParticipantType(request.getParameter("participants"));

        // Handling participant limit
        String participantLimit = request.getParameter("participantLimit");
        int limit = (participantLimit != null && !participantLimit.isEmpty()) ? Integer.parseInt(participantLimit) : 0;
        newEvent.setParticipantLimit(limit);

        newEvent.setEventType(request.getParameter("eventType"));
        newEvent.setEventLocation(request.getParameter("eventLocation"));
        newEvent.setEventPlatform(request.getParameter("eventPlatform"));
        newEvent.setDescription(request.getParameter("eventDescription"));

        EventDatabase.addEvent(newEvent);
        mdv.addObject("events",EventDatabase.getEventDatabase());
        mdv.addObject("success_msg","Event has been successfully created.");
        return mdv;
    }

    // Show the event details for editing
    @GetMapping("/detailEvent/{id}")
    public String eventDetails(@PathVariable String id, Model model) {
        Event event = EventDatabase.getEventById(id);
        model.addAttribute("event", event);
        return "detailEvent";
    }

 // Handle event update
    @PostMapping("/editEvent")
    public ModelAndView editEvent(ModelMap model, HttpServletRequest request) {
        String id = request.getParameter("id");
        Event updatedEvent = new Event();
        updatedEvent.setId(id);
        updatedEvent.setName(request.getParameter("eventName"));
        updatedEvent.setStartDate(request.getParameter("startDate"));
        updatedEvent.setEndDate(request.getParameter("endDate"));
        updatedEvent.setTime(request.getParameter("eventTime"));
        updatedEvent.setOrganizer(request.getParameter("organizer"));
        updatedEvent.setSpeaker(request.getParameter("speaker"));
        updatedEvent.setEmail(request.getParameter("email"));
        updatedEvent.setPhoneNumber(request.getParameter("phoneNumber"));
        updatedEvent.setParticipantType(request.getParameter("participants"));

        // Handling participant limit
        String participantLimit = request.getParameter("participantLimit");
        int limit = (participantLimit != null && !participantLimit.isEmpty()) ? Integer.parseInt(participantLimit) : 0;
        updatedEvent.setParticipantLimit(limit);

        updatedEvent.setEventType(request.getParameter("eventType"));
        updatedEvent.setEventLocation(request.getParameter("eventLocation"));
        updatedEvent.setEventPlatform(request.getParameter("eventPlatform"));
        updatedEvent.setDescription(request.getParameter("eventDescription"));

        boolean isUpdated = EventDatabase.updateEvent(id, updatedEvent);
        
        List<Event> events = EventDatabase.getEventDatabase();
        model.addAttribute("events", events);

        ModelAndView modelAndView = new ModelAndView("Event");
        if (isUpdated) {
            modelAndView.addObject("success_msg", "Event updated successfully!");
        } else {
            modelAndView.addObject("error_msg", "Failed to update event.");
        }
        return modelAndView;
    }


 /// Handle event deletion
    @PostMapping("/deleteEvent")
    public ModelAndView deleteEvent(@RequestParam String id) {
        // Create a ModelAndView instance
        ModelAndView modelAndView = new ModelAndView("Event");

        // Delete the event
        boolean success = EventDatabase.deleteEvent(id);

        // Add attributes to the model
        if (success) {
            modelAndView.addObject("message", "Event deleted successfully.");
        } else {
            modelAndView.addObject("message", "Failed to delete the event.");
        }

        // Fetch the updated event data to pass back to the view
        List<Event> events = EventDatabase.getEventDatabase();
        modelAndView.addObject("events", events);

        // Return the ModelAndView object
        return modelAndView;
    }



}
