package com.skilldistillery.jets.entities;

import java.util.ArrayList;
import java.util.List;

public class Airfield {
	
	private List<Jet> fleet; 

	public Airfield() {
		fleet = new ArrayList<>();
	}

	public List<Jet> getFleet() {
		return fleet;
	}

	public void setFleet(List<Jet> fleet) {
		this.fleet = fleet;
	}

	
}
