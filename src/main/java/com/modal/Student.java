package com.modal;

import java.util.LinkedHashMap;
import java.util.Map;

import com.repository.PageDatabase;

public class Student extends User {
	private String role;
	private Map<Page, String[]> permissions; // Key: Page, Value: Allowed CRUD operations

	public Student(String username, String email, String password) {
		super(username, email, password);
		role = "student";
		this.permissions = new LinkedHashMap<>();

		// Access the page database
		Map<String, Page> pageDatabase = PageDatabase.getPageDatabase();

		// Assign permissions for the student role
		this.permissions.put(pageDatabase.get("Dashboard"), new String[] { "Read" });
		this.permissions.put(pageDatabase.get("School Information"), new String[] { "Read"});
		this.permissions.put(pageDatabase.get("Event"), new String[] { "Read" });
	}
	
	public Student(String username, String email, String password,String phoneNum,String address,String district,String state) {
		super(username, email, password,phoneNum,address,district,state);
		role = "student";
		this.permissions = new LinkedHashMap<>();

		// Access the page database
		Map<String, Page> pageDatabase = PageDatabase.getPageDatabase();

		// Assign permissions for the student role
		this.permissions.put(pageDatabase.get("Dashboard"), new String[] { "Read" });
		this.permissions.put(pageDatabase.get("School Information"), new String[] { "Read"});
		this.permissions.put(pageDatabase.get("Event"), new String[] { "Read" });
	}

	@Override
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Boolean hasPermission(String page, String crud) {
	    // Find the Page object with the given page name
	    for (Page key : permissions.keySet()) {
	        if (key.getFilename().equalsIgnoreCase(page)) {
	            // Check if the CRUD operation is allowed for the page
	            String[] allowedActions = permissions.get(key);
	            for (String action : allowedActions) {
	                if (action.equalsIgnoreCase(crud)) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false; // Return false if no matching page or permission is found
	}

	@Override
	public Map<Page, String[]> getPermissions() {
		return permissions;
	}

	@Override
	public void setPermissions(Map<Page, String[]> permissions) {
		this.permissions = permissions;
	}
	
	@Override
	public void setSchool(School school) {
		if(super.getSchool() != null) {
			int prev_tot = super.getSchool().getTotalStudent() - 1;
			super.getSchool().setTotalStudent(prev_tot);
		}
		int total_student = school.getTotalStudent() + 1;
		school.setTotalStudent(total_student);
		super.setSchool(school);
	}
}
