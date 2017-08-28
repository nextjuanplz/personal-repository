#----------------------------1.1.3 Poisson Distribution
###Input values
k<-c(0,1,2,3,4,5,6,7)								###Number of goals (k)
obs<-c(20,29,16,3,1,0,1,0)							###Observed frequency of goals

###Put k and obs in a matrix
dat<-cbind(k, obs);dat

###Calculate E(x)
sumgr<-sum(k * obs);sumgr							###Finds sum(k * obs)
mn<-sumgr/sum(obs);mn								###E(x) = kp(X = k)

###Calculate Poisson probabilities
px<-dpois(k,mn);px									###X~Pois(X, mn)

###Compute expected frequencies
n<-sum(obs);n										###n = sum(obs)
expec<-n*px;expec									###E(f) = np(X = k)

###View on a data.frame
data.frame(x,px,obs,expec)

###Exact Test
nbin<-20
pi<-0.05
pval<-1-dbinom(0,nbin,pi)-dbinom(1,nbin,pi);pval	###p = p(X >= 2)

#----------------------------Asking for help about a function

#? function

#----------------------------1.4 Inferences About Multinomial Proportions
###Input values
xm<-c(190, 198, 187, 225)							###Observed frequencies
nm<-sum(xm);nm										###n = sum(xm)

###Find chi^2 observed
pi0<-c(1/4, 1/4, 1/4, 1/4)							###H0: p(A) = p(B) = p(C) = p(D) = pi0 = 1/4
exp<-nm * pi0;exp									###Expected frequencies = npi0
dif<-((xm - exp)^2)/exp;dif							###differences = (x-npi0)^2 / npi0
xsquare<-sum(dif);xsquare							###chi^2 observed = sum(differences)	

###Hypothesis test
crit<-qchisq(0.05, 3, lower.tail=F);crit			###chi^2 critical(alpha = .05, only upper-tail)
pval(pchisq(xsquare, 3, lower.tail=F));pval			###p = p(chi^2 > observed)

#rbind(xm, exp)
#dif

#---------------------------- Marginal, joint, and conditional probabilities

###Input data
afterlife<-matrix(c(435, 147, 375, 134), nrow = 2, byrow = TRUE);afterlife

###Create 2 x 2 matrix (Gender x Belief in afterlife)
dimnames(afterlife)<-list(c("Female", "Male"), c("Yes", "No"))	###Labels for i and j
names(dimnames(afterlife))<-c("Gender", "Believer")				###Labels for X and Y

###Marginal probabilities
tot<-sum(afterlife);tot
afterlife
afterlife/tot

###Joint probabilities
rowtot<-apply(afterlife, 1, sum);rowtot						###Applies sum() across rows: {1 = rows}
coltot<-apply(afterlife, 2, sum);coltot						###Applies sum() down columns: {2 = columns}

###Conditional Probabilities
###p(Y|X)
rowpct<-sweep(afterlife, 1, rowtot, "/");rowpct				###Divides each nij by n+i
round(rowpct, 3)											###Round to 3 decimal places

###p(X|Y)
colpct<-sweep(afterlife, 2, coltot, "/");colpt
round(colpct, 3)											###Round to 3 decimal places

#---------------------------- Relative risk

###Input values
phs<-matrix(c(189, 10845, 104, 10933), ncol = 2, byrow = T);phs
dimnames(phs)<-list(Group = c("Placebo", "Aspirin"), MI = c("Yes", "No"));dimnames

###2-sample test for equality of proportions (pi1 = pi2)
prop.test(phs)		
phs.test<-prop.test(phs)
#names(phs.test)
phs.test$estimate
phs.test$conf.int
$round(phs.test$conf.int, 3)

###Calculate relative risk
phs.test$estimate[1]/phs.test$estimate[2]
odds<-phs.test$estimate/(1-phs.test$estimate);odds

odds[1]/odds[2]									###Odds ratio
(phs[1, 1]* phs[2, 2])/(phs[2, 1] * phs[1, 2])
theta<-odds[1]/odds[2]								###RR estimate
ase<-sqrt(sum(1/phs));ase							###ASE = sum(1/n)

###Confidence interval for ln(theta)
logtheta.ci<-log(theta) + c(-1, 1) * 1.96 * ase
logtheta.ci

###Exponentiate to get theta
exp(logtheta.ci)									###RR = [e^ln(theta_lower), e^ln(theta_higher)]

#----------------------------chi^2 test, G^2 test, Fisher's exact test
###Input values
gendergap<-matrix(c(279, 73, 225, 165, 47, 191, byrow = TRUE, nrow = 2)
dimnames(gendergap)<-list(Gender = c("Females", "Males", PartyID = c("Democrat", "Independent", "Republican"))
gendergap

###chi^2 test
chisq.test(gendergap)
chisq.test(gendergap, simulate.p.value = TRUE, B = 10000)
chisq.test(gendergap, simulate.p.value = TRUE, B = 10000)

###Fisher's exact test
fisher.test(gendergap)

###G^2 test
rtot<-apply(gendergap, 1, sum);rtot
ctot<-apply(gendergap,2, sum);ctot

n<-sum(gendergap)n

mu<-rtot%*%t(ctot)/n;mu

df<-(nrow(gendergap) - 1) * (ncol(gendergap) - 1);df
g<-2*sum(gendergap * log(gendergap/mu));g
pval<-1-pchisq(g, df);pval