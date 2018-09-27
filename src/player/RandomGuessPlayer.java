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
	
	int shotsCounter = 0;
	private int[][] allShots;
	
    @Override
    public void initialisePlayer(World world) {
        this.world = world;
        allShots = new int[world.numColumn][world.numRow];
        for(int i=0;i<allShots.length;i++) {
        	for(int j=0;j<allShots[i].length;j++) {
        		
        	}
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
                            break;
                        case "Frigate":
                            Frigate--;
                            break;
                        case "Submarine":
                            Submarine--;
                            break;
                        case "Cruiser":
                            Cruiser--;
                            break;
                        case "PatrolCraft":
                            PatrolCraft--;
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
    	shotsCounter = 0;
        Guess guess = new Guess();
    	for(int i=0;i<allShots.length;i++) {
    		for(int j=0;j<allShots[i].length;j++) {
    			if(allShots[i][j] == 0) {
    				shotsCounter++;
    			}
    			
    		}
    	}
    	Random r = new Random();
    	int nextShot = 0;
    	
    	nextShot = r.nextInt(shotsCounter + 1);
    	nextShot -=1;
    	System.out.println(nextShot);
    	if(nextShot < world.numColumn) {
    		guess.column = world.numColumn - nextShot;
    		guess.row = 1;
    	}
    	else {
    		int division = nextShot/world.numColumn;
    		int modulo = nextShot%world.numColumn;
    		if(modulo == 0) {
    			guess.column = world.numColumn;
    			guess.row = division;
    		}
    		else {
    			guess.column = modulo;
    			guess.row = division + 1;
    		}
    	}
    	boolean validHit = false;
    	while(!validHit) {
    		if(allShots[guess.column-1][guess.row-1] == 1) {
    			if(guess.column < world.numColumn) {
    				guess.column += 1;
    			}
    			else {
    				guess.column = 1;
    				if(guess.row < world.numRow) {
    					guess.row +=1;
    				}
    				else {
    					guess.column = 1;
    					guess.row = 1;
    				}
    				
    			}
    		}
    		else {
    			validHit = true;
    			break;
    		}
    	}
    	allShots[guess.column-1][guess.row-1] = 1;
        
        return guess;
    } // end of makeGuess()


    @Override
    public void update(Guess guess, Answer answer) {
        // To be implemented.
    } // end of update()


    @Override
    public boolean noRemainingShips() {
        // To be implemented.

        // dummy return
        return false;
    } // end of noRemainingShips()

} // end of class RandomGuessPlayer
