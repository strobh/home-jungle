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
     * @param plantDayOfSpecies The date when the user has to plant the seeds.
     */
    public FuturePlant(long speciesId, String description, LocalDate plantDayOfSpecies) {
        this.speciesId = speciesId;
        this.description = description;
        this.plantDay = calculatePlantDateFromSpecies(plantDayOfSpecies);
    }

    /**
     * Calculates the plant day of a plant based on the generic plant day of a species,
     * which has 0 as year.
     *
     * @param plantDayOfSpecies The generic plant day of a species which has 0 as year.
     * @return The correct plant day with the current or next year depending on which day is next.
     */
    private LocalDate calculatePlantDateFromSpecies(LocalDate plantDayOfSpecies) {
        // change the year of the date to the current year
        int currentYear = LocalDate.now().getYear();
        LocalDate plantDay = plantDayOfSpecies.withYear(currentYear);

        // if the plant date is in the future, use it
        if (plantDay.isAfter(LocalDate.now())) {
            return plantDay;
        }

        // otherwise, we take the next year
        return plantDay.withYear(currentYear + 1);
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
