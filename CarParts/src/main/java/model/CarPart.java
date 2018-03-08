/*
 * File: CarPart.java
 * Author: Josh Archer
 *
 * Car Part Object
 */

package model;

import java.io.Serializable;

/*
 * Car Part Object
 */
public class CarPart implements Serializable
{
    private String id;
    private String manufacturer;
    private double listPrice;

    public CarPart(String id, String manufacturer, double listPrice)
    {
        this.id = id;
        this.manufacturer = manufacturer;
        this.listPrice = listPrice;
    }

    public String getId()
    {
        return id;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public double getListPrice()
    {
        return listPrice;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public void setListPrice(double listPrice)
    {
        this.listPrice = listPrice;
    }

    public String toString()
    {
        return id + " - " + manufacturer + ": ($" + listPrice + ")";
    }
}
