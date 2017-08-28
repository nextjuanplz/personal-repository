/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package inputoutput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class InputOutput {

    public static void main(String[] args) throws IOException {

        // Byte-stream I/O
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error opening files");
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        
        // Character stream I/O
        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("characteroutput.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        
        // Line-based I/O
        BufferedReader lineInStream = null;
        PrintWriter lineOutStream = null;

        try {
            lineInStream = new BufferedReader(new FileReader("xanadu.txt"));
            lineOutStream = new PrintWriter(new FileWriter("lineoutput.txt"));

            String l;
            while ((l = lineInStream.readLine()) != null) {
                lineOutStream.println(l);
            }
        } finally {
            if (lineInStream != null) {
                lineInStream.close();
            }
            if (lineOutStream != null) {
                lineOutStream.close();
            }
        }
        
        // Scanner
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("xanaduScan.txt")));

            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    
        // Scanner with locale
        double sum = 0;

        try {
            s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));
            s.useLocale(Locale.US);

            while (s.hasNext()) {
                if (s.hasNextDouble()) {
                    double value = s.nextDouble();
                    sum += value;
                } else {
                    s.next();
                }   
            }
        } finally {
            s.close();
        }

        System.out.println(sum);

        // print and println
        int i = 2;
        double r = Math.sqrt(i);
        
        System.out.print("The square root of ");
        System.out.print(i);
        System.out.print(" is ");
        System.out.print(r);
        System.out.println(".");

        i = 5;
        r = Math.sqrt(i);
        System.out.println("The square root of " + i + " is " + r + ".");
    
        // format specifiers
        int ii = 2;
        double rr = Math.sqrt(ii);
        
        System.out.format("The square root of %d is %f.%n", ii, rr);
        
        //additional elements
        System.out.format("%f, %1$+020.10f %n", Math.PI);
    }    
}
