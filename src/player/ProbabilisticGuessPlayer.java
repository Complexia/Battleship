package player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import ship.AircraftCarrier;
import ship.Cruiser;
import ship.Frigate;
import ship.PatrolCraft;
import ship.Submarine;
import world.World;

/**
 * Probabilistic guess player (task C).
 * Please implement this class.
 *
 * @author Youhan Xia, Jeffrey Chan
 */
public class ProbabilisticGuessPlayer  implements Player{
	
	World world;
	private int AircraftCarrier = 6;
	private int Frigate = 4;
	private int Submarine = 3;
	private int Cruiser = 4;
	private int PatrolCraft = 2;
	private int[] alreadyHit;
	private int[] submarineCount;
	private int[] patrolCraftCount;
	private int[] cruiserCount;
	private int[] frigateCount;
	private int[] aircraftCount;
	private int[] totalCount;
	private cs[] seek;
	private cs[] seek1;
	private cs[] seek2;
	private cs[] seek3;
	private cs[] seek4;
	
	private boolean hunt;
	private boolean target;
	int nextShot = 0;
	cs originalHit;
	cs currentHit;
	cs nextGuess;
	int nextGuess1;
	int option = 0;
	int checker = 0;
	int selection = 0;
	int changer = 0;
	String shipName = "";
	int numHits = 0;
	int infiniteCounter = 0;
	int cruiserLength = 2;
	int aircraftLength = 3;
	int cheeker = 0;
	int gridShot = 0;
	

    @Override
    public void initialisePlayer(World world) {
    	this.world = world;
    	hunt = true;
    	target = false;
    	alreadyHit = new int[world.numColumn * world.numRow];
    	frigateCount = new int[world.numColumn * world.numRow];
    	
    	//Calculating for Frigate
    	int k = 0;
    	int j = 0;
    	for(int i=0;i<frigateCount.length;i++) {
    		if(j == frigateCount.length - world.numColumn && i == world.numColumn) {
    			break;
    		}
    		if(i == world.numColumn) {
    			j = j + i;
    			i = 0;
    			if(j < frigateCount.length - (Frigate * world.numRow - 10)) {
    				if(k <  Frigate - 1) {
        				k++;
        			}
    			}
    			else {
    				k--;
    			}
    			
    			
    		}
    		if(i == 0) {
    			frigateCount[j + i] += 2 + k;
    			
    		}
    		else if(i < Frigate) {
    			frigateCount[j + i] += 2 + i + k;
    		}
    		else if(world.numColumn - 1 - i >= Frigate - 1) {
    			frigateCount[j + i] = frigateCount[j + i-1];
    		}
    		else if(world.numColumn - 1 -i < Frigate - 1) {
    			frigateCount[j + i] = frigateCount[j + i-1] - 1;
    		}
    		
    		
    		System.out.println(i+j + " " + frigateCount[j + i]);
    		
    	}
    	
    	//Calculating for Submarine
    	submarineCount = new int[world.numColumn * world.numRow];
    	k = 0;
    	j = 0;
    	for(int i=0;i<submarineCount.length;i++) {
    		if(j == submarineCount.length - world.numColumn && i == world.numColumn) {
    			break;
    		}
    		if(i == world.numColumn) {
    			j = j + i;
    			i = 0;
    			if(j < submarineCount.length - (Submarine * world.numRow - 10)) {
    				if(k <  Submarine - 1) {
        				k++;
        			}
    			}
    			else {
    				k--;
    			}
    			
    			
    		}
    		if(i == 0) {
    			submarineCount[j + i] = +  2 + k;
    			
    		}
    		else if(i < Submarine) {
    			submarineCount[j + i] = + 2 + i + k;
    		}
    		else if(world.numColumn - 1 - i >= Submarine - 1) {
    			submarineCount[j + i] = submarineCount[j + i-1];
    		}
    		else if(world.numColumn - 1 -i < Submarine - 1) {
    			submarineCount[j + i] = submarineCount[j + i-1] - 1;
    		}
    		
    		
    		System.out.println(i+j + " " + submarineCount[j + i]);
    		
    	}
    	
    	//Calculating for PatrolCraft
    	patrolCraftCount = new int[world.numColumn * world.numRow];
    	k = 0;
    	j = 0;
    	for(int i=0;i<patrolCraftCount.length;i++) {
    		if(j == patrolCraftCount.length - world.numColumn && i == world.numColumn) {
    			break;
    		}
    		if(i == world.numColumn) {
    			j = j + i;
    			i = 0;
    			if(j < patrolCraftCount.length - (PatrolCraft * world.numRow - 10)) {
    				if(k <  PatrolCraft - 1) {
        				k++;
        			}
    			}
    			else {
    				k--;
    			}
    			
    			
    		}
    		if(i == 0) {
    			patrolCraftCount[j + i] = +  2 + k;
    			
    		}
    		else if(i < PatrolCraft) {
    			patrolCraftCount[j + i] = + 2 + i + k;
    		}
    		else if(world.numColumn - 1 - i >= PatrolCraft - 1) {
    			patrolCraftCount[j + i] = patrolCraftCount[j + i-1];
    		}
    		else if(world.numColumn - 1 -i < PatrolCraft - 1) {
    			patrolCraftCount[j + i] = patrolCraftCount[j + i-1] - 1;
    		}
    		
    		
    		System.out.println(i+j + " " + patrolCraftCount[j + i]);
    		
    	}
    	
    	//Calculating for Cruiser
    	cruiserCount = new int[world.numColumn * world.numRow];
    	k = 0;
    	j = 0;
    	for(int i=0;i<cruiserCount.length;i++) {
    		if(j == cruiserCount.length - world.numColumn && i == world.numColumn) {
    			break;
    		}
    		if(i == world.numColumn) {
    			j = j + i;
    			i = 0;
    			if(j < cruiserCount.length - (cruiserLength * world.numRow - 10)) {
    				if(k <  cruiserLength - 1) {
        				k++;
        			}
    			}
    			else {
    				k--;
    			}
    			
    			
    		}
    		if(i == 0) {
    			cruiserCount[j + i] = +  2 + k;
    			
    		}
    		else if(i < cruiserLength) {
    			if(j < 10) {
    				cruiserCount[j + i] = + 2 + i + 1  + k; // + extra 1 for width
    			}
    			else {
    				cruiserCount[j + i] = (+2 + i + 1  + k)*2; // x 2 for width
    			}
    			
    		}
    		else if(world.numColumn - 1 - i >= cruiserLength - 1) {
    			cruiserCount[j + i] = cruiserCount[j + i-1];
    		}
    		else if(world.numColumn - 1 -i < cruiserLength - 1) {
    			cruiserCount[j + i] = cruiserCount[j + i-1] - 1 - 1; // - extra 1 for width
    		}
    		
    		if(i == 9) {
    			cruiserCount[j + i] = 2 + k;
    		}
    		
    		
    		System.out.println(i+j + " " + cruiserCount[j + i]);
    		
    	}
    	
    	//Calculating for AircraftCarrier
    	aircraftCount = new int[world.numColumn * world.numRow];
    	k = 0;
    	j = 0;
    	for(int i=0;i<aircraftCount.length;i++) {
    		if(j == aircraftCount.length - world.numColumn && i == world.numColumn) {
    			break;
    		}
    		if(i == world.numColumn) {
    			j = j + i;
    			i = 0;
    			if(j < aircraftCount.length - (aircraftLength * world.numRow - 10)) {
    				if(k <  aircraftLength - 1) {
        				k++;
        			}
    			}
    			else {
    				k--;
    			}			
    		}
    		if(i == 0) {
    			aircraftCount[j + i] = +  2 + k;
    			
    		}
    		else if(i < aircraftLength) {
    			if(j < 10) {
    				aircraftCount[j + i] = + 2 + i  + k; // + extra 1 for width
    			}
    			else {
    				aircraftCount[j + i] = (+ 2 + i  + k)*2; // x 2 for width
    			}
    			
    		}
    		else if(world.numColumn - 1 - i >= aircraftLength - 1) {
    			aircraftCount[j + i] = aircraftCount[j + i-1];
    		}
    		else if(world.numColumn - 1 -i < aircraftLength - 1) {
    			aircraftCount[j + i] = aircraftCount[j + i-1] - 1 - 1; // - extra 1 for width
    		}
    		if(i == 9) {
    			aircraftCount[j + i] = 2 + k;
    		}
    		
    		
    		System.out.println(i+j + " " + aircraftCount[j + i]);
    		
    	}
    	
    	//calculating Total
    	totalCount = new int[world.numColumn * world.numRow];
    	for(int i=0;i<totalCount.length;i++) {
    		totalCount[i] = totalCount[i] + frigateCount[i] + submarineCount[i] + patrolCraftCount[i] + cruiserCount[i] + aircraftCount[i];
    		
    	}
    	
    	
    } // end of initialisePlayer()

    @Override
    public Answer getAnswer(Guess guess) {
    	Answer answer = new Answer();
    	for(int i=0;i<world.shipLocations.size();i++) {
    		for(int j = 0;j<world.shipLocations.get(i).coordinates.size();j++) {
    			if(world.shipLocations.get(i).coordinates.get(j).column == guess.column && world.shipLocations.get(i).coordinates.get(j).row == guess.row) {
        			
        			answer.isHit = true;
        			
        			String shipName = world.shipLocations.get(i).ship.name();
                    switch (shipName) {
                        case "AircraftCarrier":
                            AircraftCarrier--;
                            
                            if(AircraftCarrier < 0) {
                            	AircraftCarrier = 0;
                            	
                            }
                            if(AircraftCarrier == 0) {
                            	answer.shipSunk = new AircraftCarrier();
                            }
                            break;
                        case "Frigate":
                            Frigate--;
                            if(Frigate < 0) {
                            	Frigate = 0;
                            }
                            if(Frigate == 0) {
                            	answer.shipSunk = new Frigate();
                            }
                            break;
                        case "Submarine":
                            Submarine--;
                            if(Submarine < 0) {
                            	Submarine = 0;
                            }
                            if(Submarine == 0) {
                            	answer.shipSunk = new Submarine();
                            }
                            break;
                        case "Cruiser":
                            Cruiser--;
                            if(Cruiser < 0) {
                            	Cruiser = 0;
                            }
                            if(Cruiser == 0) {
                            	answer.shipSunk = new Cruiser();
                            }
                            break;
                        case "PatrolCraft":
                            PatrolCraft--;
                            if(PatrolCraft < 0) {
                            	PatrolCraft = 0;
                            }
                            if(PatrolCraft == 0) {
                            	answer.shipSunk = new PatrolCraft();
                            }
                            break;
                        
                    }
        			
        		}
//    			
    		}
    		
    	}

        
    	return answer;
    } // end of getAnswer()

    Random r = new Random();
    @Override
    public Guess makeGuess() {
        
    	Guess guess = new Guess();
    	
    	
    	
    	
    	if(hunt) {
    		int m = 0;
    		for(int i = 0;i<totalCount.length;i++) {
    			if(totalCount[i] > m) {
    				m = totalCount[i];
    			}
    		}
    		ArrayList<Integer> nextShots = new ArrayList<Integer>();
    		for(int i=0;i<totalCount.length;i++) {
    			if(totalCount[i] == m) {
    				nextShots.add(i);
    			}
    		}
    		nextShot = nextShots.get(r.nextInt(nextShots.size()));
    		
    		while(totalCount[nextShot] <= 0) {
    			for(int i=0;i<totalCount.length;i++) {
        			if(totalCount[i] == m) {
        				nextShots.add(i);
        			}
        		}
    			nextShot = nextShots.get(r.nextInt(nextShots.size()));
    		}
    		
    		if(nextShot < world.numColumn) {
        		guess.column = world.numColumn - (world.numColumn - nextShot);
        		guess.row = 0;
        	}
        	else {
        		int division = nextShot/world.numColumn;
        		int modulo = nextShot%world.numColumn;
        		if(modulo == 0) {
        			guess.column = 0;
        			guess.row = division;
        		}
        		else {
        			guess.column = modulo;
        			guess.row = division;
        		}
        	}
    		
    	}
    	else { //target

    		gridShot = nextGuess.row * world.numColumn + nextGuess.column;
    		guess.column = nextGuess.column;
    		guess.row = nextGuess.row;
    		while(totalCount[gridShot] <= 0) {
    			System.out.println("What?");
    			
    			
    			gridShot = nextGuess.row * world.numColumn + nextGuess.column; 

        		Guess guess1 = new Guess();
        		guess1.column = nextGuess.column;
        		guess1.row = nextGuess.row;
        		Answer answer1 = new Answer();
        		answer1.isHit = false;
        		if(gridShot < 0 || gridShot > 99) {
        			update(guess1,answer1);
        		}
        		else if(totalCount[gridShot] <= 0) {
        			System.out.println(gridShot + " " + totalCount[gridShot]);
        			update(guess1,answer1);
        		}
            	
	    		guess.column = nextGuess.column;
	    		guess.row = nextGuess.row;
	    		
    		
    	}
    	

    	
        
    	}
    	return guess;
    } // end of makeGuess()


    @Override
    public void update(Guess guess, Answer answer) {
    	//Setting the coordinate probability count to zero
    	totalCount[nextShot] = 0;
    	//recalculating probable hits for Frigate
    	for(int i=0;i<totalCount.length;i++) {
    		if(totalCount[i] > 0) {
    			int division = nextShot/world.numColumn;
        		int modulo =nextShot%world.numColumn;
        		int divisionI = i/world.numColumn;
        		int moduloI =i%world.numColumn;
        		int xax =(modulo - moduloI); //xax is the distance along the x axis
        		if(xax < 0) {
        			xax = xax * -1;
        		}
        		int yax = (division - divisionI); //yax is the distance along the y axis
        		if(yax < 0) {
        			yax = yax * -1;
        		}
        		
        		if(xax < Frigate && division == divisionI) {
        			xax--;
        			totalCount[i] = totalCount[i] - xax;
     
        		}
        		if(yax < Frigate && modulo == moduloI) {
        			yax--;
        			totalCount[i] = totalCount[i] - yax;
        		}
        		if(totalCount[i] < 0) {
        			totalCount[i] = 0;
        		}
    		}
    		
    		
    	}
    	//Recalculating hits for PatrolCraft
    	for(int i=0;i<totalCount.length;i++) {
    		if(totalCount[i] > 0) {
    			int division = nextShot/world.numColumn;
        		int modulo =nextShot%world.numColumn;
        		if(nextShot < world.numColumn) {
        			modulo = nextShot;
        		}
        		
        		int divisionI = i/world.numColumn;
        		int moduloI =i%world.numColumn;
        		if(i < world.numColumn) {
        			modulo = nextShot;
        		}
        		int xax =(modulo - moduloI);
        		if(xax < 0) {
        			xax = xax * -1;
        		}
        		int yax = (division - divisionI);
        		if(yax < 0) {
        			yax = yax * -1;
        		
        		}
        		
        		int hax = totalCount[i];
        		if(xax < PatrolCraft && division == divisionI) {
        			xax--;
        			totalCount[i] = totalCount[i] - xax;
        		}
        		if(yax < PatrolCraft && modulo == moduloI) {
        			yax--;
        			totalCount[i] = totalCount[i] - yax;
        		}
        		if(totalCount[i] < 0) {
        			totalCount[i] = 0;
        		}
    		}
    		
   	
    	}
    	//Recalculating hits for Submarine
    	for(int i=0;i<totalCount.length;i++) {
    		if(totalCount[i] > 0) {
    			int division = nextShot/world.numColumn;
        		int modulo =nextShot%world.numColumn;
        		if(nextShot < world.numColumn) {
        			modulo = nextShot;
        		}
        		
        		int divisionI = i/world.numColumn;
        		int moduloI =i%world.numColumn;
        		if(i < world.numColumn) {
        			modulo = nextShot;
        		}
        		int xax =(modulo - moduloI);
        		if(xax < 0) {
        			xax = xax * -1;
        		}
        		int yax = (division - divisionI);
        		if(yax < 0) {
        			yax = yax * -1;
        		
        		}
        		
        		int hax = totalCount[i];
        		if(xax < Submarine && division == divisionI) {
        			xax--;
        			totalCount[i] = totalCount[i] - xax;
        		}
        		if(yax < Submarine && modulo == moduloI) {
        			yax--;
        			totalCount[i] = totalCount[i] - yax;
        		}
        		if(totalCount[i] < 0) {
        			totalCount[i] = 0;
        		}
       	
    		}
    		
    	}
    	
    	//Recalculating hits for AircraftCarrier
    	for(int i=0;i<totalCount.length;i++) {
    		if(totalCount[i] > 0) {
    			int division = nextShot/world.numColumn;
        		int modulo =nextShot%world.numColumn;
        		if(nextShot < world.numColumn) {
        			modulo = nextShot;
        		}
        		
        		int divisionI = i/world.numColumn;
        		int moduloI =i%world.numColumn;
        		if(i < world.numColumn) {
        			modulo = nextShot;
        		}
        		int xax =(modulo - moduloI);
        		if(xax < 0) {
        			xax = xax * -1;
        		}
        		int yax = (division - divisionI);
        		if(yax < 0) {
        			yax = yax * -1;
        		
        		}
        		
        		int hax = totalCount[i];
        		if(xax < aircraftLength && division == divisionI) {
        			xax--;
        			totalCount[i] = totalCount[i] - xax;
        		}
        		if(yax < aircraftLength && modulo == moduloI) {
        			yax--;
        			totalCount[i] = totalCount[i] - yax;
        		}
        		if(totalCount[i] < 0) {
        			totalCount[i] = 0;
        		}
    		}
    		
   	
    	}
    	
    	//Recalculating hits for Cruiser
    	for(int i=0;i<totalCount.length;i++) {
    		if(totalCount[i] > 0) {
    			int division = nextShot/world.numColumn;
        		int modulo =nextShot%world.numColumn;
        		if(nextShot < world.numColumn) {
        			modulo = nextShot;
        		}
        		
        		int divisionI = i/world.numColumn;
        		int moduloI =i%world.numColumn;
        		if(i < world.numColumn) {
        			modulo = nextShot;
        		}
        		int xax =(modulo - moduloI); //xax is the distance along the x axis (column)
        		if(xax < 0) {
        			xax = xax * -1;
        		}
        		int yax = (division - divisionI); //yax is the distance along the y axis
        		if(yax < 0) {
        			yax = yax * -1;
        			
        		
        		}
        		
        		int hax = totalCount[i];
        		if(xax < cruiserLength && division == divisionI) { 
        			xax--;
        			totalCount[i] = totalCount[i] - xax;
        		}
        		if(yax < cruiserLength && modulo == moduloI) {
        			yax--;
        			totalCount[i] = totalCount[i] - yax;
        		}
        		if(totalCount[i] < 0) {
        			totalCount[i] = 0;
        		}
    		}
    		
   	
    	}
        if(hunt) {// hunt mode
        	if(answer.isHit) {
        		hunt = false;
            	target = true;
            	originalHit = new cs(guess.column,guess.row); //The hit that was originally 
            	currentHit = new cs(guess.column,guess.row);  
            	option = 0;
            	selection = 0;
            	changer = 0;
        	}
        	
        }
        if(target) { // target mode 
        	
        	totalCount[gridShot] = 0;
        	if(answer.isHit) {
    			numHits++;
    			if(answer.shipSunk != null) {
    				System.out.println("shiplength " + answer.shipSunk.len());
    				//this is commented out because it doesn't work properly, but the idea is there
    				//recalculating Total
//    		    	if(answer.shipSunk.name().equalsIgnoreCase("Frigate")) {
//    		    		for(int i=0;i<totalCount.length;i++) {
//        		    		totalCount[i] = totalCount[i] - frigateCount[i];
//        		    		
//        		    	}
//    		    	}
//    		    	if(answer.shipSunk.name().equalsIgnoreCase("Submarine")) {
//    		    		for(int i=0;i<totalCount.length;i++) {
//        		    		totalCount[i] = totalCount[i] - submarineCount[i];
//        		    		
//        		    	}
//    		    	}
//    		    	if(answer.shipSunk.name().equalsIgnoreCase("PatrolCraft")) {
//    		    		for(int i=0;i<totalCount.length;i++) {
//        		    		totalCount[i] = totalCount[i] - patrolCraftCount[i];
//        		    		
//        		    	}
//    		    	}
//    		    	if(answer.shipSunk.name().equalsIgnoreCase("Cruiser")) {
//    		    		for(int i=0;i<totalCount.length;i++) {
//        		    		totalCount[i] = totalCount[i] - cruiserCount[i];
//        		    		
//        		    	}
//    		    	}
//    		    	if(answer.shipSunk.name().equalsIgnoreCase("AircraftCarrier")) {
//    		    		for(int i=0;i<totalCount.length;i++) {
//        		    		totalCount[i] = totalCount[i] - aircraftCount[i];
//        		    		
//        		    	}
//    		    	}
//    		    	
    				if(answer.shipSunk.len() * answer.shipSunk.width() < numHits) {
    					numHits = numHits - answer.shipSunk.len() * answer.shipSunk.width();
    					System.out.println("Keep targeting");
    				}
    				else {
    					System.out.println("YESYESYES");
        				target = false;
        				hunt = true;
        				option = 0;
        				selection = 0;
        				numHits = 0;
    				}
    				
    				
    			}
    			
    			if(option == 0) {
    				if(world.numRow - originalHit.row - 1 == 0) {
    					option = 1;
    				}
    				else {
    					seek = new cs[world.numRow - originalHit.row-1];
        				for(int i=0;i<seek.length;i++) {
        					
        					seek[i] = new cs(originalHit.column, originalHit.row +1 +  i);
        				}
        				if(selection > seek.length - 1) {
        					selection = 0;
        					option++;
        				}
        				else {
        					nextGuess = seek[selection];
            				selection++;
        				}
    				}
    					
    				
    			}
    			
    			if(option == 1) {
    				if(originalHit.row == 0) {
    					option = 2;
    				}
    				else {
    					seek = new cs[originalHit.row];
        				for(int i=0;i<seek.length;i++) {
        					
        					seek[i] = new cs(originalHit.column, originalHit.row -1-i);
        				}
        				if(selection > seek.length - 1) {
        					selection = 0;
        					option++;
        				}
        				else {
        					nextGuess = seek[selection];
            				selection++;
        				}
        				
    				}
    				
    			}
    			
    			if(option == 2) {
    				if(world.numColumn - originalHit.column - 1 == 0) {
    					option = 3;
    				}
    				else {
    					seek = new cs[world.numColumn - originalHit.column-1];
        				for(int i=0;i<seek.length;i++) {
        					
        					seek[i] = new cs(originalHit.column + 1 + i, originalHit.row);
        				}
        				if(selection > seek.length - 1) {
        					selection = 0;
        					option++;
        				}
        				else {
        					nextGuess = seek[selection];
            				selection++;
        				}
    				}
    				
    			}
    			
    			if(option == 3) {
    				if(originalHit.column == 0) {
    					answer.isHit = false;
    				}
    				seek = new cs[originalHit.column];
    				for(int i=0;i<seek.length;i++) {
    					
    					seek[i] = new cs(originalHit.column - 1 - i, originalHit.row);
    				}
    				if(selection > seek.length - 1) {
    					selection = 0;
    					answer.isHit = false;
    				}
    				else {
    					nextGuess = seek[selection];
        				selection++;
    				}
    			}
    			
    			
    			
    			

            	
    			
        		
    			
    		}
    		if(!answer.isHit) {
    			
    			if(option == 0 && selection == 0 && changer > 0) {
    				option = 4;
    			}
    			answer.isHit = true;
    			
    			selection = 0;
    			option++;
    			numHits--;
    			update(guess,answer);
    			
    			if(option > 3) {
    				if(changer == 0) {
    					originalHit = new cs(currentHit.column -1, currentHit.row - 1);
    					
        				changer = 1;
        				
    				}
    				else if(changer == 1) {
    					
    					originalHit = new cs(currentHit.column +1, currentHit.row - 1);
    					
    					changer = 2;
    					
    				}
    				else if(changer == 2) {
    					
    					originalHit = new cs(currentHit.column -1, currentHit.row + 1);
    					
    					changer = 3;
    					
    				}
    				else if(changer == 3) {
    					originalHit = new cs(currentHit.column +1, currentHit.row + 1);
    					changer = 4;
    				}
    				
    				
    				
    				else if(changer > 3) {
    					int something = nextShot + 9 + infiniteCounter;
    					if(something > 99) {
    						something = nextShot - 9 + infiniteCounter;
    					}
    					int column = something % world.numColumn;
    					int row = something / world.numRow;
    					currentHit = new cs(column,row);
    					changer = 0;
    					originalHit = currentHit;
    					
    					infiniteCounter++;
    					
    					
    					System.out.println("changer overextended");
    				}
    				option = 0;
    				
    				nextGuess = originalHit;
    				
    				
    			}
    			
    			

    			
    			
            		
    		}
    		

        	
        	
        }
        
    } // end of update()


    @Override
    public boolean noRemainingShips() {
    	//System.out.println(AircraftCarrier + " " + Frigate + " " + Submarine + " " + PatrolCraft + " " + Cruiser);
        if(AircraftCarrier == 0 && Frigate == 0 && Submarine == 0 && PatrolCraft == 0 && Cruiser == 0) {
        	return true;
        }
        return false;
    } // end of noRemainingShips()
    
    private class cs {
    	int column;
    	int row;
		public cs(int column, int row) {
			this.column = column;
			this.row = row;
		}
    	
    }

} // end of class ProbabilisticGuessPlayer
