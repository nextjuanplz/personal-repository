#------Problem 1: 2014 World Cup Soccer goals scored by Colombia
###Input values
k<-c(0,1,2,3,4)				###k: number of goals in a match
obs<-c(0,0,1,1,1)				###obs: observed goal frequency
###Put k and obs in dat
dat<-cbind(k,obs);dat

###Calculate E(x)
sumgr<-sum(k * obs);sumgr		###sumgr: S(k * obs)
n<-sum(obs);n				###n: S(obs)
mn<-sumgr/n;mn				###mn: E(x) = (S(k * obs))/n

###Calculate Poisson probabilities
px<-dpois(k,mn);				###px: X~Pois(k, mn)
###Probabilities greater than 4
pgreater<-(1 - sum(px));		###pgreater: p(X > 4)
px<-c(px, pgreater)
k<-c(k, ">4");				###Modify k for k > 4
###View on a dataframe
data.frame(k,px)

###Compute expected frequencies
expec<-(n*px);				###E(f) = np(X = k)
###View on a dataframe
data.frame(k,expec)

###View everything on a dataframe:	| k | p(x = k) | obs | E() |
obs<-c(obs, 0)				###Modify obs for k > 4
data.frame(k,px,obs,expec)

###Readjust k
k<-c(0,1,2,3,4,5);			###5 used as placeholder for k > 4
###Put data in datamatrix
datamatrix = matrix(c(k, (obs)), nrow = 6, ncol = 2);

###Chi-square test
chisq.test(obs, p = px)
chisq.test(obs, p = px,simulate.p.value = TRUE)

###Fisher's exact test
fisher.test(datamatrix, alternative = "two.sided")
table<-array(data = c(k,obs), dim = c (6,2), dimnames = NULL);table