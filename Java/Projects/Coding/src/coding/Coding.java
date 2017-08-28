/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template DataFile, choose Tools | Templates
 * and open the template in the editor.
 */
package coding;

import core.DataFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class Coding {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        //Initialize DataFile array
        Coding.dataFiles = new DataFile[numberOfDataFiles];
        
        //Create DataFiles
        for (DataFile dataFile: Coding.dataFiles) {
            //Ask user to enter name
            //String fileName;
            dataFile = new DataFile(fileName);
        }
        
        System.out.println("Files loaded successfully.");
        
        //Open programs
        for(Datafile dataFile: Coding.dataFiles) {
            try {
                String brakeCommands = new String(TIMES_TO_BREAK + " " +
                                       numberOfDataFiles + " "
                                       + TIMES_TO_BREAK_OUTPUT + " "
                                       + dataFile.fileName + "BRAKE.txt");
                
                
                Process timesToBreakProcess = Runtime.getRuntime().exec(
                                              brakeCommands);
                
                
                //Catch values
                //dataFile.setBrakeDur = ;
                //dataFile.NumberOfBrakes = ;
                timesToBreakProcess.waitFor();
            } catch (IOException ex) {
                Logger.getLogger(Coding.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error opening Times to Break.exe");
            }

            //Speed
            try {
                String brakeCommands = new String(SPEED + " " +
                                       numberOfDataFiles + " "
                                       + SPEED_OUTPUT + " "
                                       + dataFile.fileName + "SPEED.txt");
                Process speedProcess = Runtime.getRuntime().exec(SPEED);
                //dataFile.setAvgSpeed = ;
                speedProcess.waitFor();
            } catch (IOException ex) {
                Logger.getLogger(Coding.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error opening Speed.exe");
            }
        }
        
        
    }
    
    //Fields
    private static final String TIMES_TO_BREAK = "../Times to break.exe";
    private static final String SPEED = "../speed.exe";
    private static final String TIMES_TO_BREAK_OUTPUT = "__TIMES_TO_BREAK_OUTPUT";
    private static final String SPEED_OUTPUT = "__SPEED_OUTPUT";
    
    public static int numberOfDataFiles;
    private static String outputName;
    public DataFile[] dataFiles;
    private XSSFWorkbook workBook;
    private static final String[] OUTPUT_HEADER = 
    {"DataFile", "Number of Brakes", "Avg. Brake Duration (s.)", "Avg. Speed"};
    
    //Methods
    private void outputDataFile() {
        workBook = new XSSFWorkbook();
        XSSFSheet sheet = this.workBook.createSheet(outputName);
        Object dataTypes[][] = new Object[Coding.numberOfDataFiles][OUTPUT_HEADER.length];
        
        //Add header
        int i = 0;
        dataTypes[i] = new Object[OUTPUT_HEADER.length];
        for (String headerComponent: OUTPUT_HEADER) {
            dataTypes[0][i] = headerComponent;
            i++;
        }
        
        //Add stuff from DataFiles
        i = 1;
        for (DataFile DataFile: dataFiles) {
            dataTypes[i][0] = DataFile.getFileName();
            dataTypes[i][1] = DataFile.getNumberOfBrakes();
            dataTypes[i][2] = DataFile.getBrakeDur();    
            dataTypes[i][3] = DataFile.getAvgSpeed();
            //Accel
            i++;
        }
        
        int rowNum = 0;

        //Copy values onto spreadsheet
        for (Object[] datatype : dataTypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                } else if (field instanceof Double) {
                    cell.setCellValue((Double) field);
                }
            }
        }
        
        //Write to DataFile
        try {
            DataFileOutputStream outputStream = new DataFileOutputStream("../" + outputName);
            workBook.write(outputStream);
            workBook.close();
        } catch (DataFileNotFoundException e) {
            System.out.println("FNF Exception");
        }
        
        
    }
}
