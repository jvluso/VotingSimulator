# VotingSimulator

 This is a voting simulator to look at the Bayesian Regret for different voting methods. It is built to look at it in several different ways in order to be able to handle multi-winner elections.

## Utility

 This simulator uses issue based utility. Each person is asigned a random opinion on a number of issues, on a unit ball. The regret / negative utility of a candidate to a person is the distance between the two of them. We look a large number of metrics to determine which voting methods are better or worse.

The following metrics are tracked for each output:

### Majority Misses

 For each issue, this compares the majority of the voters to the majority of the council, and counts how often the results are different. This represents the number of times that a vote done by the council will turn out differently from a vote done by the population. A smaller number is better.

### Mean Misses

 For each issue, this compares the mean of the voters to the mean of the council, and counts how often the results are different. The mean represents what a group is likely to comprimize to do. If the majority who would vote against an issue care about it enough less than the minority who would vote for it, it is not unreasonable that they would be likely to compromize on other issues to get the votes for the issue. A smaller number is better.

### Super Majority Misses

 This is just like Majority Misses, but for issues that have a 2/3rds majority. In general, councils that do better in Majority Misses, but worse in Super Majority Misses do not represent minority voters well, and if certain actions like the ability to change the constitution need to be protected by requiring a super majority, voting systems that do poorly here will not offer those protections. A smaller number is better.

### Distance Between Means

 This is the distance in issue space between the mean opinion of the voters and the mean opinion of the council. The closer the means are, the better the council represents the population. A lower number is better.

### Mean Distance to the Mean

 This is the mean distance from each individual voter to the mean oppinion of the council. Using the distance between the voter and the candidates, this is one way of looking at the Bayesian Regret. A lower number is better.

### Issue Utility

 Each voter is considered to get an amount of utility on each issue based on their oppinion on the issue. If the winning council votes on each issue, this is the average amount of utility gained/lost by all the voters. A higher number is better.

### Median Difference

 This is just like the Distance between Means, but using the median voter on each issue rather than the mean. It also uses the median winning candidate instead of the mean. A lower number is better.

### Mean Nearest Distance

 Like the Mean Distance to the Mean, this is a way of looking at the Bayesian Regret, using distances to measure regret. However, unlike the Mean Distance to the Mean, this looks at the distance to the nearest winning councilmember rather than the mean oppinion of the council. This should represent how closely represented the average voter is. A lower number is better.


## Inputs

There are a few variables used to generate the output. Changing the variables can change the output.

### Population Size

This is the number of voters in the population.

### Issues

This is the number of issues that the voters care about. It should be at least two.

### Clustering

If this is not 0, it will cause the population to cluster together. This different distribution will result in different distributions. The closer it is to 1, the more clustering will occur. It cannot be 1 or higher. This might or might not be a good way to represent that people who interact with each other can influence each other, and end up with similar oppinions.

### Repetitions

This is how many times the simulation will run. If it doesn't run ehough, stastical anomolies can impact the result. However it does make it take longer to run more.


## Sample Output

 The following uses a population of 105 people, 2 issues, no clustering, and 10000 repetitions. 



. | majorityMisses | meanMisses | superMajorityMisses | distBetwenMeans | meanDistToMean | issueUtility | medianDiff | meanNearestDist
 ---  |  ---  |  ---  |  ---  |  ---  |  ---  |  ---  |  ---  |  --- 
HypotheticalDirectDemocracy | 0.0 | 0.2619 | 0.0 | 0.0 | 0.9964248670458794 | 0.09735401454732055 | 0.0 | 0.0
HypotheticalPerfectSingleWinner | 0.2619 | 0.0 | 3.0 | 0.0 | 0.9964248670458794 | 0.10117888669758104 | 0.06385825772869644 | 0.9964248670458794
HypotheticalBestRunningCandidate from 20 candidates | 0.4106 | 0.3197 | 3.0 | 0.9380656727578127 | 1.2809621483683586 | 0.09426070621041581 | 0.8896993644770255 | 1.2809621483683586
1 winner Sequential Score using strategy Honest Score from 20 candidates | 0.504 | 0.4605 | 3.0 | 0.9423936196073551 | 1.2817343567609787 | 0.08931136899880367 | 0.8973600646054787 | 1.2817343567609787
1 winner STV using strategy Honest List from 20 candidates | 0.8429 | 0.8288 | 3.0 | 0.9605298937754154 | 1.294580454993248 | 0.06419059378530365 | 0.9316027673393086 | 1.294580454993248
Plurality using strategy Honest List from 20 candidates | 1.3149 | 1.3256 | 3.0 | 0.9901088430259988 | 1.320308627998829 | 0.018171509072708432 | 0.9842328885972673 | 1.320308627998829
Jury of 1 from 20 candidates | 1.4228 | 1.4251 | 3.0 | 0.996983514211096 | 1.3281755145907401 | 0.007461371410469292 | 0.9973684619768843 | 1.3281755145907401
5 winner Sequential Score using strategy Honest Score from 20 candidates | 0.8188 | 0.8259 | 0.342 | 0.16058953896015418 | 1.0034566716074944 | 0.06457793797044432 | 0.4939622733249753 | 0.6713871244490147
5 winner STV using strategy Honest List from 20 candidates | 0.9908 | 1.0345 | 0.1782 | 0.14012827164725486 | 1.0029452415764333 | 0.04765630923322169 | 0.4455776657442424 | 0.6036514595210553
5 winner At Large using strategy ApprovalFiftyPercentScore from 20 candidates | 0.6518 | 0.6069 | 1.9019 | 0.6237528719981961 | 1.1265424895524978 | 0.08049156831394066 | 0.7391555922904786 | 0.8511210862398148
Jury of 5 from 20 candidates | 1.3475 | 1.3612 | 1.1497 | 0.4151812811722739 | 1.0628834990799427 | 0.015420344088407001 | 0.6551210885636344 | 0.729687903881073

