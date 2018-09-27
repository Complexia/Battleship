package player;

import java.util.Scanner;

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
    @Override
    public void initialisePlayer(World world) {
        this.world = world;
    	
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
        

        
        return null;
    } // end of makeGuess()


    @Override
    public void update(Guess guess, Answer answer) {
        // To be implemented.
    } // end of update()


    @Override
    public boolean noRemainingShips() {
        // To be implemented.

        // dummy return
        return true;
    } // end of noRemainingShips()

} // end of class RandomGuessPlayer
