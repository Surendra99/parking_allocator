package com.parking_allocater.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.parking_allocater.util.StringConstants;

public class ParkingSpace extends BaseParkingSpace {
	
	public ParkingSpace(int slots) {
		super(slots);
	}
	
	public String park(String vehicleNumber,String color){
		if(this.vehicles.size() == totalSlots)
			return StringConstants.NO_SLOT_AVAILABLE;
		for(int i=1;i<=totalSlots;i++){
			Vehicle vehicle = this.vehicles.get(i);
			if(vehicle == null){
				vehicles.put(i, new Vehicle(vehicleNumber,color,i));
				return StringConstants.ALLOCATED_SLOT_NUMBER+i;
			}
		}
		return "";
	}

	public void leave(int slot) {
		this.vehicles.remove(slot);
	}

	public void printVehicleNumbers(String color) {
		List<String> numbers = this.getVehicleNumbers(color);
		if(numbers.isEmpty()){
			System.out.println("Not Found");
			return;
		}
		numbers.forEach(System.out::println);
	}

	public void printSlotNumbers(String color) {
		List<Integer> numbers = this.getSlotNumbers(color);
		if(numbers.isEmpty()){
			System.out.println("Not Found");
			return;
		}
		numbers.forEach(System.out::println);
	}
	
	public void printSlotNumber(String vehicleNumber){
		int slot = this.getSlot(vehicleNumber);
		if(slot == -1){
			System.out.println(StringConstants.NOT_FOUND);
		}else{
			System.out.println(slot);
		}
	}

	// returns -1 if no vehicle is matching with given number.
	public int getSlot(String vehicleNumber) {
		Vehicle vehicle = this.getVehicle(vehicleNumber);
		return vehicle == null ? -1 : vehicle.getSlot();
	}

	public String getVehicleNumber(int slot) {
		Vehicle vehicle = this.vehicles.get(slot);
		return vehicle != null ? vehicle.getRegNumber() : StringConstants.SLOT_AVAILABLE_FOR_PARKING;
	}

	public List<Integer> getSlotNumbers(String color) {
		return this.vehicles.values().stream().parallel().filter(vehicle -> vehicle.isSameColor(color))
				.map(Vehicle::getSlot).collect(Collectors.toList());
	}

	public List<String> getVehicleNumbers(String color) {
		return this.vehicles.values().stream().parallel().filter(vehicle -> vehicle.isSameColor(color))
				.map(Vehicle::getRegNumber).collect(Collectors.toList());
	}

	public Vehicle getVehicle(String  vehicleNumber) {
		Optional<Vehicle> optional = this.vehicles.values().stream()
				.parallel().filter(item -> item.isSameVehicle(vehicleNumber)).findFirst(); 
		return optional.isPresent() ? optional.get() : null;
	}

}
