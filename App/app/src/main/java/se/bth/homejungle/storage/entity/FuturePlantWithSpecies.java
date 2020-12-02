package se.bth.homejungle.storage.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.Objects;

/**
 * FuturePlantWithSpecies models the relationship between a future plant and its species.
 *
 * The class can be used to get the information about a future plant and its species at the same
 * time, e.g., for displaying these information in a list item.
 */
public class FuturePlantWithSpecies {
    /**
     * The future plant.
     */
    @Embedded
    public FuturePlant futurePlant;

    /**
     * The species of the future plant.
     */
    @Relation(
            parentColumn = "species_id",
            entityColumn = "id"
    )
    public Species species;

    public FuturePlant getFuturePlant() {
        return futurePlant;
    }

    public Species getSpecies() {
        return species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuturePlantWithSpecies that = (FuturePlantWithSpecies) o;
        return Objects.equals(futurePlant, that.futurePlant) &&
                Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(futurePlant, species);
    }
}
