package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.modal.Equipment;

public class EquipmentDatabase {
	private static List<Equipment> equipmentList = new ArrayList<>();

	static {
		Equipment equipment1 = new Equipment("666666", "Camera", "Canon", 3);

		Equipment equipment2 = new Equipment("888888", "Phone", "iPhone", 4);

		Equipment equipment3 = new Equipment("659721", "Camera", "Nikon", 2);

		equipmentList.add(equipment1);
		equipmentList.add(equipment2);
		equipmentList.add(equipment3);
	}

	public static List<Equipment> getEquipmentDatabase() {
		return equipmentList;
	}

	public static void addEquipment(Equipment equipment) {
		equipmentList.add(equipment);
	}

	public static Equipment getEquipmentById(String id) {
		return equipmentList.stream().filter(equipment -> equipment.getId().equals(id)).findFirst().orElse(null);
	}

	public static boolean updateEquipment(String id, Equipment updatedEquipment) {
		for (Equipment equipment : equipmentList) {
			if (equipment.getId().equals(id)) {
				equipment.setEquipmentId(updatedEquipment.getEquipmentId());
				equipment.setName(updatedEquipment.getName());
				equipment.setBrand(updatedEquipment.getBrand());
				equipment.setAmount(updatedEquipment.getAmount());
				return true;
			}
		}
		return false;
	}

	public static boolean deleteEquipment(String id) {
		return equipmentList.removeIf(equipment -> equipment.getId().equals(id));
	}

	public static Boolean searchIfExist(String id) {
		for (Equipment equipment : EquipmentDatabase.getEquipmentDatabase()) {
			if (equipment.getId().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}
}
