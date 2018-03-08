/*
 * File: JavaExporter.java
 * Author: Jonnathon McCoy
 * Version: 1.0
 * Date: 2/28/2018
 *
 * Strategy for importing from a file with Java Objects.
 */

package io.importing;

import io.IImporter;
import model.CarPart;
import model.PartsModel;

import java.io.*;

/**
 * Strategy for importing from a file with Java Objects.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class JavaImporter implements IImporter {
    
    private final String IMPORT_FILE = "files/parts.dat";
    
    /**
     * Imports the parts to the model using an ObjectInputStream to create Java objects from a file.
     * @param data - Parts Model - The model responsible for the business logic for the program.
     */
    @Override
    public void importParts(PartsModel data) {
        
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(IMPORT_FILE));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            
            while(fileInputStream.available() > 0) {
                data.addPart((CarPart) objectInputStream.readObject());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error using file. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input/Output error. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found in readObject() " + e.getMessage());
        }
    }
}
