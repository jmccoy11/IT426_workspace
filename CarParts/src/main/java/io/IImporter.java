/*
 * File: IImporter.java
 * Author: Josh Archer
 *
 * Interface for applying a strategy for importing objects in a particular format.
 */

package io;

import model.PartsModel;

/**
 * Interface for applying a strategy for importing objects in a particular format.
 */
public interface IImporter
{
    public void importParts(PartsModel data);
}
