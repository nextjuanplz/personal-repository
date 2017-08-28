/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlflow;

/**
 *
 * @author kwhiting
 */
public class ControlFlow {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // if-else if-else
        
        int testscore = 86;
        char grade;

        if (testscore >= 90) {
            grade = 'A';
        } else if (testscore >= 80) {
            grade = 'B';
        } else if (testscore >= 70) {
            grade = 'C';
        } else if (testscore >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("Grade = " + grade);
        
        // switch
        int month1 = 8;
        String monthString;
        switch (month1) 
        {
            case 1:  monthString = "January";
                     break;
            case 2:  monthString = "February";
                     break;
            case 3:  monthString = "March";
                     break;
            case 4:  monthString = "April";
                     break;
            case 5:  monthString = "May";
                     break;
            case 6:  monthString = "June";
                     break;
            case 7:  monthString = "July";
                     break;
            case 8:  monthString = "August";
                     break;
            case 9:  monthString = "September";
                     break;
            case 10: monthString = "October";
                     break;
            case 11: monthString = "November";
                     break;
            case 12: monthString = "December";
                     break;
            default: monthString = "Invalid month";
                     break;
        }
        System.out.println(monthString);

        // switch that falls through
//        int month1 = 8;
//        String monthString;
System.out.println("=================================================");
        switch (monthString) 
        {
            case "January":
                System.out.println(monthString);
                break;
            case "February":
                System.out.println(monthString);
                break;
            case "March":
                System.out.println(monthString);
                break;
            case "April":
                System.out.println(monthString);
                break;
            case "May":
                System.out.println(monthString);
                break;
            case "June":
                System.out.println(monthString);
                break;
            case "July":
                System.out.println(monthString);
                break;
            case "August":
                System.out.println(monthString);
                break;
            case "September":
                System.out.println(monthString);
                break;
            case "October":
                System.out.println(monthString);
                break;
            case "November":
                System.out.println(monthString);
                break;
            case "December":
                System.out.println(monthString);
                break;
            default:
                System.out.println("Invalid month");
                break;
        }
        System.out.println(monthString);

       if(monthString.equalsIgnoreCase("August"))
           System.out.println("Month was " + monthString);
       
        // multiple case labels
        int month = 2;
        int year = 2000;
        int numDays = 0;

        switch (month) 
        {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:          
                numDays = 31;
                break;
            case 4: case 6:
            case 9: case 11:
                numDays = 30;
                break;
            case 2:
                if (((year % 4 == 0) && 
                     !(year % 100 == 0))
                     || (year % 400 == 0))
                    numDays = 29;
                else
                    numDays = 28;
                break;
            default:
                System.out.println("Invalid month.");
                break;
        }
        System.out.println("Number of Days = " + numDays); 
        
        // String in switch
        String monthStr = "August";

        int returnedMonthNumber = getMonthNumber(monthStr);

        if (returnedMonthNumber == 0) {
            System.out.println("Invalid month");
        } else {
            System.out.println(returnedMonthNumber);
        }        
    }

    public static int getMonthNumber(String month) 
    {

        int monthNumber = 0;

        if (month == null) {
            return monthNumber;
        }

        switch (month.toLowerCase()) {
            case "january":
                monthNumber = 1;
                break;
            case "february":
                monthNumber = 2;
                break;
            case "march":
                monthNumber = 3;
                break;
            case "april":
                monthNumber = 4;
                break;
            case "may":
                monthNumber = 5;
                break;
            case "june":
                monthNumber = 6;
                break;
            case "july":
                monthNumber = 7;
                break;
            case "august":
                monthNumber = 8;
                break;
            case "september":
                monthNumber = 9;
                break;
            case "october":
                monthNumber = 10;
                break;
            case "november":
                monthNumber = 11;
                break;
            case "december":
                monthNumber = 12;
                break;
            default: 
                monthNumber = 0;
                break;
        }

        return monthNumber;
    }
}
