package voting.voting;

import java.util.Random;

public class Person {
	
	float[] opinions;
	
	public Person(int issues, Random rand){
		opinions = new float[issues];
		for(int i=0;i<issues;i++){
			opinions[i]=rand.nextFloat() * 2 - 1;
		}
	}
	
	public float[] getOpinions(){
		return opinions;
	}

	public float dist(Person p){
		float d=0;
		for(int i=0;i<opinions.length;i++){
			d+=(opinions[i]-p.getOpinions()[i])*(opinions[i]-p.getOpinions()[i]);
		}
		return (float) Math.sqrt(d);
	}

}
