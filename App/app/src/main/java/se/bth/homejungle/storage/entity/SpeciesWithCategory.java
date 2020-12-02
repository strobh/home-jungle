package se.bth.homejungle.storage.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.Objects;

/**
 * SpeciesWithCategory models the relationship between a species and its category.
 *
 * The class can be used to get the information about a plant species and its category at the same
 * time.
 */
public class SpeciesWithCategory {
    /**
     * The species.
     */
    @Embedded
    public Species species;

    /**
     * The category of the species.
     */
    @Relation(
            parentColumn = "category_id",
            entityColumn = "category_id"
    )
    public SpeciesCategory category;

    public Species getSpecies() {
        return species;
    }

    public SpeciesCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpeciesWithCategory that = (SpeciesWithCategory) o;
        return Objects.equals(species, that.species) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, category);
    }
}
