/*
 * File: PartsModel.java
 * Author: Josh Archer
 *
 * Business Logic for the Car Parts Import/Export Program.
 */

package model;

import io.IExporter;
import io.IImporter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Business Logic for the Car Parts Import/Export Program.
 */
public class PartsModel
{
    private Collection<CarPart> parts;

    public PartsModel()
    {
        parts = new ArrayList<CarPart>();
    }

    public void addPart(CarPart part)
    {
        parts.add(part);
    }

    public Collection<CarPart> getParts()
    {
        return Collections.unmodifiableCollection(parts);
    }

    public void saveParts(IExporter exporter)
    {
        exporter.exportParts(this);
    }

    public void loadParts(IImporter importer)
    {
        importer.importParts(this);
    }

    public void clear()
    {
        parts.clear();
    }
}
