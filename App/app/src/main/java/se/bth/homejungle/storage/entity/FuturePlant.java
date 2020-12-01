package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.util.Objects;

import se.bth.homejungle.storage.converter.DateConverter;

/**
 * The FuturePlant entity class encapsulates a plant that a user wants to plant in the future
 * and for which the user wants to be remind when he has to plant the seeds.
 *
 * We store the species of the plant, its description by which the user identifies it
 * and when the user has to plant the seeds.
 *
 * The Android Room Persistence Library automatically creates a database table for this entity.
 */
@Entity(tableName = "future_plant")
@TypeConverters(DateConverter.class)
public class FuturePlant {

    /**
     * The id of the plant in the local database.
     */
    @PrimaryKey(autoGenerate = true)
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
     * The date when the user has to plant the seeds.
     */
    @ColumnInfo(name = "plant_day")
    public LocalDate plantDay;

    /**
     * Creates a new future plant.
     *
     * @param speciesId The id of the species of the plant.
     * @param description The description of the plant by which the user can identify it.
     * @param plantDay The date when the user has to plant the seeds.
     */
    public FuturePlant(long speciesId, String description, LocalDate plantDay) {
        this.speciesId = speciesId;
        this.description = description;
        this.plantDay = plantDay;
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

    public LocalDate getPlantDay() {
        return plantDay;
    }

    public void setPlantDay(LocalDate plantDay) {
        this.plantDay = plantDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuturePlant that = (FuturePlant) o;
        return id == that.id &&
                speciesId == that.speciesId &&
                Objects.equals(description, that.description) &&
                Objects.equals(plantDay, that.plantDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, speciesId, description, plantDay);
    }
}
