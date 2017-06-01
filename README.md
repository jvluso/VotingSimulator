 # VotingSimulator

 This is a voting simulator to look at the Bayesian Regret for different voting systems. It is built to look at it in several different ways in order to be able to handle multi-winner elections.

## Utility

 This simulator uses issue based utility. Each person is asigned a random opinion on a number of issues, on a unit ball. The regret / negative utility of a candidate to a person is the distance between the two of them. We look at both the bayesian regret based on the mean opinion of the elected body and the nearest candidate from the elected body, which are the same in a single-winner election. We also look at the number of decisions that the person disagrees with. More complicated distributions of opinions on issues may eventually be added.

## Sample Output

 The following uses a population of 105 people, 2 issues, and 10000 repetitions. 



  . | majorityMisses | meanMisses | superMajorityMisses | distBetwenMeans | meanDistToMean | issueUtility | medianDiff | meanNearestDist
  ---  |  ---  |  ---  |  ---  |  ---  |  ---  |  ---  |  ---  |  ---  
  HypotheticalPerfectSingleWinner | 0.2578 | 0.0 | 1.9995 | 0.0 | 0.9928256420254707 | 0.11058811928486684 | 0.15830334772883028 | 0.9928256420254707
  HypotheticalBestRunningCandidate from 20 candidates | 0.2568 | 0.0836 | 1.9995 | 0.9146005707631719 | 1.1973567295670509 | 0.10908673156906153 | 0.7925984482367209 | 1.1973567295670509
  1 winner Reweighted Score using strategy honest from 20 candidates | 0.2891 | 0.2339 | 1.9995 | 0.9220272373393463 | 1.1907808202981949 | 0.10278693249419739 | 0.8095090373171359 | 1.1907808202981949
  1 winner Approval using strategy honest 1 votes from 20 candidates | 0.8846 | 0.9038 | 1.9995 | 0.9890605806771214 | 1.251742444086075 | 0.017406581425652257 | 0.984492175456284 | 1.251742444086075
  1 winner IRV using strategy honest from 20 candidates | 0.5756 | 0.5238 | 1.9995 | 0.9474650027133393 | 1.210617317545414 | 0.07354083260464249 | 0.8862257014936046 | 1.210617317545414
  Jury of 1 from 20 candidates | 0.9159 | 0.9193 | 1.9995 | 0.9920201058918258 | 1.2594738353967667 | 0.013498324306705035 | 0.9921973373568997 | 1.2594738353967667
  HypotheticalDirectDemocracy | 0.0 | 0.2578 | 0.0 | 0.0 | 0.9928256420254707 | 0.10169540945439366 | 0.0 | 0.0
  5 winner Reweighted Score using strategy honest from 20 candidates | 0.4052 | 0.4668 | 0.0982 | 0.13550284503517215 | 0.9942152365446091 | 0.08116659448147111 | 0.5644470883442578 | 0.39121081532686947
  5 winner Approval using strategy honest 5 votes from 20 candidates | 0.6791 | 0.6823 | 1.1567 | 0.5480226053123233 | 1.0737947175979614 | 0.0537088769655762 | 0.792636073640125 | 0.6243538219541311
  1 winner Approval using strategy honest 1 votes from 4 candidates from 5 districts | 0.6855 | 0.6899 | 0.7717 | 0.37602632589436746 | 1.0324357074558734 | 0.05226376954612788 | 0.6553797540152924 | 0.46953012799471616
  Jury of 5 from 20 candidates | 0.857 | 0.8768 | 0.7463 | 0.3901266993580742 | 1.041443212646246 | 0.02246245071090816 | 0.7077874692292668 | 0.47398947010338305
