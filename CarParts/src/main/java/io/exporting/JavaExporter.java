/*
 * File: JavaExporter.java
 * Author: Jonnathon McCoy
 * Version: 1.0
 * Date: 2/28/2018
 *
 * Strategy for exporting to a file with Java Objects.
 */

package io.exporting;

import io.IExporter;
import model.CarPart;
import model.PartsModel;

import java.io.*;

/**
 * Strategy for exporting to a file with Java Objects.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class JavaExporter implements IExporter {
    
    private final String EXPORT_FILE = "files/parts.dat";
    
    /**
     * Export the parts in the model to a file using an ObjectOutputStream to write Java objects.
     * @param data - Parts Model - The model responsible for the business logic for the program.
     */
    @Override
    public void exportParts(PartsModel data) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(EXPORT_FILE), true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(CarPart part: data.getParts()) {
                objectOutputStream.writeObject(part);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error using file. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input/Output error. " + e.getMessage());
        }
    }
}
