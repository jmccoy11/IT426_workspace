/*
 * File: JSONExporter.java
 * Author: Jonnathon McCoy
 * Version: 1.0
 * Date: 2/28/2018
 *
 * Strategy for exporting to a file with JSON notation.
 */

package io.exporting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.IExporter;
import model.PartsModel;

import java.io.*;

/**
 * Strategy for exporting to a file with JSON notation.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class JSONExporter implements IExporter {
    
    private final String EXPORT_FILE = "files/parts.json";
    
    /**
     * Export the parts in the model to a file using an GSON to write Java objects in JSON notation.
     * @param data - Parts Model - The model responsible for the business logic for the program.
     */
    @Override
    public void exportParts(PartsModel data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(EXPORT_FILE, true)){
            gson.toJson(data.getParts().toArray(), fileWriter);
        }
        catch (IOException e) {
            System.out.println("Error using file. " + e.getMessage());
        }
    }
}
