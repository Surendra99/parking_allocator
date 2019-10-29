package com.parking_allocater.domain;

import java.util.Map;
import java.util.TreeMap;

public class BaseParkingSpace {
	
	protected Map<Integer, Vehicle> vehicles = new TreeMap<>();
	protected int totalSlots = 0;

	public BaseParkingSpace(int slots){
		this.totalSlots = slots;
	}
	
	public void print(){
		this.vehicles.entrySet().stream().forEach(entrySet->entrySet.getValue().print());
	}
}
