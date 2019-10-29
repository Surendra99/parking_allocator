package com.parking_allocatoer.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.parking_allocater.domain.ParkingSpace;
import com.parking_allocater.service.ParkingAllocaterService;

public class ParkingAllocatorServiceTest {
	
	@Test
	public void should_execute_commands(){
		ParkingAllocaterService service = ParkingAllocaterService.getInstance();
		
		//should not throw null pointer exception.
		service.process("park KA-01-HH-1234 White");
		
		service.process("create_parking_lot 6 park");
		
		ParkingSpace space = service.getParkingSpace();
		assertNotNull(service.getParkingSpace());
		
		service.process("park KA-01-HH-1234 White");
		assertEquals(1,space.getSlot("KA-01-HH-1234"));
		
		service.process("leave 1");
		assertEquals(-1,space.getSlot("KA-01-HH-1234"));
		
		service.process("status");
		
		// should print vehicle number if present
		service.process("registration_numbers_for_cars_with_colour white");
		
//		should print vehicle number if present
		service.process("slot_numbers_for_cars_with_colour white");
		
		service.process("slot_number_for_registration_number KA-01-HH-3141");
	}

}
