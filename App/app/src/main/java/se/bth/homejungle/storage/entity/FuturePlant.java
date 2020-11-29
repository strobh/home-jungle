package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.util.Objects;

import se.bth.homejungle.storage.converter.DateConverter;

@Entity(tableName = "future_plant")
@TypeConverters(DateConverter.class)
public class FuturePlant {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "species_id")
    public long speciesId;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "plant_day")
    public LocalDate plantDay;

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
