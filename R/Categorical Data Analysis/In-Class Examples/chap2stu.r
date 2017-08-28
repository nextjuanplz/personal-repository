### Computing marginal, joint, and conditional probabilities

afterlife<-matrix(c(435,147,375,134),nrow=2,byrow=TRUE);afterlife
dimnames(afterlife)<-list(c("Female","Male"),c("Yes","No"))
names(dimnames(afterlife))<-c("Gender","Believer")
tot<-sum(afterlife);tot
afterlife/tot
rowtot<-apply(afterlife,1,sum);rowtot
coltot<-apply(afterlife,2,sum);coltot
rowpct<-sweep(afterlife,1,rowtot,"/");rowpct
round(rowpct,3)
colpct<-sweep(afterlife,2,coltot,"/");colpct
round(colpct,3)


## Difference in proportions, relative risk, odds, odds ratio
phs<-matrix(c(189,10845,104,10933),ncol=2,byrow=T);phs
dimnames(phs)<-list(Group=c("Placebo","Aspirin"),MI=c("Yes","No"))
phs
prop.test(phs)
phs.test<-prop.test(phs)
names(phs.test)
phs.test$estimate
phs.test$conf.int
round(phs.test$conf.int,3)
phs.test$estimate[1]/phs.test$estimate[2] ## Relative Risk

phs.test$estimate
odds<-phs.test$estimate/(1-phs.test$estimate);odds
odds[1]/odds[2]
(phs[1,1]*phs[2,2])/(phs[2,1]*phs[1,2])
theta<-odds[1]/odds[2]
ase<-sqrt(sum(1/phs));ase
logtheta.ci<-log(theta)+c(-1,1)*1.96*ase
logtheta.ci
exp(logtheta.ci)

### Chi-square test, G-square test, Fisher's exact test

gendergap <- matrix(c(279,73,225,165,47,191),byrow=TRUE,nrow=2)
dimnames(gendergap) <- list(Gender=c("Females","Males"),PartyID=c("Democrat","Independent","Republican"))
gendergap
chisq.test(gendergap)
chisq.test(gendergap,simulate.p.value=TRUE,B=10000)
chisq.test(gendergap,simulate.p.value=TRUE,B=10000)

fisher.test(gendergap)

rtot<-apply(gendergap,1,sum);rtot
ctot<-apply(gendergap,2,sum);ctot
n<-sum(gendergap);n
mu<-rtot%*%t(ctot)/n;mu
df<-(nrow(gendergap)-1)*(ncol(gendergap)-1);df
g<-2*sum(gendergap*log(gendergap/mu));g
pval<-1-pchisq(g,df);pval


