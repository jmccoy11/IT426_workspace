package entities;

import java.util.UUID;

public class Fruit
{
    private UUID id;
    private String type;
    private boolean fresh;

    public Fruit(String type, boolean fresh)
    {
        id = UUID.randomUUID();
        this.type = type;
        this.fresh = fresh;
    }

    public UUID getId()
    {
        return id;
    }

    public String getType()
    {
        return type;
    }

    public boolean isFresh()
    {
        return fresh;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setFresh(boolean fresh)
    {
        this.fresh = fresh;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fruit fruit = (Fruit) o;

        return id != null ? id.equals(fruit.id) : fruit.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "Fruit {type='" + type + '\'' +
                ", fresh=" + fresh + '}';
    }
}
