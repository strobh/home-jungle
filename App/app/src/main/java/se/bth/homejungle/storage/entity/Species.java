package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.util.Objects;

import se.bth.homejungle.storage.converter.DateConverter;

/**
 * The Species entity class encapsulates a plant species.
 *
 * Plant species cannot be created by the user. The app provides a database of species with
 * information about each of them that are relevant to care for a plant of this species.
 * This includes a general description and information about the plant species, how a user can
 * plant the seeds of this species, how much water a species needs and in which intervals,
 * as well as how much sun plants of this species need.
 *
 * The Android Room Persistence Library automatically creates a database table for this entity.
 */
@Entity(tableName = "species")
@TypeConverters(DateConverter.class)
public class Species {

    /**
     * The id of the plant species.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "species_id")
    public long id;

    /**
     * The id of the category of the plant species.
     */
    @ColumnInfo(name="category_id")
    public long categoryId;

    /**
     * The name of the image file.
     */
    @ColumnInfo(name="image")
    public String image;

    /**
     * The name of the plant species.
     */
    @ColumnInfo(name="name")
    public String name;

    /**
     * A description of the plant species.
     */
    @ColumnInfo(name="description")
    public String description;

    /**
     * A description of how seeds of the plant species can be planted.
     */
    @ColumnInfo(name="how_to_start")
    public String howToStart;

    /**
     * How much water the plant species needs.
     */
    @ColumnInfo(name="water")
    public double water;

    /**
     * The interval in which the plant needs to be watered in days.
     */
    @ColumnInfo(name="water_period")
    public int waterDays;

    /**
     * How much sun the plant species needs.
     */
    @ColumnInfo(name="sun")
    public double sun;

    /**
     * When seeds of the species should be planted.
     */
    @ColumnInfo(name="plant_date")
    public LocalDate plantDate;

    public Species(long categoryId, String image, String name, String description, String howToStart, double water, int waterDays, double sun, LocalDate plantDate) {
        this.image = image;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.howToStart = howToStart;
        this.water = water;
        this.waterDays = waterDays;
        this.sun = sun;
        this.plantDate = plantDate;
    }

    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public LocalDate getPlantDate() {
        return plantDate;
    }

    public void setPlantDate(LocalDate plantDate) {
        this.plantDate = plantDate;
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
                Objects.equals(howToStart, species.howToStart) &&
                Objects.equals(plantDate, species.plantDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, name, description, howToStart, water, waterDays, sun, plantDate);
    }
}
