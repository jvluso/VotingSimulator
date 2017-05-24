 # VotingSimulator

 This is a voting simulator to look at the Bayesian Regret for different voting systems. It is built to look at it in several different ways in order to be able to handle multi-winner elections.

## Utility

 This simulator uses issue based utility. Each person is asigned a random opinion on a number of issues, on a unit ball. The regret / negative utility of a candidate to a person is the distance between the two of them. We look at both the bayesian regret based on the mean opinion of the elected body and the nearest candidate from the elected body, which are the same in a single-winner election. We also look at the number of decisions that the person disagrees with. More complicated distributions of opinions on issues may eventually be added.

## Sample Output

 The following uses a population of 105 people, 2 issues, and 10000 repetitions. 

. | majorityMisses | meanMisses | superMajorityMisses | distBetwenMeans | meanDistToMean | meanNearestDist | coloquial name | Bayesian regret 1 | Bayesian regret 2
 --- | --- | --- | --- | --- | --- | --- | --- | --- | --- 
HypotheticalPerfectSingleWinner | 0.257 | 0 | 1.9993 | 0 | 0.992688676 | 0.992688676 | Hypothetical Perfect Single Winner | -3.800978238 | -3.800978238
HypotheticalBestRunningCandidate from 5 candidates | 0.3806 | 0.2842 | 1.9993 | 0.9267036784 | 1.204961237 | 1.204961237 | Hypothetical Best Running Candidate  | 0 | 0
1 winner Reweighted Score using strategy honest from 5 candidates | 0.4498 | 0.4096 | 1.9993 | 0.9343424588 | 1.20908842 | 1.20908842 | Score Voting | 0.07390184166 | 0.07390184166
1 winner Approval using strategy honest 1 votes from 5 candidates | 0.7588 | 0.767 | 1.9995 | 0.9714247821 | 1.240338955 | 1.240338955 | Plurality Voting | 0.6334777016 | 0.6334777016
Jury of 1 from 5 candidates | 0.9321 | 0.9355 | 1.9998 | 0.9936711155 | 1.260808061 | 1.260808061 | Random Winner | 1 | 1
HypotheticalDirectDemocracy | 0 | 0.257 | 0 | 0 | 0.992688676 | 0 | Direct Democracy | -3.800978238 | -21.57618212
5 winner Reweighted Score using strategy honest from 20 candidates | 0.4061 | 0.4695 | 0.1039 | 0.1356509868 | 0.9940029487 | 0.3888564752 | RRV | -3.777444711 | -14.61327089
5 winner Approval using strategy honest 5 votes from 20 candidates | 0.6762 | 0.6746 | 1.1693 | 0.5530348883 | 1.074347219 | 0.6260752375 | At-Large Elections | -2.33879046 | -10.36560295
1 winner Approval using strategy honest 1 votes from 4 candidates from 5 districts | 0.6904 | 0.685 | 0.7836 | 0.3786388537 | 1.032724956 | 0.4707319458 | District Plurality | -3.084083749 | -13.14719878
Jury of 5 from 20 candidates | 0.8506 | 0.8662 | 0.7501 | 0.3889514866 | 1.041020213 | 0.4738024138 | Random Jury | -2.935547867 | -13.09221861
