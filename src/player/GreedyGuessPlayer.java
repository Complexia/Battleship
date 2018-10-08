package player;

import java.util.Random;
import java.util.Scanner;

import ship.AircraftCarrier;
import ship.Cruiser;
import ship.Frigate;
import ship.PatrolCraft;
import ship.Submarine;
import world.World;

/**
 * Greedy guess player (task B).
 * Please implement this class.
 *
 * @author Youhan Xia, Jeffrey Chan
 */
public class GreedyGuessPlayer  implements Player{
	World world;
	private int AircraftCarrier = 6;
	private int Frigate = 4;
	private int Submarine = 3;
	private int Cruiser = 4;
	private int PatrolCraft = 2;
	private int[] alreadyHit;
	private int[] alreadyTar;
	private cs[] seek;
	private boolean hunt;
	private boolean target;
	int nextShot = 0;
	cs originalHit;
	cs currentHit;
	cs nextGuess;
	int option = 0;
	int checker = 0;
	
	
    @Override
    public void initialisePlayer(World world) {
    	this.world = world;
    	hunt = true;
    	target = false;
    	alreadyHit = new int[world.numColumn * world.numRow];
    	alreadyTar = new int[world.numColumn * world.numRow];
    	int skip = 0;
    	int j = 0;
    	for(int i=0;i<alreadyHit.length;i++) {
    		
    		if(skip == 0) {
    			alreadyHit[i] = 1;
    			skip = 1;
    		}
    		else {
    			skip = 0;
    		}
    		j++;
    		if(j == world.numColumn - 1) {
    			i++;
    			j = 0;
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
//    			if(AircraftCarrier == 0) {
//    				answer.shipSunk = new AircraftCarrier();
//    			}
//    			if(Frigate == 0) {
//    				answer.shipSunk = new Frigate();
//    			}
//    			if(Submarine == 0) {
//    				answer.shipSunk = new Submarine();
//    			}
//    			if(Cruiser == 0) {
//    				answer.shipSunk = new Cruiser();
//    			} 	
//    			if(PatrolCraft == 0) {
//    				answer.shipSunk = new PatrolCraft();
//    			}
    		}
    		
    	}

        
    	return answer;

        
    } // end of getAnswer()


    @Override
    public Guess makeGuess() {
    	Guess guess = new Guess();
    	
    	Random r = new Random();
    	nextShot = 0;
    	
    	boolean validHit = false;
    	if(hunt) {
    		
    		while(!validHit) {
        		nextShot = r.nextInt(world.numColumn * world.numRow);
            	
            	
            	if(alreadyHit[nextShot] == 0) {
            		alreadyHit[nextShot] = 1;
            		//System.out.println("Here, " + nextShot);
            		
            		alreadyTar[nextShot] = 1;
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
        	
    		
    	}
    	else if(target) {
    		
    		validHit = false;
    		
    		while(!validHit) {
    			
    			

        		int gridShot = nextGuess.row * world.numColumn + nextGuess.column;
        		System.out.println("GRID " + gridShot);
//        		for(int j=0;j<alreadyTar.length;j++) {
//        			System.out.println("Xz " + j + " " +  alreadyTar[j]);
//        		}
            	if(alreadyTar[gridShot] == 0) {
            		alreadyTar[gridShot] = 1;
            		alreadyHit[gridShot] = 1;
            		validHit = true;
            		break;
            	}
            	else {
            		Guess guess1 = new Guess();
            		guess1.column = nextGuess.column;
            		guess1.row = nextGuess.row;
            		Answer answer1 = new Answer();
            		answer1.isHit = false;
            		
            		update(guess1,answer1);
            	}
            	
            	
            	
        	}
    		guess.column = nextGuess.column;
    		guess.row = nextGuess.row;
    		
    		
    	}
    	

    	
        
        return guess;
    } // end of makeGuess()


    @Override
    public void update(Guess guess, Answer answer) {
    	
    	if(hunt) {
    		if(answer.isHit) {
    	    	
        		
            	hunt = false;
            	target = true;
            	originalHit = new cs(guess.column,guess.row);
            	currentHit = new cs(guess.column,guess.row);
            	option = 0;
            	
            	
        	
            }
    	}
        
    	if(target) {
    		if(answer.isHit) {
    			
    			if(answer.shipSunk != null) {
    				System.out.println("YESYESYES");
    				target = false;
    				hunt = true;
    				option = 0;
    			}
    			if(option == 0) {
    				if(currentHit.row + 1 >= world.numRow) {
    					
    				}
    				else {
    					nextGuess = new cs(currentHit.column,currentHit.row+1);
        				currentHit = nextGuess;
    				}
    				
    			}
    			if(option == 1) {
    				if(currentHit.row - 1 < 0) {
    					
    				}
    				else {
    					nextGuess = new cs(currentHit.column,currentHit.row-1);
        				currentHit = nextGuess;
    				}
    				
    			}
    			if(option == 2) {
    				if(currentHit.column + 1 >= world.numColumn) {
    					
    				}
    				else {
    					nextGuess = new cs(currentHit.column+1,currentHit.row);
        				currentHit = nextGuess;
    				}
    				
    			}
    			if(option == 3) {
    				if(currentHit.column - 1 < 0) {
    					option = 0;
    					nextGuess = new cs(currentHit.column,currentHit.row+1);
        				currentHit = nextGuess;
    				}
    				else {
    					option = 0;
    					nextGuess = new cs(currentHit.column-1,currentHit.row);
        				currentHit= nextGuess;
    				}
    				
    			}
            	
    			
        		
    			
    		}
    		else {
    			
    			currentHit = originalHit;
    			option++;
    			
    			if(option == 0) {
    				if(originalHit.row + 1 >= world.numRow) {
    					option++;
    				}
    				else {
    					nextGuess = new cs(originalHit.column,originalHit.row+1);
        				currentHit = nextGuess;
    				}
    				
    			}
    			if(option == 1) {
    				if(originalHit.row - 1 < 0) {
    					option++;
    				}
    				else {
    					nextGuess = new cs(originalHit.column,originalHit.row-1);
        				currentHit = nextGuess;
    				}
    				
    			}
    			if(option == 2) {
    				if(originalHit.column + 1 >= world.numColumn) {
    					option++;
    				}
    				else {
    					nextGuess = new cs(originalHit.column+1,originalHit.row);
        				currentHit = nextGuess;
    				}
    				
    			}
    			if(option == 3) {
    				System.out.println("Here");
    				if(originalHit.column - 1 < 0) {
    					
    					nextGuess = new cs(currentHit.column,currentHit.row+1);
        				currentHit = nextGuess;
    				}
    				else {
    					nextGuess = new cs(originalHit.column-1,originalHit.row);
        				currentHit = nextGuess;
    				}
    				
    			}
    			
    			
    			if(option >= 4) {
    				
    				
    				
    				
    				if(checker == 0) {
    					checker = 1;
    				}
    				else {
    					if(nextGuess.column > originalHit.column && originalHit.column -1 >= 0) {
        					originalHit.column -=1;
        					nextGuess = originalHit;
        				}
        				else if(nextGuess.column < originalHit.column && originalHit.column +1 < world.numColumn) {
        					originalHit.column +=1;
        					nextGuess = originalHit;
        				}
    					checker = 0;
    				}
    				
    				
    				option = 0;
    				
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
} // end of class GreedyGuessPlayer
