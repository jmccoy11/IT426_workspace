/*
 * File: IExporter.java
 * Author: Josh Archer
 *
 * Interface for applying a strategy for exporting objects in a particular format.
 */

package io;

import model.PartsModel;

/**
 * Interface for applying a strategy for exporting objects in a particular format.
 */
public interface IExporter
{
    public void exportParts(PartsModel data);
}
