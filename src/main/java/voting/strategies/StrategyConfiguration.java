package voting.strategies;

public class StrategyConfiguration {

	ScoreStrategyType scoreType;
	RankedStrategyType rankedType;
	Integer ballotSize;
	
	public StrategyConfiguration(){
		
	}
	
	public void withDefaults(StrategyConfiguration def){
		if(rankedType == null){
			rankedType = def.rankedType;
		}
		if(scoreType == null){
			scoreType = def.scoreType;
		}
		if(ballotSize == null){
			ballotSize = def.ballotSize;
		}
	}
}
