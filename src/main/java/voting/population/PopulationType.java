package voting.population;

import java.util.Random;

public class PopulationType {

	private int issues;
	private int populationSize;
	private Random rand;
	private float closeness;
	
	public int getIssues() {
		return issues;
	}
	public Random getRand() {
		return rand;
	}
	public int getPopulationSize() {
		return populationSize;
	}
	public PopulationType(int i,int p, float c){
		issues=i;
		populationSize=p;
    	rand = new Random();
    	closeness = c;
	}
	public PopulationType(){
		issues=3;
		populationSize=105;
    	rand = new Random();
    	closeness = 0;
	}
	public float getCloseness() {
		return closeness;
	}
}
