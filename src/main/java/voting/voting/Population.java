package voting.voting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
	
	private List<Person> people;
	
	public Population(int size,int issues){
    	people = new ArrayList<Person>(size);
    	Random rand = new Random();
    	for(int i=0;i<size;i++){
    		people.add(i, new Person(issues,rand));
    	}
		
	}
	
	public Population(List<Person> p){
		people = new ArrayList<Person>(p);
	}
	
	public List<Person> getPeople(){
		return people;
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
}
