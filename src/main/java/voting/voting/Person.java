package voting.voting;

import java.util.Map;
import java.util.Random;

import voting.strategies.Ballot;
import voting.strategies.Strategy;

public class Person {
	
	private float[] opinions;
	private Strategy strategy;

	public Person(int issues, Random rand){
		opinions = new float[issues];
		for(int i=0;i<issues;i++){
			opinions[i]=rand.nextFloat() * 2 - 1;
		}
		
    	float mean = this.dist(new float[issues]);
		for(int i=0;i<issues;i++){
			opinions[i]=opinions[i]/mean;
		}
	}
	
	protected void setStrategy(Strategy s){
		strategy=s;
	}
	
	private Person(float[] o,Strategy s){
		opinions=o;
		strategy=s;
	}
	
	public static Person HypotheticalPerson(float[] o){
		return new Person(o,null);
	}
	
	public float[] getOpinions(){
		return opinions;
	}

	public float dist(float[] p){
		float d=0;
		for(int i=0;i<opinions.length;i++){
			d+=(opinions[i]-p[i])*(opinions[i]-p[i]);
		}
		return (float) Math.sqrt(d);
	}
	
	
	public <T> Ballot<T> vote(Population candidates, Strategy<T> def){
		if(strategy.getClass()==null){
			return def.vote(this, candidates);
		}else{
			return strategy.vote(this, candidates);
		}
	}
	
	

}
