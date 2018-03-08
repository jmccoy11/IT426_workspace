/*
 * File: JSONExporter.java
 * Author: Jonnathon McCoy
 * Version: 1.0
 * Date: 2/28/2018
 *
 * Strategy for importing from a file with JSON notation.
 */

package io.importing;

import com.google.gson.Gson;
import io.IImporter;
import model.CarPart;
import model.PartsModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Strategy for importing from a file with JSON notation.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class JSONImporter implements IImporter {
    
    private final String IMPORT_FILE = "files/parts.json";
    
    /**
     * Imports the parts to the model using an ObjectInputStream to create Java objects from a file.
     * @param data - Parts Model - The model responsible for the business logic for the program.
     */
    @Override
    public void importParts(PartsModel data) {
        Gson gson = new Gson();
    
        try (FileReader fileReader = new FileReader(IMPORT_FILE)) {
            CarPart[] parts = gson.fromJson(fileReader, CarPart[].class);
            for(CarPart part: parts) {
                data.addPart(part);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
