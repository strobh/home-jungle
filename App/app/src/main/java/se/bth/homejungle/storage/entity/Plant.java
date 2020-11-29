package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.util.Objects;

import se.bth.homejungle.storage.converter.DateConverter;

@Entity(tableName = "plant")
@TypeConverters(DateConverter.class)
public class Plant {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "species_id")
    public long speciesId;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "last_watered")
    public LocalDate lastWatered;

    public Plant(long speciesId, String description) {
        this.speciesId = speciesId;
        this.description = description;
        this.lastWatered = LocalDate.now();
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
