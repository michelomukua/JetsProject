package com.skilldistillery.jets.entities;

public class PassengerPlane extends Jet implements PassengerCarrier {
	
	public PassengerPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
		
	}

	@Override
	public void loadPassengers() {
		// TODO Auto-generated method stub
		
	}

}
