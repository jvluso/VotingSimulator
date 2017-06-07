package voting.strategies;

public class Ballot<T> {
	T vote;

	public Ballot(T v){
		vote = v;
	}
	
	public T getVote(){
		return vote;
	}

}
