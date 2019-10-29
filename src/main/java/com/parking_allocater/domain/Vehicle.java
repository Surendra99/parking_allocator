package com.parking_allocater.domain;

public class Vehicle {
	private String regNumber;
	private String color;
	private int slot;

	public Vehicle(String number, String color,int slot) {
		this.regNumber = number;
		this.color = color;
		this.slot = slot;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public int getSlot() {
		return slot;
	}

	public boolean isSameColor(String color) {
		return this.color.equalsIgnoreCase(color);
	}

	public boolean isSameVehicle(String number) {
		return this.regNumber.equalsIgnoreCase(number);
	}
	
	public void print(){
		System.out.printf("%-5d%s%n",this.slot,String.format("%5s%5s", this.regNumber,this.color));
	}
}
