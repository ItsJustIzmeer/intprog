package com.modal;

import java.util.LinkedHashMap;
import java.util.Map;

import com.repository.PageDatabase;

public class DistrictOfficer extends User {
	private String role;
	private Map<Page, String[]> permissions; // Key: Page, Value: Allowed CRUD operations

	public DistrictOfficer(String username, String email, String password) {
		super(username, email, password);
		role = "district officer";
		this.permissions = new LinkedHashMap<>();

		// Access the page database
		Map<String, Page> pageDatabase = PageDatabase.getPageDatabase();

		// Assign permissions for the district officer role
		this.permissions.put(pageDatabase.get("Dashboard"), new String[] { "Read" });
		this.permissions.put(pageDatabase.get("Manage School"), new String[] { "Read", "Update" });
		this.permissions.put(pageDatabase.get("Manage Event Application"), new String[] { "Read", "Update" });
		this.permissions.put(pageDatabase.get("Manage Request Equipment"),new String[] { "Read","Update"});
		this.permissions.put(pageDatabase.get("Manage Level Application"),new String[] { "Read","Update"});
		this.permissions.put(pageDatabase.get("User Management"), new String[] { "Create", "Read", "Update", "Delete" });
		this.permissions.put(pageDatabase.get("User Permission"), new String[] { "Read", "Update" });
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
	public void setPermissions(Map<Page, String[]> permissions) {
		this.permissions = permissions;
	}

	@Override
	public Map<Page, String[]> getPermissions() {
		return permissions;
	}
}
