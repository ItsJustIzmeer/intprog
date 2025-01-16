package com.modal;

import java.util.UUID;
import java.time.LocalDateTime;

public class ReqEquipment {
	private String id;
	private String equipmentId;
	private String name;
	private String brand;
	private int amount;
	private LocalDateTime applyDate;
	private LocalDateTime approvedDate;

	public ReqEquipment(String equipmentId, String name, String brand, int amount, LocalDateTime applyDate,
			LocalDateTime approvedDate) {
		super();
		this.id = UUID.randomUUID().toString();
		this.equipmentId = equipmentId;
		this.name = name;
		this.brand = brand;
		this.amount = amount;
		this.applyDate = applyDate;
		this.approvedDate = approvedDate;
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

	public LocalDateTime getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDateTime applyDate) {
		this.applyDate = applyDate;
	}

	public LocalDateTime getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDateTime approvedDate) {
		this.approvedDate = approvedDate;
	}

	public ReqEquipment() {
		this.id = UUID.randomUUID().toString();
	}
}
