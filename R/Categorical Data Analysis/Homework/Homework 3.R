###Read table with header
data<-read.csv("challenger.csv", header = TRUE);data
###Attaches data to a matrix with headers as variables
attach(data)

###Data preparation
O.ring2<-ifelse(O.ring>0,1,0);O.ring2

###Logistic regression
fit<-glm(O.ring2~Temp+Pressure,family=binomial(logit))
summary(fit)

###Likelihood Ratio CI
lr<-confint(fit);lr
elr<-exp(lr);elr

###LRT
nul<-fit$null.deviance;nul
res<-fit$deviance;res
lrt<-nul-res;lrt

##New fit without pressure
fit2<-glm(O.ring2~Temp,family=binomial(logit))
predict(object = fit2, newdata = data.frame(Temp), type = "response")

###Plots
#ag<-aggregate(formula = O.ring2 ~ Temp, data = data, FUN = sum)
n<-sum(fit2$y)

plot(x = Temp,y = O.ring2,col="black",main="O.ring vs. Temp",xlab="Temp",ylab="O.ring",xlim=c(31,51),ylim=c(0,1));
curve(expr = predict(object = fit2, newdata = data.frame(Temp = x), type = "response"), col = "black", add = T, xlim=c(31,51))
ci.pi<-function(newdata, mod.fit.obj, alpha){
      # print(newdata)  # Test
      linear.pred<-predict(object = mod.fit.obj, newdata = newdata, type = "link", se = TRUE)
      CI.lin.pred.lower<-linear.pred$fit - qnorm(p = 1-alpha/2)*linear.pred$se
      CI.lin.pred.upper<-linear.pred$fit + qnorm(p = 1-alpha/2)*linear.pred$se
      CI.pi.lower<-exp(CI.lin.pred.lower) / (1 + exp(CI.lin.pred.lower))
      CI.pi.upper<-exp(CI.lin.pred.upper) / (1 + exp(CI.lin.pred.upper))
      list(lower = CI.pi.lower, upper = CI.pi.upper)
    }
###Plot C.I. bands
curve(expr = ci.pi(newdata = data.frame(Temp = x), mod.fit.obj = fit2, alpha = 0.05)$lower, col = "blue", lty = "dotdash", add = TRUE, xlim = c(31, 51))
curve(expr = ci.pi(newdata = data.frame(Temp = x), mod.fit.obj = fit2, alpha = 0.05)$upper, col = "blue", lty = "dotdash", add = TRUE, xlim = c(31, 51))

###95%CI for Temp = 31
new<-data.frame(Temp = 31)
prednew<-predict(object = fit2, newdata = new, type = "response", se = T);prednew
alpha<-.05
lower<-prednew$fit-qnorm(1-alpha/2)*prednew$se;lower
upper<-prednew$fit+qnorm(1-alpha/2)*prednew$se;upper

###90%CI for Temp = 31
new<-data.frame(Temp = 31)
prednew<-predict(object = fit2, newdata = new, type = "response", se = T);prednew
alpha<-.1
lower<-prednew$fit-qnorm(1-alpha/2)*prednew$se;lower
upper<-prednew$fit+qnorm(1-alpha/2)*prednew$se;upper

###90%CI for Temp = 72
new<-data.frame(Temp = 72)
prednew<-predict(object = fit2, newdata = new, type = "response", se = T);prednew
alpha<-.1
lower<-prednew$fit-qnorm(1-alpha/2)*prednew$se;lower
upper<-prednew$fit+qnorm(1-alpha/2)*prednew$se;upper

###New fit with quadratic term
fit3<-glm(O.ring2~Temp+I(Temp^2),family=binomial(logit))
summary(fit3)

###Other fits
#Probit
probfit<-glm(O.ring2~Temp,family=binomial(probit))
summary(probfit)
###90%CI for Temp = 31
new<-data.frame(Temp = 31)
prednew<-predict(object = probfit, newdata = new, type = "response", se = T);prednew
alpha<-.1
lower<-prednew$fit-qnorm(1-alpha/2)*prednew$se;lower
upper<-prednew$fit+qnorm(1-alpha/2)*prednew$se;upper
###90%CI for Temp = 72
new<-data.frame(Temp = 72)
prednew<-predict(object = probfit, newdata = new, type = "response", se = T);prednew
alpha<-.1
lower<-prednew$fit-qnorm(1-alpha/2)*prednew$se;lower
upper<-prednew$fit+qnorm(1-alpha/2)*prednew$se;upper

#Log-log
logfit<-glm(O.ring2~Temp,family=binomial(cloglog))
summary(logfit)
###90%CI for Temp = 31
new<-data.frame(Temp = 31)
prednew<-predict(object = logfit, newdata = new, type = "response", se = T);prednew
alpha<-.1
lower<-prednew$fit-qnorm(1-alpha/2)*prednew$se;lower
upper<-prednew$fit+qnorm(1-alpha/2)*prednew$se;upper

###90%CI for Temp = 72
new<-data.frame(Temp = 72)
prednew<-predict(object = logfit, newdata = new, type = "response", se = T);prednew
alpha<-.1
lower<-prednew$fit-qnorm(1-alpha/2)*prednew$se;lower
upper<-prednew$fit+qnorm(1-alpha/2)*prednew$se;upper

###Read table with header
data2<-read.csv("placekick.csv", header = TRUE);data2
###Attaches data to a matrix with headers as variables
attach(data2)

###Logistic regression and LRT
##Interaction
modfit<-glm(good~distance+wind+I(wind*distance),family=binomial(logit))
summary(modfit)
#LRT
nul<-modfit$null.deviance;nul
res<-modfit$deviance;res
lrt<-nul-res;lrt
##No interaction
modfit2<-glm(good~distance+wind,family=binomial(logit))
summary(modfit2)
#LRT
nul<-modfit2$null.deviance;nul
res<-modfit2$deviance;res
lrt<-nul-res;lrt

