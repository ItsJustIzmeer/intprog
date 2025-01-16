package com.modal;

import java.util.UUID;

public class Equipment {
	private String id;
	private String equipmentId;
	private String name;
	private String brand;
	private int amount;

	public Equipment() {
		this.id = UUID.randomUUID().toString();
	}

	public Equipment(String equipmentId, String name, String brand, int amount) {
		super();
		this.id = UUID.randomUUID().toString();
		this.equipmentId = equipmentId;
		this.name = name;
		this.brand = brand;
		this.amount = amount;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
