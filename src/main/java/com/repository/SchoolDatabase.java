package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.modal.School;

public class SchoolDatabase {
	private static List<School> schoolList = new ArrayList<>();

	// Initialize schools
	static {
		School school1 = new School("SMK Taman Universiti", "JEA1049", "Secondary", 1, 1079, 109, "Encik Azman",
				"0193515096", "Jalan Pendidikan, Taman Universiti", "Skudai", "Johor");
		School school2 = new School("SMK Taman Bukit Indah", "JEA1050", "Secondary", 2, 1239, 111, "Encik Osman",
				"0193515096", "Jalan Sri Skudai, Taman Sri Skudai", "Skudai", "Johor");
		School school3 = new School("SMK Taman Mutiara Rini 2", "JEA1051", "Secondary", 2, 1583, 90, "Encik Zafri",
				"0193515096", "Jalan Hang Jebat, Mutiara Rini", "Skudai", "Johor");
		School school4 = new School("SMK Taman Tanjung Pengelih", "JEA1051", "Secondary", 3, 1530, 82, "Encik Fadz",
				"0193515096", "Tanjung Pengelih", "Skudai", "Johor");
		School school5 = new School("SMK Tasek Utara 2", "JEA1051", "Secondary", 1, 600, 50, "Encik Daus", "0193515096",
				"Jalan Taruka, Kampung Muafakat", "Skudai", "Johor");
		School school6 = new School("SMK Sri Tebrau", "JEA1051", "Secondary", 4, 786, 58, "Encik Thaqif", "0193515096",
				"Jalan Rentaka, Taman Sri Tebrau", "Skudai", "Johor");

		schoolList.add(school1);
		schoolList.add(school2);
		schoolList.add(school3);
		schoolList.add(school4);
		schoolList.add(school5);
		schoolList.add(school6);
	}

	public static List<School> getSchoolDatabase() {
		return schoolList;
	}

	public static void addSchool(School school) {
		schoolList.add(school);
	}

	public static School getSchoolById(String id) {
		return schoolList.stream().filter(school -> school.getId().equals(id)).findFirst().orElse(null);
	}

	public static boolean updateSchool(String id, School updatedSchool) {
		for (School school : schoolList) {
			if (school.getId().equals(id)) {
				school.setName(updatedSchool.getName());
				school.setCode(updatedSchool.getCode());
				school.setType(updatedSchool.getType());
				school.setVersion(updatedSchool.getVersion());
				school.setTotalStudent(updatedSchool.getTotalStudent());
				school.setTotalTeacher(updatedSchool.getTotalTeacher());
				school.setHead(updatedSchool.getHead());
				school.setPhoneNum(updatedSchool.getPhoneNum());
				school.setAddress(updatedSchool.getAddress());
				school.setDistrict(updatedSchool.getDistrict());
				school.setState(updatedSchool.getState());
				return true;
			}
		}
		return false;
	}

	public static boolean deleteSchool(String id) {
		return schoolList.removeIf(school -> school.getId().equals(id));
	}

	public static Boolean searchIfExist(String id) {
		for (School school : SchoolDatabase.getSchoolDatabase()) {
			if (school.getId().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}
}
