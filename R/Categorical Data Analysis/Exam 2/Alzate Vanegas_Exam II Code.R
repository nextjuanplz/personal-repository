######Exam II
#####Problem 1
####Data Storage
counts<-c(749,627,420,153,233,330,374,266,35,38,37,26,133,303,467,800)
S<-rep(c("Lower","Lower Middle","Upper Middle","Higher"),4)
E<-rep(c(rep("Low",4),rep("High",4)),2)
P<-c(rep("No",8),rep("Yes",8))
data<-data.frame(S,E,P,counts)

####Contingency Table
cont.table<-xtabs(formula=counts~S+E+P,data=data);cont.table

####(a)
###(SE,SP,PE) model
fit<-glm(formula=counts~.^2,data=data,family=poisson(link="log"),na.action=na.exclude,control=list(epsilon=0.0001,maxit=50,trace=T))
summary(fit)

###Lambda coefficients
lmdSE.11<-summary(fit)$coef[7]
lmdSE.12<-0
lmdSE.21<-summary(fit)$coef[8]
lmdSE.22<-0
lmdSE.31<-summary(fit)$coef[9]
lmdSE.32<-0
lmdSE.41<-0
lmdSE.42<-0
lmdPE.11<-summary(fit)$coef[13]
lmdPE.12<-0
lmdPE.21<-0
lmdPE.22<-0

###SE Local ORs
#S(Lower, Lower Middle)
ORSE.1<-exp((lmdSE.11+lmdSE.22)-(lmdSE.12+lmdSE.21));ORSE.1
#S(Lower Middle, Upper Middle)
ORSE.2<-exp((lmdSE.21+lmdSE.22)-(lmdSE.31+lmdSE.31));ORSE.2
#S(Upper Middle, Higher)
ORSE.3<-exp((lmdSE.31+lmdSE.32)-(lmdSE.42+lmdSE.41));ORSE.3
#S(Lower, Higher)
ORSE.4<-exp((lmdSE.11+lmdSE.41)-(lmdSE.42+lmdSE.12));ORSE.4
#S(Lower, Upper Middle)
ORSE.5<-exp((lmdSE.11+lmdSE.31)-(lmdSE.32+lmdSE.12));ORSE.5
#S(Lower Middle, Higher)
ORSE.6<-exp((lmdSE.21+lmdSE.41)-(lmdSE.42+lmdSE.22));ORSE.6

###PE OR
ln.ORPE<-(lmdPE.11+lmdPE.22)-(lmdPE.12+lmdPE.21)
ORPE<-exp(ln.ORPE);ORPE

###PE OR 95% CI
z_alpha=qnorm(0.025,lower.tail=F)
ln.ORPE.se<-coef(summary(fit))[13, "Std. Error"]
ORPE.ci<-exp(ln.ORPE+z_alpha*c(-1,1)*ln.ORPE.se);ORPE.ci

####(b)
###Goodness-of-fit
G.sq<-fit$deviance;G.sq
G.sq.df<-fit$df.residual;G.sq.df
p.val<-round(1-pchisq(G.sq,G.sq.df),4);p.val

###Residuals
save.predict<-predict(object=fit,type="response")
save.pearson<-residuals(object=fit,type="pearson")
h<-lm.influence(model=fit)$h
standard.pearson<-save.pearson/sqrt(1-h)
save.all<-data.frame(data,predict=round(save.predict,4),pearson=round(save.pearson,4),standard.pearson=round(standard.pearson,4))
xtabs(standard.pearson~S+E+P,data=save.all)

####(c)
###(SE,SP) model
fit2<-glm(counts~(E+S)^2+(P+S)^2,data=data,family=poisson(link="log"),na.action=na.exclude,control=list(epsilon=0.0001,maxit=50,trace=T))
summary(fit2)
###Test if P and E are conditionally independent
G.sq.S<-fit2$deviance-fit$deviance;G.sq.S
G.sq.df.S<-fit2$df.residual- fit$df.residual;G.sq.df.S
p.val.S<-round(1-pchisq(G.sq.S,G.sq.df.S),4);p.val.S

#####Problem 2
####Data Storage
pct<-c(.113,.229,0,.028)
Victim<-c(rep("White",2),rep("Black",2))
Defendant<-rep(c("White","Black"),2)
data.2<-data.frame(Victim,Defendant,pct)

####Contingency Table
cont.table.2<-xtabs(formula=pct~Victim+Defendant,data=data.2);cont.table.2

####(a)
###Logit model
fit3<-glm(formula=pct~Victim+Defendant,data=data.2,family=binomial(logit))
summary(fit3)

####(b)
###Conditional OR between defendant race and death penalty
ln.ORDefVer<-summary(fit3)$coef[3]
ORDefVer<-exp(ln.ORDefVer);ORDefVer
ln.ORDefVer.se<-coef(summary(fit3))[3, "Std. Error"]
ORDefVer.ci<-exp(ln.ORDefVer+z_alpha*c(-1,1)*ln.ORDefVer.se);ORDefVer.ci


####(c)
###Reduced logit model
fit4<-glm(formula=pct~Victim,data=data.2,family=binomial(logit))
summary(fit4)

###Test effect of defendant’s race controlling for victim’s race
G.sq.defendant<-fit4$deviance-fit3$deviance;G.sq.defendant
G.sq.defendant.df<-fit4$df.residual- fit3$df.residual;G.sq.defendant.df
p.val.defendant<-round(1-pchisq(G.sq.defendant,G.sq.defendant.df),4);p.val.defendant
