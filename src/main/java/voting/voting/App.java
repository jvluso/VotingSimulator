package voting.voting;

import java.util.ArrayList;
import java.util.List;

import voting.population.PopulationType;
import voting.systems.Election;
import voting.systems.HypotheticalBestRunningCandidate;
import voting.systems.HypotheticalDirectDemocracy;
import voting.systems.HypotheticalPerfectSingleWinner;
import voting.systems.InstantRunoff;
import voting.systems.Jury;
import voting.systems.WithCandidates;
import voting.systems.ranked.SingleTransferable;
import voting.systems.score.AtLarge;
import voting.systems.score.NonSequentialScore;
import voting.systems.score.SequentialScore;

public class App 
{
	static int populationSize = 105;
	static int issues = 3;
	static int repititions = 1000;
	static int winners = 3;
	static int candidates = 10;
	static float cluster = (float) 0.1;
    public static void main( String[] args ) 
    {
    	
    	List<Election> elections = new ArrayList<Election>();
    	elections.add(new HypotheticalDirectDemocracy());
    	elections.add(new HypotheticalPerfectSingleWinner());
    	elections.add(new WithCandidates(candidates,new HypotheticalBestRunningCandidate()));
    	elections.add(new WithCandidates(candidates,new InstantRunoff()));
    	elections.add(new WithCandidates(candidates,new Jury(1)));
    	elections.add(new WithCandidates(candidates,new NonSequentialScore(winners)));
    	elections.add(new WithCandidates(candidates,new SequentialScore(winners)));
    	elections.add(new WithCandidates(candidates,new SingleTransferable(winners)));
    	elections.add(new WithCandidates(candidates,new AtLarge(winners)));
    	elections.add(new WithCandidates(candidates,new Jury(winners)));
    	
    	PopulationType type = new PopulationType(issues,populationSize,cluster);
    	
    	Election election1 = new WithCandidates(candidates,new SequentialScore(winners));
    	Election election2 = new WithCandidates(candidates,new AtLarge(winners));

    	Simulate.electionTypes(type, repititions,elections);
    	//Simulate.comparison(type, repititions,election1,election2);
    	//Simulate.strategies(populationSize, issues, repititions,election,new HonestScore(),new ApprovalFiftyPercentScore());
    	
    	
    }
    

    
}
