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
    		System.out.println("Stuck in hunt?");
    		
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
    		if(selection == 0) {
    			selection = 1;
    		}
    		
    		guess.column = seek[selection-1].column;
    		guess.row = seek[selection-1].row;
    		nextShot = guess.row * world.numRow + guess.column;
    		while(totalCount[nextShot] == 0) {
    			System.out.println(nextShot + " " + cheeker + " " + changer);
    			Guess guess1 = guess;
    			Answer answer = new Answer();
    			answer.isHit = false;
    			update(guess1,answer);
    			if(cheeker  == 1) {
    				break;
    			}
    			
    		}
    		
    		if(cheeker == 1) {
    			while(totalCount[nextGuess1] == 0) {
    				
    				Guess guess1 = guess;
    				Answer answer = new Answer();
        			answer.isHit = false;
        			update(guess1,answer);
        			
        			
        		}
    			int division = nextGuess1/world.numColumn;
        		int modulo = nextGuess1%world.numColumn;
        		if(modulo == 0) {
        			guess.column = 0;
        			guess.row = division;
        		}
        		else {
        			guess.column = modulo;
        			guess.row = division;
        		}
        		changer = 0;
        		cheeker = 0;
        		hunt = true;
        		Guess guess1 = guess;
    			Answer answer = new Answer();
        		answer.isHit = true;;
    			update(guess1,answer);
    			hunt = false;
        		System.out.println("HERE?" + " " + nextGuess1);
    		}
    		
    	}
    	return guess;
    } // end of makeGuess()


    @Override
    public void update(Guess guess, Answer answer) {
    	totalCount[nextShot] = 0;
        if(hunt) {
        	if(answer.isHit) {
        		
            	target = true;
            	hunt = false;
            	originalHit = new cs(guess.column,guess.row);
            	currentHit = new cs(guess.column,guess.row);
            	option = 0;
            	selection = 0;
            	changer = 0;
            	
            	seek1 = new cs[world.numRow - originalHit.row-1];
				for(int i=0;i<seek1.length;i++) {
					
					seek1[i] = new cs(originalHit.column, originalHit.row +1 +  i);
				}
				seek2 = new cs[originalHit.row];
				for(int i=0;i<seek2.length;i++) {
					
					seek2[i] = new cs(originalHit.column, originalHit.row -1 -  i);
				}
				seek3 = new cs[world.numColumn - originalHit.column-1];
				for(int i=0;i<seek3.length;i++) {
					
					seek3[i] = new cs(originalHit.column + 1 + i, originalHit.row);
				}
				seek4 = new cs[originalHit.column];
				for(int i=0;i<seek4.length;i++) {
					
					seek4[i] = new cs(originalHit.column - 1 - i, originalHit.row);
				}
				
				int s1 = seek1[0].row * world.numRow + seek1[0].column;
        		int s2 = seek2[0].row * world.numRow + seek2[0].column;
        		int s3 = seek3[0].row * world.numRow + seek3[0].column;
        		int s4 = seek4[0].row * world.numRow + seek4[0].column;
        		int order[] = new int[4];
        		order[0] = totalCount[s1];
        		order[1] = totalCount[s2];
        		order[2] = totalCount[s3];
        		order[3] = totalCount[s4];
        		
        		int m = 0;
        		for(int i = 0;i<order.length;i++) {
        			if(order[i] > m) {
        				m = order[i];
        			}
        		}
        		
        		ArrayList<Integer> nextShots = new ArrayList<Integer>();
        		for(int i=0;i<order.length;i++) {
        			if(order[i] == m) {
        				nextShots.add(i);
        			}
        		}
        		int nextIndex = nextShots.get(r.nextInt(nextShots.size()));
        		if(nextIndex == 0) {
        			seek = seek1;
        		}
        		if(nextIndex == 1) {
        			seek = seek2;
        		}
        		if(nextIndex == 2) {
        			seek = seek3;
        		}
        		if(nextIndex == 3) {
        			seek = seek4;
        		}
        		
				
        	}
        }
        if(target) {
        	if(answer.isHit) {
        		numHits++;
    			if(answer.shipSunk != null) {
    				
    				if(answer.shipSunk.len() * answer.shipSunk.width() < numHits) {
    					numHits = numHits - answer.shipSunk.len() * answer.shipSunk.width();
    					System.out.println("Keep targeting");
    				}
    				else {
    					System.out.println("ProbYESYESYES");
        				target = false;
        				hunt = true;
        				option = 0;
        				selection = 0;
        				numHits = 0;
    				}
    				
    				
    			}
    			
    			
        		
    			
    			
				if(selection <= seek.length) {
					selection++;
				}
    				
    			
        	
        	}
        	else {
        		if(changer > 3) {
        			System.out.println("And here?");
        			cheeker = 1;
        			int s1 = (currentHit.row - 1) * world.numRow + (currentHit.column - 1);
        			int s2 = (currentHit.row + 1) * world.numRow + (currentHit.column - 1);
        			int s3 = (currentHit.row - 1) * world.numRow + (currentHit.column + 1);
        			int s4 = (currentHit.row + 1) * world.numRow + (currentHit.column + 1);
        			
        			int order[] = new int[4];
            		order[0] = totalCount[s1];
            		order[1] = totalCount[s2];
            		order[2] = totalCount[s3];
            		order[3] = totalCount[s4];
            		
            		int m = 0;
            		for(int i = 0;i<order.length;i++) {
            			if(order[i] > m) {
            				m = order[i];
            			}
            		}
            		
            		ArrayList<Integer> nextShots = new ArrayList<Integer>();
            		for(int i=0;i<order.length;i++) {
            			if(order[i] == m) {
            				nextShots.add(i);
            			}
            		}
            		int nextIndex = nextShots.get(r.nextInt(nextShots.size()));
            		if(nextIndex == 0) {
            			nextGuess1 = s1;
            		}
            		if(nextIndex == 1) {
            			nextGuess1 = s2;
            		}
            		if(nextIndex == 2) {
            			nextGuess1 = s3;
            		}
            		if(nextIndex == 3) {
            			nextGuess1 = s4;
            		}
            		
            		
        		}
        		currentHit = originalHit;
        		int s1 = seek1[0].row * world.numRow + seek1[0].column;
        		int s2 = seek2[0].row * world.numRow + seek2[0].column;
        		int s3 = seek3[0].row * world.numRow + seek3[0].column;
        		int s4 = seek4[0].row * world.numRow + seek4[0].column;
        		int order[] = new int[4];
        		order[0] = totalCount[s1];
        		order[1] = totalCount[s2];
        		order[2] = totalCount[s3];
        		order[3] = totalCount[s4];
        		
        		int m = 0;
        		for(int i = 0;i<order.length;i++) {
        			if(order[i] > m) {
        				m = order[i];
        			}
        		}
        		
        		ArrayList<Integer> nextShots = new ArrayList<Integer>();
        		for(int i=0;i<order.length;i++) {
        			if(order[i] == m) {
        				nextShots.add(i);
        			}
        		}
        		int nextIndex = nextShots.get(r.nextInt(nextShots.size()));
        		if(nextIndex == 0) {
        			seek = seek1;
        		}
        		if(nextIndex == 1) {
        			seek = seek2;
        		}
        		if(nextIndex == 2) {
        			seek = seek3;
        		}
        		if(nextIndex == 3) {
        			seek = seek4;
        		}
        		
        		selection = 0;
        		changer++;
        		
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
