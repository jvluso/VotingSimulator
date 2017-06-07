package voting.voting;

import java.util.ArrayList;
import java.util.List;

import voting.strategies.ApprovalFiftyPercentScore;
import voting.strategies.HonestScore;
import voting.systems.Election;
import voting.systems.HypotheticalBestRunningCandidate;
import voting.systems.HypotheticalDirectDemocracy;
import voting.systems.Approval;
import voting.systems.HypotheticalPerfectSingleWinner;
import voting.systems.InstantRunoff;
import voting.systems.Jury;
import voting.systems.Score;
import voting.systems.WithCandidates;
import voting.systems.WithDistricts;

public class App 
{
	static int populationSize = 105;
	static int issues = 2;
	static int repititions = 10000;
    public static void main( String[] args ) 
    {
    	
    	List<Election> elections = new ArrayList<Election>();
    	elections.add(new HypotheticalPerfectSingleWinner());
    	elections.add(new WithCandidates(5,new HypotheticalBestRunningCandidate()));
    	elections.add(new WithCandidates(5,new Score(1)));
    	elections.add(new WithCandidates(5,new Approval(1,1)));
    	elections.add(new WithCandidates(5,new InstantRunoff()));
    	elections.add(new WithCandidates(5,new Jury(1)));
    	elections.add(new HypotheticalDirectDemocracy());
    	elections.add(new WithCandidates(20,new Score(5)));
    	elections.add(new WithCandidates(20,new Approval(5,5)));
    	elections.add(new WithDistricts(5,new WithCandidates(4,new Approval(1,1))));
    	elections.add(new WithCandidates(20,new Jury(5)));
    	
    	Election election = new WithCandidates(5,new Score(1));
    	
    	//Simulate.electionTypes(populationSize, issues, repititions,elections);
    	Simulate.strategies(populationSize, issues, repititions,election,new HonestScore(),new ApprovalFiftyPercentScore());
    	
    }
    
    
}
