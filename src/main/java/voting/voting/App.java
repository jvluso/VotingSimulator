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
import voting.systems.ranked.Plurality;
import voting.systems.ranked.SingleTransferable;
import voting.systems.score.AtLarge;
import voting.systems.score.NonSequentialScore;
import voting.systems.score.SequentialScore;

public class App 
{
	static int populationSize = 1005;
	static int issues = 3;
	static int repititions = 10000;
	static int winners = 5;
	static int candidates = 20;
	static float cluster = (float) 0.5;
    public static void main( String[] args ) 
    {
    	
    	List<Election> elections = new ArrayList<Election>();
    	elections.add(new HypotheticalDirectDemocracy());
    	elections.add(new HypotheticalPerfectSingleWinner());
    	elections.add(new WithCandidates(candidates,new HypotheticalBestRunningCandidate()));
    	elections.add(new WithCandidates(candidates,new SequentialScore(1)));
    	elections.add(new WithCandidates(candidates,new SingleTransferable(1)));
    	elections.add(new WithCandidates(candidates,new Plurality()));
    	elections.add(new WithCandidates(candidates,new Jury(1)));
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
