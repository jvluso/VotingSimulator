package voting.voting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import voting.strategies.Strategy;

public class Population {
	
	private List<Person> people;
	
	public Population(int size,int issues){
    	people = new ArrayList<Person>(size);
    	Random rand = new Random();
    	for(int i=0;i<size;i++){
    		people.add(i, new Person(issues,rand));
    	}
		
	}

	public Population(int size,int issues,Strategy s1, Strategy s2){
    	people = new ArrayList<Person>(size);
    	Random rand = new Random();
    	for(int i=0;i<size;i++){
    		people.add(i, new Person(issues,rand));
    		if(rand.nextFloat()*2-1<people.get(i).getOpinions()[0]){
        		people.get(i).setStrategy(s1);
    		}else{
        		people.get(i).setStrategy(s2);
    			
    		}
    	}
		
	}
	
	public Population(List<Person> p){
		people = new ArrayList<Person>(p);
	}
	
	public List<Person> getPeople(){
		return new ArrayList<Person>(people);
	}

	public int majorityVote(int i){
		int sup =0;
		for(Person p:people){
			if(p.getOpinions()[i]>0){
				sup ++;
			}
			if(p.getOpinions()[i]<0){
				sup --;
			}
		}
		return Integer.signum(sup);
	}
	

	public float[] majorityVotes(){
		float[] votes = new float[people.get(0).getOpinions().length];
		for(int i=0;i<people.get(0).getOpinions().length;i++){
			votes[i] = majorityVote(i);
		}
		return votes;
	}
	

	public int superMajorityVote(int i){
		int sup =0;
		for(Person p:people){
			if(p.getOpinions()[i]>0){
				sup ++;
			}
			if(p.getOpinions()[i]<0){
				sup --;
			}
		}
		if(sup>people.size()/3){
			return 1;
		}else if(sup<-people.size()/3){
			return -1;
		}else{
			return 0;
		}
	}

	public float meanOpinion(int i){
		float sum = 0;
		for(Person p:people){
			sum+=p.getOpinions()[i];
		}
		sum=sum/people.size();
	
		return sum;
	}

	public float medianOpinion(int i){
		List<Float> sum = new ArrayList<Float>();
		for(Person p:people){
			sum.add(p.getOpinions()[i]);
		}
		Collections.sort(sum);
	
		return sum.get(sum.size()/2);
	}
	
	public float[] meanOpinion(){
		int issues = people.get(0).getOpinions().length;
		float[] sum = new float[issues];
		for(Person p:people){
			for(int i=0;i<issues;i++){
				sum[i]+=p.getOpinions()[i];
			}
		}
		for(int i=0;i<issues;i++){
			sum[i]=sum[i]/people.size();
		}
		return sum;
	}

	public Person nearestDist(float[] p){
		float dist = people.get(0).getOpinions().length*2;
		Person nearest = null;
		
		for(Person c:people){
			if(c.dist(p)<dist){
				nearest=c;
				dist=c.dist(p);
			}
		}
		
		return nearest;
	}

	public float meanDist(float[] o){
		float dist =0;
		for(Person p:people){
			dist+=p.dist(o);
		}
		
		return dist/people.size();
	}
	
	public float meanNearestDist(Population compare){
		float dist =0;
		for(Person p:people){
			dist+=compare.nearestDist(p.getOpinions()).dist(p.getOpinions());
		}
		
		return dist/people.size();
	}
	
}
