package com.modal;

import java.util.UUID;

public class School {
	private String id;
	private String name;
	private String code;
	private String type;
	private int version;
	private int totalStudent;
	private int totalTeacher;
	private String head;
	private String phoneNum;
	private String address;
	private String district;
	private String state;

	public School() {
		this.id = UUID.randomUUID().toString();
		this.code = null; // to prevent error.
	}

	public School(String name, String code, String type, int version, int totalStudent, int totalTeacher,
			String head, String phoneNum, String address, String district, String state) {
		super();
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.code = code;
		this.type = type;
		this.version = version;
		this.totalStudent = totalStudent;
		this.totalTeacher = totalTeacher;
		this.head = head;
		this.phoneNum = phoneNum;
		this.address = address;
		this.district = district;
		this.state = state;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}

	public int getTotalTeacher() {
		return totalTeacher;
	}

	public void setTotalTeacher(int totalTeacher) {
		this.totalTeacher = totalTeacher;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
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

}
