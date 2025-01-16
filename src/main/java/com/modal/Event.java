package com.modal;

import java.util.UUID;

public class Event {
	private String id;
	private String name;
	private String startDate;
	private String endDate;
	private String time;
	private String organizer;
	private String speaker;
	private String email;
	private String phoneNumber;
	private String participantType;
	private int participantLimit;
	private String eventType;
	private String eventLocation;
	private String eventPlatform;
	private String description;

	// Getters and Setters
	public Event() {
		this.id = UUID.randomUUID().toString();
	}

	public Event(String name, String startDate, String endDate, String time, String organizer, String speaker,
			String email, String phoneNumber, String participantType, int participantLimit, String eventType,
			String eventLocation, String eventPlatform, String description) {
		super();
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.organizer = organizer;
		this.speaker = speaker;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.participantType = participantType;
		this.participantLimit = participantLimit;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventPlatform = eventPlatform;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getParticipantType() {
		return participantType;
	}

	public void setParticipantType(String participantType) {
		this.participantType = participantType;
	}

	public int getParticipantLimit() {
		return participantLimit;
	}

	public void setParticipantLimit(int participantLimit) {
		this.participantLimit = participantLimit;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventPlatform() {
		return eventPlatform;
	}

	public void setEventPlatform(String eventPlatform) {
		this.eventPlatform = eventPlatform;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
