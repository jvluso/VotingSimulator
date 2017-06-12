package voting.voting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import voting.systems.Election;
import voting.systems.HypotheticalBestRunningCandidate;
import voting.systems.HypotheticalDirectDemocracy;
import voting.systems.HypotheticalPerfectSingleWinner;
import voting.systems.InstantRunoff;
import voting.systems.Jury;
import voting.systems.WithCandidates;
import voting.systems.score.AtLarge;
import voting.systems.score.NonSequentialScore;
import voting.systems.score.SequentialScore;

public class App 
{
	static int populationSize = 105;
	static int issues = 3;
	static int repititions = 1000;
    public static void main( String[] args ) 
    {
    	
    	List<Election> elections = new ArrayList<Election>();
    	elections.add(new HypotheticalPerfectSingleWinner());
    	elections.add(new WithCandidates(20,new HypotheticalBestRunningCandidate()));
    	elections.add(new WithCandidates(20,new Jury(1)));
    	elections.add(new HypotheticalDirectDemocracy());
    	elections.add(new WithCandidates(20,new SequentialScore(5)));
    	elections.add(new WithCandidates(20,new NonSequentialScore(5)));
    	elections.add(new WithCandidates(20,new AtLarge(5)));
    	elections.add(new WithCandidates(20,new Jury(5)));
    	
    	//Election election = new WithCandidates(5,new SequentialScore(1));
    	
    	Simulate.electionTypes(populationSize, issues, repititions,elections);
    	//Simulate.strategies(populationSize, issues, repititions,election,new HonestScore(),new ApprovalFiftyPercentScore());
    	
    	
    }
    

    
}
