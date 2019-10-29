package com.parking_allocater.controller;

import com.parking_allocater.service.ParkingAllocaterService;

public class ApplicationController 
{
	public static void main(String args[])
	{
		ParkingAllocaterService.getInstance().readInput();
	}
}
