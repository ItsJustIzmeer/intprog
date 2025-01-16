package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.modal.Event;

public class EventDatabase {
	private static List<Event> eventList = new ArrayList<>();

	static {
		Event event1 = new Event("School Anniversary", "2023-10-15", "2023-10-15", "10:00 AM - 2:00 PM",
				"ABC High School", "John Doe", "john.doe@example.com", "123-456-7890", "limit", 100, "physical",
				"School Auditorium", "", "Celebrating 50 years of excellence!");

		Event event2 = new Event("Science Fair", "2023-11-20", "2023-11-20", "9:00 AM - 4:00 PM", "XYZ Science Club",
				"Jane Smith", "jane.smith@example.com", "987-654-3210", "open", 0, "physical", "School Grounds", "",
				"Annual science exhibition showcasing innovative projects.");

		Event event3 = new Event("Online Coding Workshop", "2023-12-05", "2023-12-05", "2:00 PM - 5:00 PM",
				"Tech Gurus", "Alice Brown", "alice.brown@example.com", "555-666-7777", "limit", 50, "virtual", "",
				"Zoom", "Learn the basics of Python programming in this interactive workshop.");

		eventList.add(event1);
		eventList.add(event2);
		eventList.add(event3);
	}

	public static List<Event> getEventDatabase() {
		return eventList;
	}

	public static void addEvent(Event event) {
		eventList.add(event);
	}

	public static Event getEventById(String id) {
		return eventList.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
	}

	public static boolean updateEvent(String id, Event updatedEvent) {
		for (Event event : eventList) {
			if (event.getId().equals(id)) {
				event.setName(updatedEvent.getName());
				event.setStartDate(updatedEvent.getStartDate());
				event.setEndDate(updatedEvent.getEndDate());
				event.setTime(updatedEvent.getTime());
				event.setOrganizer(updatedEvent.getOrganizer());
				event.setSpeaker(updatedEvent.getSpeaker());
				event.setEmail(updatedEvent.getEmail());
				event.setPhoneNumber(updatedEvent.getPhoneNumber());
				event.setParticipantType(updatedEvent.getParticipantType());
				event.setParticipantLimit(updatedEvent.getParticipantLimit());
				event.setEventType(updatedEvent.getEventType());
				event.setEventLocation(updatedEvent.getEventLocation());
				event.setEventPlatform(updatedEvent.getEventPlatform());
				event.setDescription(updatedEvent.getDescription());
				return true;
			}
		}
		return false;
	}

	public static boolean deleteEvent(String id) {
		return eventList.removeIf(event -> event.getId().equals(id));
	}

	public static Boolean searchIfExist(String id) {
		for (Event event : EventDatabase.getEventDatabase()) {
			if (event.getId().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}
}
