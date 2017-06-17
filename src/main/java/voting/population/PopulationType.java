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
	public void setIssues(int issues) {
		this.issues = issues;
	}
	public int getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
	
	public PopulationType(int i,int p){
		issues=i;
		populationSize=p;
    	rand = new Random();
    	closeness = (float) 0.9;
	}
	public float getCloseness() {
		return closeness;
	}
	public void setCloseness(float closeness) {
		this.closeness = closeness;
	}
}
