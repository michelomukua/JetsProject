package com.skilldistillery.jets.app;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entities.Airfield;
import com.skilldistillery.jets.entities.BusinessJet;
import com.skilldistillery.jets.entities.CargoCarrier;
import com.skilldistillery.jets.entities.CargoPlane;
import com.skilldistillery.jets.entities.CombatReady;
import com.skilldistillery.jets.entities.FighterJet;
import com.skilldistillery.jets.entities.Jet;
import com.skilldistillery.jets.entities.PassengerPlane;

public class JetsApplication {

	private Airfield airfield;
	private Scanner scanner;
	
	public static void main(String[] args) {
		
		JetsApplication app = new JetsApplication();
		app.launch();
	}
	
	
	private void launch() {
		airfield = new Airfield();
		scanner = new Scanner(System.in);
		List<Jet> fleet = loadFleetFromFile("jetData.txt");
		airfield.setFleet(fleet);
		displayUserMenu();
	}

	private void displayUserMenu() {
		
		String option = "0";
		
		while(!option.equals("9")) {
			System.out.println("Select the option from the menu :");
			System.out.println("1.List fleet ");
			System.out.println("2.Fly all jets ");
			System.out.println("3.View fastest jet ");
			System.out.println("4.View jet with longest range ");
			System.out.println("5.Load all Cargo Jets ");
			System.out.println("6.Dogfight! ");
			System.out.println("7.Add a jet to Fleet ");
			System.out.println("8.Remove a jet from Fleet ");
			System.out.println("9.Quit ");
			
			option = scanner.nextLine();
			
			takeOptionAction(option);
			
		}
		
	}
	
	private void takeOptionAction(String option) {
		
		if(option.equals("1")) {
			listFleet();
		}else if(option.equals("2")) {
			flyAllJets();
		}else if(option.equals("3")) {
			findFastestJet();
		}else if(option.equals("4")) {
			findLongestRange();
		}else if(option.equals("5")) {
			loadAllCargoPlanes();
		}else if(option.equals("6")) {
			dogFight();
		}else if(option.equals("7")) {
			addPlaneToFleet();
		}else if(option.equals("8")) {
			removePlane();
		}else if(option.equals("9")) {
			System.out.println("Exiting Jets Application");
		}else {
			System.out.println("Invalid menu choice!");
		}
		
		System.out.println(" ");
		
	}
		
	private List<Jet> loadFleetFromFile(String filename) {
		List<Jet> jets = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] fields = line.split("\\|");
            	
            	Jet jetToAdd = createJet(fields);
            	if(jetToAdd != null) {
            		jets.add(jetToAdd);
            	}
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return jets;

	}
	
	private Jet createJet(String[] fields) {		
		Jet jet = null;
		String type = fields[0];
				
		if(type.equals("B")) {
			jet = new BusinessJet(fields[1],
					Double.valueOf(fields[2]),
					Integer.valueOf(fields[3]).intValue(),
					Long.valueOf(fields[4]));
			
		}else if(type.equals("F")) {
			jet = new FighterJet(fields[1],
					Double.valueOf(fields[2]),
					Integer.valueOf(fields[3]).intValue(),
					Long.valueOf(fields[4]));
						
		}else if(type.equals("C")) {
			jet = new CargoPlane(fields[1],
					Double.valueOf(fields[2]),
					Integer.valueOf(fields[3]).intValue(),
					Long.valueOf(fields[4]));				
		}else if(type.equals("P")) {
			jet = new PassengerPlane(fields[1],
					Double.valueOf(fields[2]),
					Integer.valueOf(fields[3]).intValue(),
					Long.valueOf(fields[4]));
			
		}			
		return jet;
		
	}
	
	private void listFleet() {
		List<Jet> fleet = airfield.getFleet();
		for(int i=0;i<fleet.size();i++) {
			fleet.get(i).printJet();
			
		}
	}
	
	private void flyAllJets() {
		List<Jet> fleet = airfield.getFleet();
		for(int i=0;i<fleet.size();i++) {
			fleet.get(i).fly();
			
		}
	}
	
	private void findFastestJet() {
		List<Jet> fleet = airfield.getFleet();
		double speed = 0.0;
		int position = -1;
		for(int i=0;i<fleet.size();i++) {			
			if(fleet.get(i).getSpeed() > speed) {				
				speed = fleet.get(i).getSpeed();
				position = i;
			}			
		}
		
		System.out.println("The fastest jets is :");
		fleet.get(position).printJet();		
	}
	
	private void findLongestRange() {
		List<Jet> fleet = airfield.getFleet();
		int range = 0;
		int position = -1;
		for(int i=0;i<fleet.size();i++) {			
			if(fleet.get(i).getRange() > range) {				
				range = fleet.get(i).getRange();
				position = i;
			}			
		}
		
		System.out.println("The longest range jet is :");
		fleet.get(position).printJet();		
	}
	
	
	private void loadAllCargoPlanes() {
		List<Jet> fleet = airfield.getFleet();
		
		for(int i=0;i<fleet.size();i++) {			
			if(fleet.get(i) instanceof CargoCarrier) {				
				CargoPlane cp = (CargoPlane) fleet.get(i);
				cp.loadCargo();
			}			
		}		
	}
	
	private void dogFight() {
		List<Jet> fleet = airfield.getFleet();
		
		for(int i=0;i<fleet.size();i++) {			
			if(fleet.get(i) instanceof CombatReady) {				
				FighterJet fj = (FighterJet) fleet.get(i);
				fj.fight();
			}			
		}			
	}
	
	private void addPlaneToFleet() {
		PassengerPlane pp = new PassengerPlane("Boeing 747",670, 20000, 30000000);
		airfield.getFleet().add(pp);
		System.out.println(" Passenger Plane has been added to fleet :");
		pp.printJet();		
	}
	
	private void removePlane() {
		List<Jet> fleet = airfield.getFleet();
		System.out.println("Select Plane to delete : ");
		for(int i=0;i<fleet.size();i++) {
			System.out.println(i+1+"."+fleet.get(i).getModel());			
		}
		
		String plane = scanner.nextLine();
		
		int position = Integer.valueOf(plane).intValue() -1;
		if(position < fleet.size()){
			airfield.getFleet().remove(position);
			System.out.println("Selected plane has been removed");
			
		}else {
			System.out.println("Invalid choice");
		}		
		
	}
	
	
}


