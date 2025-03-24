package com.skilldistillery.jets.entities;

public class FighterJet extends Jet implements CombatReady{

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fight() {
		System.out.println("Fighter Jet : " +this.getModel()  + " is ready for a Dogfight!");
		
	}
	
	
	
}

