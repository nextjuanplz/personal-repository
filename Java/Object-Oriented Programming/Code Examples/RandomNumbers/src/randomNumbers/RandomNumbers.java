/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package randomNumbers;

/**
 *
 * @author kwhiting
 */
import java.util.Random;

public class RandomNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random randomNumbers = new Random();

        System.out.println("\nExample no shifting or scaling factor");
        
        boolean randomBool = randomNumbers.nextBoolean();
        int randomInt = randomNumbers.nextInt();
        long randomLong = randomNumbers.nextLong();
        double randomDub = randomNumbers.nextDouble();
        float randomFloat = randomNumbers.nextFloat();

        System.out.println("randomBool = " + randomBool);
        System.out.println("randomInt = " + randomInt);
        System.out.println("randomLong = " + randomLong);
        System.out.println("randomDub = " + randomDub);
        System.out.println("randomFloat = " + randomFloat);
        
        System.out.println("\nExample with shiftingValue and scalingFactor");
        
        
        int face;
        for(int counter = 1; counter <=20; counter++)
        {
            // random number from 1-6
            face = 1 + randomNumbers.nextInt( 6 );
            
            System.out.print(face + " ");
            
            if (counter %5 == 0)
                System.out.println();
        }

        System.out.println("\nExample with differenceBetweenValues");
        int value;
        for(int number = 1; number <=20; number++)
        {
            value = 4 + 3 * randomNumbers.nextInt( 9 );
            
            System.out.print(value + " ");
            
            if (number %5 == 0)
                System.out.println();
        }

        System.out.println("\nExample with seedValue");
        int seedValue = 53;
        int result;
        
        randomNumbers.setSeed(seedValue);
        
        for(int number = 1; number <=20; number++)
        {
            result = randomNumbers.nextInt();
            
            System.out.print(result + " ");
            
            if (number %5 == 0)
                System.out.println();
        }
    }
}
