###Problem 7.4
#Complete model
table<-data.frame(expand.grid(G=factor(c("yes","no"),levels=c("yes","no")),
I=factor(c("yes","no"),levels=c("yes","no")),
H=factor(c("yes","no"),levels=c("yes","no"))), count=c(76,114,6,11,160,181,25,48))
table
options(contrasts=c("contr.treatment","contr.poly"))
fit<-glm(count~.^2,data=table,family=poisson)
summary(fit)
#Reduced model
table2<-data.frame(expand.grid(G=factor(c("yes","no"),levels=c("yes","no")),
I=factor(c("yes","no"),levels=c("yes","no"))), count=c(76+160,114+181,6+25,11+48))
options(contrasts=c("contr.treatment","contr.poly"))
fit2<-glm(count~.,data=table2,family=poisson)
summary(fit2)
g2<-fit2$deviance-fit$deviance;g2

###Problem 7.10
#Complete model
table3<-data.frame(expand.grid(Safety=factor(c("yes","no"),levels=c("yes","no")),
Ejected=factor(c("yes","no"),levels=c("yes","no")),
NonfatalInjury=factor(c("yes","no"),levels=c("yes","no"))), count=c(1105,4624,411111,157342,14,497,483,1008))
options(contrasts=c("contr.treatment","contr.poly"))
fit3<-glm(count~.^2,data=table3,family=poisson)
summary(fit3)