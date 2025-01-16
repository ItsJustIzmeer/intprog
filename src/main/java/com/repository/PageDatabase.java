package com.repository;

import java.util.HashMap;
import java.util.Map;

import com.modal.Page;

public class PageDatabase {
	private static Map<String,Page> pageDatabase = new HashMap<>();
	
	static {
		pageDatabase.put("Dashboard",new Page("Dashboard","dashboard","fa fa-tachometer"));
		pageDatabase.put("School Information", new Page("School Information","School","fa fa-graduation-cap"));
		pageDatabase.put("Manage School", new Page("Manage School","ManageSchool","fa fa-graduation-cap"));
		pageDatabase.put("User Management",new Page("User Management","userMng","fa fa-address-book"));
		pageDatabase.put("User Permission",new Page("User Permission","userPrm","fa fa-key"));
		pageDatabase.put("Event Application", new Page("Event Application","EventApplication","fa fa-calendar"));
		pageDatabase.put("Manage Event Application", new Page("Manage Event Application","ManageEventApp","fa fa-list"));
		pageDatabase.put("Event",new Page("Event","Event","fa fa-apple"));
		pageDatabase.put("Manage Equipment",new Page("Manage Equipment","ManageEquipment","fa fa-wrench"));
		pageDatabase.put("Manage Request Equipment",new Page("Manage Request Equipment","ManageRequestEquipment","fa fa-list"));
		pageDatabase.put("Request Equipment", new Page("Request Equipment","RequestEquipment","fa fa-question"));
		pageDatabase.put("Level Upgrade Application", new Page("Level Upgrade Application","LevelUpgrade","fa fa-arrow-up"));
		pageDatabase.put("Manage Level Application", new Page("Manage Level Application","SchoolLevel","fa fa-graduation-cap"));
		
	}
	
	public static Map<String, Page> getPageDatabase() {
		return pageDatabase;
	}

	public static void setPageDatabase(Map<String, Page> pageDatabase) {
		PageDatabase.pageDatabase = pageDatabase;
	}

}
