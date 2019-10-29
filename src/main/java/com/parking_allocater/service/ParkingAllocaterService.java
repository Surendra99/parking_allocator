package com.parking_allocater.service;

import java.util.Scanner;

import com.parking_allocater.domain.ParkingSpace;

public class ParkingAllocaterService {

	private ParkingSpace parkingSpace = null;
	private static ParkingAllocaterService service = null;

	private ParkingAllocaterService() {

	}

	public static ParkingAllocaterService getInstance() {
		if (service == null)
			service = new ParkingAllocaterService();
		return service;
	}

	public void readInput() {
		Scanner scanner = null;
		try {
			while (true) {
				scanner = new Scanner(System.in);
				String string = scanner.nextLine();
				if (string.equalsIgnoreCase("exit")) {
					break;
				}
				this.process(string);
			}
		} catch (Exception e) {
			System.out.println("Program exited because of wrong input.");
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	// made public for testing
	public void process(String string) {
		if (string.contains("create_parking_lot")) {
			this.createParkingSpace(string);
			return;
		}
		if (this.parkingSpace == null) {
			System.out.println("Please create parking space first.");
			return;
		}
		String[] strings = string.split(" ");
		if (string.startsWith("park")) {
			System.out.println(parkingSpace.park(strings[1], strings[2]));
		} else if (string.contains("leave")) {
			parkingSpace.leave(Integer.valueOf(strings[1]));
		} else if (string.contains("status")) {
			parkingSpace.print();
		} else if (string.contains("registration_numbers_for_cars_with_colour")) {
			parkingSpace.printVehicleNumbers(strings[1]);
		} else if (string.contains("slot_numbers_for_cars_with_colour")) {
			parkingSpace.printSlotNumbers(strings[1]);
		} else if (string.contains("slot_number_for_registration_number")) {
			parkingSpace.printSlotNumber(strings[1]);
		}
	}

	private void createParkingSpace(String string) {
		if (this.parkingSpace == null)
			this.parkingSpace = new ParkingSpace(Integer.valueOf(string.split(" ")[1]));
	}
	
	public ParkingSpace getParkingSpace(){
		return this.parkingSpace;
	}
}
