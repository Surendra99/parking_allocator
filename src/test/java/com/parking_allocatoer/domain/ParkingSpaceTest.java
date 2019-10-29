package com.parking_allocatoer.domain;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.parking_allocater.domain.ParkingSpace;
import com.parking_allocater.util.StringConstants;

public class ParkingSpaceTest {

	@Test
	public void should_park_vehicles() {
		ParkingSpace parkingSpace = new ParkingSpace(6);
		parkingSpace.park("KA-01-HH-1234", "White");
		parkingSpace.park("KA-01-HH-1235", "White");
		parkingSpace.park("KA-01-HH-1233", "White");
		parkingSpace.park("KA-01-HH-1232", "White");
		parkingSpace.park("KA-01-HH-1237", "White");
		parkingSpace.park("KA-01-HH-1231", "White");
		assertTrue(parkingSpace.park("KA-01-HH-1235", "White")
				.equals(StringConstants.NO_SLOT_AVAILABLE));
		parkingSpace.print();
	}
	
	@Test
	public void should_get_no_of_vehicle_per_color(){
		ParkingSpace parkingSpace = new ParkingSpace(6);
		parkingSpace.park("KA-01-HH-1234", "White");
		parkingSpace.park("KA-01-HH-1235", "Blue");
		parkingSpace.park("KA-01-HH-1233", "Black");
		parkingSpace.park("KA-01-HH-1232", "White");
		parkingSpace.park("KA-01-HH-1237", "White");
		parkingSpace.park("KA-01-HH-1231", "Blue");
		
		assertEquals(parkingSpace.getVehicleNumbers("White").size(),3);
	}
	
	@Test
	public void should_vehicle_number_in_slot(){
		ParkingSpace parkingSpace = new ParkingSpace(6);
		parkingSpace.park("KA-01-HH-1234", "White");
		parkingSpace.park("KA-01-HH-1235", "Blue");	
		
		assertEquals(2,parkingSpace.getSlot("KA-01-HH-1235"));
		assertEquals(-1,parkingSpace.getSlot("KA-01-HH-12"));
	}
	
	@Test
	public void should_get_vehicle_no_from_slot(){
		ParkingSpace parkingSpace = new ParkingSpace(6);
		parkingSpace.park("KA-01-HH-1234", "White");
		parkingSpace.park("KA-01-HH-1235", "Blue");
		
		assertTrue("KA-01-HH-1235".equals(parkingSpace.getVehicleNumber(2)));
		assertEquals(StringConstants.SLOT_AVAILABLE_FOR_PARKING,parkingSpace.getVehicleNumber(-1));
	}
	
	@Test
	public void should_get_slot_numbers(){
		ParkingSpace parkingSpace = new ParkingSpace(6);
		parkingSpace.park("KA-01-HH-1234", "White");
		parkingSpace.park("KA-01-HH-1235", "Blue");
		parkingSpace.park("KA-01-HH-1233", "Black");
		parkingSpace.park("KA-01-HH-1232", "White");
		parkingSpace.park("KA-01-HH-1237", "White");
		parkingSpace.park("KA-01-HH-1231", "Blue");
		
		Integer[] slots = {1,4,5}; 
		String color = "white";
		parkingSpace.printSlotNumbers(color);
		assertEquals(Arrays.asList(slots),parkingSpace.getSlotNumbers(color));
	}
	
	@Test
	public void get_vehicle_numbers_by_color(){
		ParkingSpace parkingSpace = new ParkingSpace(6);
		parkingSpace.park("KA-01-HH-1234", "White");
		parkingSpace.park("KA-01-HH-1235", "Blue");
		parkingSpace.park("KA-01-HH-1233", "Black");
		parkingSpace.park("KA-01-HH-1232", "White");
		parkingSpace.park("KA-01-HH-1237", "White");
		parkingSpace.park("KA-01-HH-1231", "Blue");
		
		String[] slots = {"KA-01-HH-1235","KA-01-HH-1231"};
		String color = "blue";
		parkingSpace.printVehicleNumbers(color);
		assertEquals(Arrays.asList(slots),parkingSpace.getVehicleNumbers(color));
	}
	
	@Test
	public void should_assign_nearest_slot(){
		ParkingSpace parkingSpace = new ParkingSpace(6);
		parkingSpace.park("KA-01-HH-1234", "White");
		parkingSpace.park("KA-01-HH-1235", "Blue");
		parkingSpace.park("KA-01-HH-1232", "Black");
		
		parkingSpace.leave(1);
		assertEquals(StringConstants.SLOT_AVAILABLE_FOR_PARKING,parkingSpace.getVehicleNumber(1));
		
		parkingSpace.park("KA-01-HH-1111", "Black");
		assertEquals("KA-01-HH-1111",parkingSpace.getVehicleNumber(1));
		
	}

}
