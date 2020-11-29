package se.bth.homejungle.storage.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.time.LocalDate;
import java.util.Objects;

public class PlantWithSpecies {
    @Embedded
    public Plant plant;

    @Relation(
            parentColumn = "species_id",
            entityColumn = "id"
    )
    public Species species;

    public Plant getPlant() {
        return plant;
    }

    public Species getSpecies() {
        return species;
    }

    public LocalDate getNextWateringDate() {
        LocalDate lastWatered = plant.getLastWatered();
        return lastWatered.plusDays(species.getWaterDays());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlantWithSpecies that = (PlantWithSpecies) o;
        return Objects.equals(plant, that.plant) &&
                Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plant, species);
    }
}
