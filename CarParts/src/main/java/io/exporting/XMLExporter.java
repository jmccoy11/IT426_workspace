/*
 * File: XMLExporter.java
 * Author: Jonnathon McCoy
 * Version: 1.0
 * Date: 2/28/2018
 *
 * Strategy for exporting to a file XML Format.
 */

package io.exporting;

import io.IExporter;
import model.CarPart;
import model.PartsModel;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

/**
 * Strategy for exporting to a file with XML Format.
 *
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class XMLExporter implements IExporter {
    
    private final String EXPORT_FILE = "files/parts.xml";
    
    /**
     * Export the parts in the model to a file using a DocumentBuilder to create an XML Document.
     * @param data - Parts Model - The model responsible for the business logic for the program.
     */
    @Override
    public void exportParts(PartsModel data) {
    
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocument = documentFactory.newDocumentBuilder();
    
            // root elements
            Document partsDocument = xmlDocument.newDocument();
            
            Element allPartsElements = partsDocument.createElement("parts");
            partsDocument.appendChild(allPartsElements);
    
            for(CarPart part: data.getParts()) {
                Element partElement = partsDocument.createElement("part");
                allPartsElements.appendChild(partElement);
                
                Element idElement = partsDocument.createElement("id");
                idElement.appendChild(partsDocument.createTextNode(part.getId()));
                partElement.appendChild(idElement);
    
                Element manufacturerElement = partsDocument.createElement("manufacturer");
                manufacturerElement.appendChild(partsDocument.createTextNode(part.getManufacturer()));
                partElement.appendChild(manufacturerElement);
    
                Element listPriceElement = partsDocument.createElement("listprice");
                listPriceElement.appendChild(partsDocument.createTextNode(String.valueOf(part.getListPrice())));
                partElement.appendChild(listPriceElement);
    
            }
    
            //create the xml transformer and set output properties to make it print with new lines
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            //write to file
            StreamResult result = new StreamResult(new File(EXPORT_FILE));
            DOMSource source = new DOMSource(partsDocument);
            transformer.transform(source, result);
    
            System.out.println("File Saved!");
        } catch (ParserConfigurationException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (TransformerConfigurationException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (TransformerException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
