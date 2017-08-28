###Vectors
# Define the variable vegas
vegas <- "Go!";vegas
numeric_vector <- c(1, 10, 49);numeric_vector
character_vector <- c("a", "b", "c");character_vector
# Complete the code for boolean_vector
boolean_vector <- c(TRUE,FALSE,TRUE);boolean_vector
# Poker winnings from Monday to Friday
poker_vector <- c(140, -50, 20, -120, 240)
# Roulette winnings from Monday to Friday
roulette_vector <-c(-24,-50,100,-350,10)
# Poker winnings from Monday to Friday
poker_vector <- c(140, -50, 20, -120, 240)
# Roulette winnings from Monday to Friday
roulette_vector <- c(-24, -50, 100, -350, 10)
# Assign days as names of poker_vector
names(poker_vector) <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");names(poker_vector)
# Assign days as names of roulette_vectors
names(roulette_vector)<-c("Monday","Tuesday","Wednesday","Thursday","Friday");names(roulette_vector)
# Poker and roulette winnings from Monday to Friday:
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector
# Define a new variable based on a selection
poker_wednesday <- c(poker_vector[3]);poker_wednesday
# Poker and roulette winnings from Monday to Friday:
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector
# Define a new variable based on a selection
poker_midweek <- poker_vector[c(2,3,4)];poker_midweek