package com.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.modal.ReqEquipment;

public class ReqEquipmentDatabase {
	private static List<ReqEquipment> reqEquipmentList = new ArrayList<>();

	static {
		ReqEquipment reqEquipment1 = new ReqEquipment("666666", "Camera", "Canon", 3, LocalDateTime.now(), LocalDateTime.now());

		reqEquipmentList.add(reqEquipment1);

	}

	public static List<ReqEquipment> getReqEquipmentDatabase() {
		return reqEquipmentList;
	}

	public static void addReqEquipment(ReqEquipment reqEquipment) {
		reqEquipmentList.add(reqEquipment);
	}

	public static ReqEquipment getReqEquipmentById(String id) {
		return reqEquipmentList.stream().filter(reqEquipment -> reqEquipment.getId().equals(id)).findFirst().orElse(null);
	}

	public static boolean updateReqEquipment(String id, ReqEquipment updatedReqEquipment) {
		for (ReqEquipment reqEquipment : reqEquipmentList) {
			if (reqEquipment.getId().equals(id)) {
				reqEquipment.setEquipmentId(updatedReqEquipment.getEquipmentId());
				reqEquipment.setName(updatedReqEquipment.getName());
				reqEquipment.setBrand(updatedReqEquipment.getBrand());
				reqEquipment.setAmount(updatedReqEquipment.getAmount());
				reqEquipment.setApplyDate(updatedReqEquipment.getApplyDate());
				return true;
			}
		}
		return false;
	}

	public static boolean deleteReqEquipment(String id) {
		return reqEquipmentList.removeIf(reqEquipment -> reqEquipment.getId().equals(id));
	}

	public static Boolean searchIfExist(String id) {
		for (ReqEquipment reqEquipment : ReqEquipmentDatabase.getReqEquipmentDatabase()) {
			if (reqEquipment.getId().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}
}
