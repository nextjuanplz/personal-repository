###Read table with header
data<-read.table("crab.txt", header = T);data

###Attaches data to a matrix with headers as variables
attach(data)

###Print color column
color

###Data preparation
weight<-weight/1000; color = color - 1;
satell<-ifelse(satell>0,1,0);satell		###If satell > 0 --> 1, else --> 0

###Logistic regression
fit<-glm(satell~width,family=binomial(logit));fit ###Logit is default link function
###Access variables
summary(fit)					###More information for Wald Test
names(fit)						###Shows variables
fit$coefficients
fit$coefficients[1]
fit$coefficients[2]
summary(fit$residuals)

###Wald CI
beta<-coef(fit)[2];beta				###2nd row
vb<-vcov(fit);vb
seb<-sqrt(vb[2,2]);seb
alpha<-.05
wald.ci<-beta+c(-1,1)*qnorm(1-alpha/2)*seb;wald.ci
ebci<-exp(wald.ci);ebci

###Prediction of pi(x_0)
new<-data.frame(width = 26.5)
prednew<-predict(object = fit, newdata = new, type = "response", se = T);prednew
###CI for pi(x_0)
lower<-prednew$fit-qnorm(1-alpha/2)*prednew$se;lower
upper<-prednew$fit+qnorm(1-alpha/2)*prednew$se;upper
ci<-prednew$fit+c(-1,1)*qnorm(1-alpha/2)*prednew$se;ci
###Likelihood Ratio CI
lr<-confint(fit)
lrci<-lr[2,];lrci
elrci<-exp(lrci);elrci

###Probit link function
fitp<-glm(satell~width,family=binomial(probit));fitp
summary(fitp)
new<-data.frame(width = 26.5)
prednewp<-predict(object = fitp, newdata = new, type = "response", se = T);prednewp

###
y<-cbind(c(14,32,11,12),c(93,81,52,43));y
rbind(c(14,32,11,12),c(93,81,52,43))
x<c(1,0,1,0)
z<-c(1,1,0,0)
fitd<-glm(y~x+z,family=binomial);fitd		###Logit is default
summary(fitd);