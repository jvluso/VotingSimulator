package voting.voting;

import voting.population.Population;
import voting.systems.Election;

public class StrategyResults {
	
	private String strategies;
	private Election votingSystem;
	private double popMean = 0;
	private double electedMean = 0;
	private int repititions = 0;
	
	public StrategyResults(Election v, String s){
		votingSystem=v;
		strategies=s;
	}

	public Election getVotingSystem() {
		return votingSystem;
	}

	public void addResult(Population voters, Population candidates){

    	Population congress = votingSystem.vote(voters, candidates);

    	popMean+=voters.meanOpinion(0);
    	electedMean+=congress.meanOpinion(0);
    	repititions++;
    	
	}

	public void reportResults(){

    	System.out.println(votingSystem.name() + strategies);
    	System.out.println("popMean : " + popMean/repititions);
    	System.out.println("electedMean : " + electedMean/repititions);
	}

	public void csvReport(String sep){

    	System.out.print(votingSystem.name()+ strategies + sep);
    	System.out.print(popMean/repititions + sep);
    	System.out.print(electedMean/repititions + "\n");
	}
	public static void csvHead(String sep){

    	System.out.print("." + sep);
    	System.out.print("popMean" + sep);
    	System.out.print("electedMean" + "\n");
	}


}
