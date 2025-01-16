package com.modal;

import java.util.Map;
import java.util.UUID;

public class User {
	private String id,username, email, password,profilePic,status,phoneNum,address,district,state;
	private School school = new School();

	public User() {}
	
	public User(String username, String email, String password) {
		super();
		this.setId(UUID.randomUUID().toString());
		this.setStatus("A"); //A-active,B-banned
		this.username = username;
		this.email = email;
		this.password = password;
		this.profilePic = "profile_pic";
	}
	
	public User(String username,String email,String password,String phoneNum,String address,String district,String state) {
		super();
		this.setId(UUID.randomUUID().toString());
		this.setStatus("A");
		this.profilePic = "profile_pic"; //cannot change.
		this.username = username;
		this.email= email;
		this.password = password;
		this.phoneNum = phoneNum;
		this.address = address;
		this.district = district;
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return null;
	}

	public Boolean hasPermission(String page, String crud) {
		return null;
	}

	public void setPermissions(Map<Page, String[]> permissions) {
	}

	public Map<Page, String[]> getPermissions() {
		return null;
	}
	
	public String getUername() {
		return username;
	}


	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	
	public String printPermission() {	
		return "";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
