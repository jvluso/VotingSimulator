package voting.voting;

import voting.systems.Election;

public class ElectionsResults {
	
	private Election votingSystem;
	private double majorityMisses = 0;
	private double meanMisses = 0;
	private double superMajorityMisses = 0;
	private double distBetwenMeans = 0;
	private double meanDistToMean = 0;
	private double meanNearestDist = 0;
	private double issueUtility = 0;
	private double medianDiff = 0;
	private double repititions = 0;
	
	public ElectionsResults(Election v){
		votingSystem=v;
	}

	public Election getVotingSystem() {
		return votingSystem;
	}

	public double getMajorityMisses() {
		return majorityMisses;
	}
	
	public double getMeanMisses() {
		return meanMisses;
	}

	public double getSuperMajorityMisses() {
		return superMajorityMisses;
	}

	public double getDistBetwenMeans() {
		return distBetwenMeans;
	}

	public double getMeanDistToMean() {
		return meanDistToMean;
	}

	public double getMeanNearestDist() {
		return meanNearestDist;
	}
	
	public void addResult(Population voters, Population candidates){

    	Population congress = votingSystem.vote(voters, candidates);

    	float dist = 0;
    	float medDist = 0;
    	for(int i=0;i<voters.getPeople().get(0).getOpinions().length;i++){
    		if(voters.majorityVote(i) != congress.majorityVote(i)){
    			majorityMisses++;
    		}
    		if((int) Math.signum(voters.meanOpinion(i)) != congress.majorityVote(i)){
    			meanMisses++;
    		}
    		if(voters.superMajorityVote(i) != congress.superMajorityVote(i)){
    			superMajorityMisses++;
    		}
    		dist+=(voters.meanOpinion(i)-congress.meanOpinion(i))*(voters.meanOpinion(i)-congress.meanOpinion(i));
    		medDist+=(voters.medianOpinion(i)-congress.medianOpinion(i))*(voters.medianOpinion(i)-congress.medianOpinion(i));
    	}
    	issueUtility+=dot(voters.meanOpinion(),congress.majorityVotes());
    	distBetwenMeans+=Math.sqrt(dist);
    	medianDiff+=Math.sqrt(medDist);
    	meanDistToMean+=voters.meanDist(congress.meanOpinion());
    	meanNearestDist+=voters.meanNearestDist(congress);
    	repititions++;
	}

	public void reportResults(){

    	System.out.println(votingSystem.name());
    	System.out.println("majorityMisses : " + majorityMisses/repititions);
    	System.out.println("meanMisses : " + meanMisses/repititions);
    	System.out.println("superMajorityMisses : " + superMajorityMisses/repititions);
    	System.out.println("distBetwenMeans : " + distBetwenMeans/repititions);
    	System.out.println("meanDistToMean : " + meanDistToMean/repititions);
    	System.out.println("issueUtility : " + issueUtility/repititions);
    	System.out.println("medianDiff : " + medianDiff/repititions);
    	System.out.println("meanNearestDist : " + meanNearestDist/repititions);
	}

	public void csvReport(String sep){

    	System.out.print(votingSystem.name() + sep);
    	System.out.print(majorityMisses/repititions + sep);
    	System.out.print(meanMisses/repititions + sep);
    	System.out.print(superMajorityMisses/repititions + sep);
    	System.out.print(distBetwenMeans/repititions + sep);
    	System.out.print(meanDistToMean/repititions + sep);
    	System.out.print(issueUtility/repititions + sep);
    	System.out.print(medianDiff/repititions + sep);
    	System.out.print(meanNearestDist/repititions + "\n");
	}
	public static void csvHead(String sep){

    	System.out.print("." + sep);
    	System.out.print("majorityMisses" + sep);
    	System.out.print("meanMisses" + sep);
    	System.out.print("superMajorityMisses" + sep);
    	System.out.print("distBetwenMeans" + sep);
    	System.out.print("meanDistToMean" + sep);
    	System.out.print("issueUtility" + sep);
    	System.out.print("medianDiff" + sep);
    	System.out.print("meanNearestDist" + "\n");
	}
	
	private float dot(float[] a,float[] b){
		float s =0;
		
		for(int i=0;i<a.length;i++){
			s+=a[i]*b[i];
		}
		
		return s;
	}

}
