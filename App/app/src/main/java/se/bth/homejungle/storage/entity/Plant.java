package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.util.Objects;

import se.bth.homejungle.storage.entity.converter.DateConverter;

/**
 * The Plant entity class encapsulates a plant that a user wants to manage in the app.
 *
 * We store the species of the plant, its description by which the user identifies it
 * and when it was last watered by the user.
 *
 * The Android Room Persistence Library automatically creates a database table for this entity.
 */
@Entity(tableName = "plant")
@TypeConverters(DateConverter.class)
public class Plant {

    /**
     * The id of the plant in the local database.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_id")
    public long id;

    /**
     * The id of the species of the plant in the local database.
     */
    @ColumnInfo(name = "species_id")
    public long speciesId;

    /**
     * The description of the plant by which the user can identify it.
     */
    @ColumnInfo(name = "description")
    public String description;

    /**
     * The date when the plant was last watered by the user. Based on this we can calculate
     * when the plant has to be watered next.
     */
    @ColumnInfo(name = "last_watered")
    public LocalDate lastWatered;

    /**
     * Creates a new plant.
     *
     * The date when the plant was last watered is set to the current date.
     *
     * @param speciesId The id of the species of the plant.
     * @param description The description of the plant by which the user can identify it.
     */
    public Plant(long speciesId, String description) {
        this.speciesId = speciesId;
        this.description = description;
        this.lastWatered = LocalDate.now();
    }

    /**
     * Creates a new plant based on the information of a future plant.
     *
     * @param futurePlant The future plant that was now planted.
     * @return The plant created base on the information of a future plant.
     */
    public static Plant createFromFuturePlant(FuturePlant futurePlant) {
        return new Plant(futurePlant.getSpeciesId(), futurePlant.getDescription());
    }

    public long getId() {
        return id;
    }

    public long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(long speciesId) {
        this.speciesId = speciesId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(LocalDate lastWatered) {
        this.lastWatered = lastWatered;
    }

    /**
     * Sets the date when the plant was last watered to today.
     */
    public void setLastWateredToToday() {
        this.lastWatered = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return id == plant.id &&
                speciesId == plant.speciesId &&
                Objects.equals(description, plant.description) &&
                Objects.equals(lastWatered, plant.lastWatered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, speciesId, description, lastWatered);
    }
}
