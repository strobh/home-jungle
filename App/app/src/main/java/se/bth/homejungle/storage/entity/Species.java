package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Species {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="category")
    public String category;

    @ColumnInfo(name="description")
    public String description;

    @ColumnInfo(name="how_to_start")
    public String howToStart;

    @ColumnInfo(name="water")
    public double water;

    @ColumnInfo(name="water_period")
    public int waterDays;

    @ColumnInfo(name="sun")
    public double sun;

    public Species(String name, String category, String description, String howToStart, double water, int waterDays, double sun) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.howToStart = howToStart;
        this.water = water;
        this.waterDays = waterDays;
        this.sun = sun;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowToStart() {
        return howToStart;
    }

    public void setHowToStart(String howToStart) {
        this.howToStart = howToStart;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public int getWaterDays() {
        return waterDays;
    }

    public void setWaterDays(int waterDays) {
        this.waterDays = waterDays;
    }

    public double getSun() {
        return sun;
    }

    public void setSun(double sun) {
        this.sun = sun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Species species = (Species) o;
        return id == species.id &&
                Double.compare(species.water, water) == 0 &&
                waterDays == species.waterDays &&
                Double.compare(species.sun, sun) == 0 &&
                Objects.equals(name, species.name) &&
                Objects.equals(category, species.category) &&
                Objects.equals(description, species.description) &&
                Objects.equals(howToStart, species.howToStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, description, howToStart, water, waterDays, sun);
    }
}
