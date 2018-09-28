package player;

import java.util.Scanner;
import java.util.*;
import ship.AircraftCarrier;
import ship.Cruiser;
import ship.Frigate;
import ship.PatrolCraft;
import ship.Submarine;
import world.World;

/**
 * Random guess player (task A).
 * Please implement this class.
 *
 * @author Youhan Xia, Jeffrey Chan
 */
public class RandomGuessPlayer implements Player{
	World world;
	private int AircraftCarrier = 5;
	private int Frigate = 4;
	private int Submarine = 3;
	private int Cruiser = 4;
	private int PatrolCraft = 2;
	private int[] alreadyHit;
	
	
	
    @Override
    public void initialisePlayer(World world) {
        this.world = world;
        
        
        alreadyHit = new int[world.numColumn * world.numRow];
        
        
    	
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
                            break;
                        case "Frigate":
                            Frigate--;
                            if(Frigate < 0) {
                            	Frigate = 0;
                            }
                            break;
                        case "Submarine":
                            Submarine--;
                            if(Submarine < 0) {
                            	Submarine = 0;
                            }
                            break;
                        case "Cruiser":
                            Cruiser--;
                            if(Cruiser < 0) {
                            	Cruiser = 0;
                            }
                            break;
                        case "PatrolCraft":
                            PatrolCraft--;
                            if(PatrolCraft < 0) {
                            	PatrolCraft = 0;
                            }
                            break;
                        
                    }
        			
        		}
    			if(AircraftCarrier == 0) {
    				answer.shipSunk = new AircraftCarrier();
    			}
    			if(Frigate == 0) {
    				answer.shipSunk = new Frigate();
    			}
    			if(Submarine == 0) {
    				answer.shipSunk = new Submarine();
    			}
    			if(Cruiser == 0) {
    				answer.shipSunk = new Cruiser();
    			}
    			if(PatrolCraft == 0) {
    				answer.shipSunk = new PatrolCraft();
    			}
    		}
    		
    	}

        
    	return answer;
    } // end of getAnswer()


    @Override
    public Guess makeGuess() {
    	
        Guess guess = new Guess();
    	
    	Random r = new Random();
    	int nextShot = 0;
    	
    	boolean validHit = false;
    	while(!validHit) {
    		nextShot = r.nextInt(world.numColumn * world.numRow +1);
        	
        	if(nextShot != 0) {
        		nextShot -=1;
        	}
        	if(alreadyHit[nextShot] == 0) {
        		alreadyHit[nextShot] = 1;
        		validHit = true;
        		break;
        	}
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

    	
        
        return guess;
    } // end of makeGuess()


    @Override
    public void update(Guess guess, Answer answer) {
        // To be implemented.
    } // end of update()


    @Override
    public boolean noRemainingShips() {
    	System.out.println(AircraftCarrier + " " + Frigate + " " + Submarine + " " + PatrolCraft + " " + Cruiser);
        if(AircraftCarrier == 0 && Frigate == 0 && Submarine == 0 && PatrolCraft == 0 && Cruiser == 0) {
        	return true;
        }
        return false;
    } // end of noRemainingShips()

} // end of class RandomGuessPlayer
