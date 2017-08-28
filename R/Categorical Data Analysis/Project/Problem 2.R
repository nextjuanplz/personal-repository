#------Problem 2: Heart disease in males according to SES and smoking habits
###Input values
table<-array(data = c(51,92,68,22,21,9,43,28,22), dim = c(3,3))
dimnames(table)<-list(Smoking = c("current","former","never"), SES = c("high","medium","low"));table

###Degrees of freedom
v<-(nrow(table)-1)*(ncol(table)-1);v			###df

###Totals
rowtot<-apply(table,1,sum);rowtot				###ni+
coltot<-apply(table,2,sum);coltot				###n+j
n<-sum(rowtot);n							###n

###Expected frequencies
muhat<-rowtot%*%t(coltot)/n					###E(f)
dimnames(muhat)<-list(Smoking = c("current","former","never"), SES = c("high","medium","low"));muhat

###Chi-square test
chisq.test(table)

###G-square test
g<-2*sum(table*log(table/muhat));g				###G^2 statistic
pval<-1-pchisq(g,v);pval					###p-value

###pi+ and p+j
qi<-c(1 - (rowtot/n));qi					###qi: qi+ = 1 - pi+
qj<-c(1 - (coltot/n));qj					###qj: q+j = 1 - p+j
###Standardized Pearson residuals
z<-c((table - muhat)/(sqrt(muhat * qi * qj)))		###z: adjusted Pearson residual
z<-array(data = c((table - muhat)/(sqrt(muhat * qi * qj))), dim = c(3,3))	
dimnames(z)<-list(Smoking = c("current","former","never"), SES = c("high","medium","low"));z

###Partitions
###G1
g1.table<-array(data = c(51,92,22,21), dim = c(2,2))
dimnames(g1.table)<-list(Smoking = c("current","former"), SES = c("high","medium"));g1.table
g1.rowtot<-apply(g1.table,1,sum)				###ni+
g1.coltot<-apply(g1.table,2,sum)				###n+j
g1.n<-sum(g1.rowtot)						###n
g1.muhat<-g1.rowtot%*%t(g1.coltot)/g1.n;			###E(f)
dimnames(g1.muhat)<-list(Smoking = c("current","former"), SES = c("high","medium"));g1.muhat
g1<-2*sum(g1.table*log(g1.table/g1.muhat));g1		###G1 partition
g1.pval<-1-pchisq(g1,1);g1.pval				###p-value
###G2
g2.table<-array(data = c(73,113,43,28), dim = c(2,2))
dimnames(g2.table)<-list(Smoking = c("current","former"), SES = c("high/medium","low"));g2.table
g2.rowtot<-apply(g2.table,1,sum)				###ni+
g2.coltot<-apply(g2.table,2,sum)				###n+j
g2.n<-sum(g2.rowtot)						###n
g2.muhat<-g2.rowtot%*%t(g2.coltot)/g2.n;			###E(f)
dimnames(g2.muhat)<-list(Smoking = c("current","former"), SES = c("high/medium","low"));g2.muhat
g2<-2*sum(g2.table*log(g2.table/g2.muhat));g2		###G2 partition
g2.pval<-1-pchisq(g2,1);g2.pval				###p-value
###G3
g3.table<-array(data = c(143,68,43,9), dim = c(2,2))
dimnames(g3.table)<-list(Smoking = c("current/former","never"), SES = c("high","medium"));g3.table
g3.rowtot<-apply(g3.table,1,sum)				###ni+
g3.coltot<-apply(g3.table,2,sum)				###n+j
g3.n<-sum(g3.rowtot)						###n
g3.muhat<-g3.rowtot%*%t(g3.coltot)/g3.n;			###E(f)
dimnames(g3.muhat)<-list(Smoking = c("current/former","never"), SES = c("high","medium"));g3.muhat
g3<-2*sum(g3.table*log(g3.table/g3.muhat));g3		###G3 partition
g3.pval<-1-pchisq(g3,1);g3.pval				###p-value
###G4
g4.table<-array(data = c(186,77,71,22), dim = c(2,2))
dimnames(g4.table)<-list(Smoking = c("current/former","never"), SES = c("high/medium","low"));g4.table
g4.rowtot<-apply(g4.table,1,sum)				###ni+
g4.coltot<-apply(g4.table,2,sum)				###n+j
g4.n<-sum(g4.rowtot)						###n
g4.muhat<-g4.rowtot%*%t(g4.coltot)/g4.n;			###E(f)
dimnames(g4.muhat)<-list(Smoking = c("current/former","never"), SES = c("high/medium","low"));g4.muhat
g4<-2*sum(g4.table*log(g4.table/g4.muhat));g4		###G4 partition
g4.pval<-1-pchisq(g4,1);g4.pval				###p-value
###Check for G
print("G-square equals");g
print("G-square equals");sum(g1,g2,g3,g4)

###Odds ratio CI
###Input values
odds.table<-array(data = c(51,68,43,22), dim = c(2,2));
dimnames(odds.table)<-list(Smoking = c("current","never"), SES = c("high","low"));odds.table
odds.estimate<-c((51*22)/(68*43));odds.estimate		###OR estimate
###ln CI
ase<-sqrt(c(1/51 + 1/68 + 1/43 + 1/22));ase		###ASE of lnOR
ln.odds.estimate=log(odds.estimate);ln.odds.estimate	###lnOR estimate
z_alpha=qnorm(0.025,lower.tail = F);z_alpha		###z(.025)
ln.theta_low=(ln.odds.estimate - (z_alpha * ase));ln.theta_low	###lnOR - (z_alpha * ASE)
ln.theta_high=(ln.odds.estimate +(z_alpha * ase));ln.theta_high	###lnOR + (z_alpha * ASE)
###OR CI
theta_low=exp(ln.theta_low);theta_low
theta_high=exp(ln.theta_high);theta_high