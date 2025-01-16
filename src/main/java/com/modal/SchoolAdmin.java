package com.modal;

import java.util.LinkedHashMap;
import java.util.Map;

import com.repository.PageDatabase;

public class SchoolAdmin extends User {
	private String role;
	private Map<Page, String[]> permissions; // Key: Page, Value: Allowed CRUD operations

	public SchoolAdmin() {
		super();
	}

	public SchoolAdmin(String username, String email, String password) {
		super(username, email, password);
		role = "school admin";
		this.permissions = new LinkedHashMap<>();

		// Access the page database
		Map<String, Page> pageDatabase = PageDatabase.getPageDatabase();

		// Assign permissions for the school admin role
		this.permissions.put(pageDatabase.get("Dashboard"), new String[] { "Read" });
		this.permissions.put(pageDatabase.get("School Information"), new String[] { "Read", "Update"});
		this.permissions.put(pageDatabase.get("Event"), new String[] { "Create", "Read", "Update", "Delete" });
		
		this.permissions.put(pageDatabase.get("Manage Equipment"), new String[] { "Read", "Update", "Delete" });
		this.permissions.put(pageDatabase.get("Request Equipment"), new String[] { "Create", "Read", "Delete" });
		this.permissions.put(pageDatabase.get("Level Upgrade Application"),
				new String[] { "Create", "Read", "Delete" });
		this.permissions.put(pageDatabase.get("User Management"),
				new String[] { "Create", "Read", "Update", "Delete" });
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
	public String printPermission() {
		String output = "";
		for (Map.Entry<Page,String[]> entry: permissions.entrySet()) {
			Page page = entry.getKey();
			String[] allowedActions = entry.getValue();
			
			boolean hasReadPermission = false;
			for(String action: allowedActions) {
				if("Read".equalsIgnoreCase(action)) {
					hasReadPermission = true;
				}
			}
			
			if(hasReadPermission == true) {
				output += page.getTitle();
			}
		}
		return output;
	}

	@Override
	public Map<Page, String[]> getPermissions() {
		return permissions;
	}
}
