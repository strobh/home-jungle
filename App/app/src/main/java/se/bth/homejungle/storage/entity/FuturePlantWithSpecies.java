package se.bth.homejungle.storage.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.Objects;

public class FuturePlantWithSpecies {
    @Embedded
    public FuturePlant futurePlant;

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
