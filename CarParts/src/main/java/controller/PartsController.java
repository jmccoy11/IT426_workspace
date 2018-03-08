/*
 * File: PartsController.java
 * Author: Jonnathon McCoy
 * Version: 1.1
 * Date: 2/28/2018
 *
 * Controller for the Car Parts Import/Export Program.
 */

package controller;

import io.exporting.JSONExporter;
import io.exporting.JavaExporter;
import io.exporting.XMLExporter;
import io.importing.JSONImporter;
import io.importing.JavaImporter;
import io.importing.XMLImporter;
import model.CarPart;
import model.PartsModel;

import java.util.Collection;

/**
 * Controller for the Car Parts Import/Export Program.
 *
 * @author Josh Archer
 * @author Jonnathon McCoy
 * @version 1.1
 */
public class PartsController
{
    private PartsModel model;
    
    /**
     * Constructor for PartController
     */
    public PartsController()
    {
        model = new PartsModel();
    }
    
    /**
     * Pass a CarPart object to the model to add using the id, manufacturer, and listPrice.
     * @param id - String - CarPart id
     * @param manufacturer - String - manufacturer of the car part
     * @param listPrice - double - Listed price.
     */
    public void addPart(String id, String manufacturer, double listPrice)
    {
        model.addPart(new CarPart(id, manufacturer, listPrice));
    }
    
    /**
     * Get all parts currently loaded into the model.
     * @return - Collection<CarPart> - Collection of all parts currently loaded into the model.
     */
    public Collection<CarPart> getParts()
    {
        return model.getParts();
    }
    
    /**
     * Import parts using a strategy based on whether to use the Java importer, the JSON importer, or the XML
     * importer.
     * @param strategy - String - String value from the UI for which strategy to use.
     */
    public void importParts(String strategy)
    {
        switch(strategy) {
            case "Java": model.loadParts(new JavaImporter());
                break;
            case "JSON": model.loadParts(new JSONImporter());
                break;
            case "XML": model.loadParts(new XMLImporter());
                break;
            default: break;
        }
    }
    
    /**
     * Export parts using a strategy based on whether to use the Java exporter, the JSON exporter, or the XML
     * exporter.
     * @param strategy - String - String value from the UI for which strategy to use.
     */
    public void exportParts(String strategy)
    {
        switch(strategy) {
            case "Java": model.saveParts(new JavaExporter());
                break;
            case "JSON": model.saveParts(new JSONExporter());
                break;
            case "XML": model.saveParts(new XMLExporter());
                break;
            default: break;
        }
    }
}
