/*
 * File: XMLExporter.java
 * Author: Jonnathon McCoy
 * Version: 1.0
 * Date: 2/28/2018
 *
 * Strategy for importing from a file XML Format.
 */

package io.importing;

import io.IImporter;
import model.CarPart;
import model.PartsModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Strategy for importing from a file XML Format.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class XMLImporter implements IImporter {
    
    private final String IMPORT_FILE = "files/parts.xml";
    
    /**
     * Imports the parts to the model using an ObjectInputStream to create Java objects from a file.
     * @param data - Parts Model - The model responsible for the business logic for the program.
     */
    @Override
    public void importParts(PartsModel data) {
        try {
            File fXmlFile = new File(IMPORT_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
    
            doc.getDocumentElement().normalize();
            
            NodeList partsList = doc.getElementsByTagName("part");
            
            for (int temp = 0; temp < partsList.getLength(); temp++) {
        
                Node partNode = partsList.item(temp);
        
                if (partNode.getNodeType() == Node.ELEMENT_NODE) {
            
                    Element partElement = (Element) partNode;
            
                    String partId = partElement.getElementsByTagName("id").item(0).getTextContent();
                    String manufacturer = partElement.getElementsByTagName("manufacturer").item(0).getTextContent();
                    String listPrice = partElement.getElementsByTagName("listprice").item(0).getTextContent();

                    data.addPart(new CarPart(partId, manufacturer, Double.parseDouble(listPrice)));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
