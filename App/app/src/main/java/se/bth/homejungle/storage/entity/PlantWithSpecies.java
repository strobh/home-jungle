package se.bth.homejungle.storage.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.time.LocalDate;
import java.util.Objects;

/**
 * PlantWithSpecies models the relationship between a plant and its species.
 *
 * The class can be used to get the information about a plant and its species at the same time,
 * e.g., for displaying these information in a list item.
 *
 * Additionally, this class is able to calculate when a plant needs to be watered next based on
 * when the plant was watered last time and the interval in which the plant species must be watered.
 */
public class PlantWithSpecies {
    /**
     * The plant.
     */
    @Embedded
    public Plant plant;

    /**
     * The species of the plant.
     */
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

    /**
     * Calculates when the plant has to be watered next based on when the plant was watered last
     * time and the interval in which the plant species must be watered.
     *
     * @return The date when the plant has to be watered next.
     */
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
