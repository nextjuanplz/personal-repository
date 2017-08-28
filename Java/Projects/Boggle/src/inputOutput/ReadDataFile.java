/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class ReadDataFile implements IReadDataFile {   
    //Member variables
    private Scanner scanner = null;
    private String fileName;
    private ArrayList<String> fileData;    
    
    //Custom constructor
    public ReadDataFile(String str) {
        this.fileName = str;
        this.fileData = new ArrayList<>();
    }
    
    //Getter
    /**
     * @return the fileData
     */
    public ArrayList<String> getFileData() {
        return fileData;
    }
    
    //Methods   
    @Override
    public void populateData() {
        //Try-catch structure
        try {
            URL url = getClass().getResource(fileName);
            File dataFile;
            dataFile = new File(url.toURI());
            scanner = new Scanner(dataFile);     

            //Loop to end of file
            while(scanner.hasNext()) {
                //Add file data
                getFileData().add(scanner.next());
            }
        } catch (URISyntaxException | FileNotFoundException ex) {
            Logger.getLogger(ReadDataFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (scanner != null)
                scanner.close();
        }
    }

    /**
     * @param fileData the fileData to set
     */
    public void setFileData(ArrayList<String> fileData) {
        this.fileData = fileData;
    }
}