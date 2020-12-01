package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "species")
public class Species {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name="category_id")
    public long categoryId;

    @ColumnInfo(name="name")
    public String name;

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

    public Species(long categoryId, String name, String description, String howToStart, double water, int waterDays, double sun) {
        this.name = name;
        this.categoryId = categoryId;
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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
                categoryId == species.categoryId &&
                Double.compare(species.water, water) == 0 &&
                waterDays == species.waterDays &&
                Double.compare(species.sun, sun) == 0 &&
                Objects.equals(name, species.name) &&
                Objects.equals(description, species.description) &&
                Objects.equals(howToStart, species.howToStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, name, description, howToStart, water, waterDays, sun);
    }
}
