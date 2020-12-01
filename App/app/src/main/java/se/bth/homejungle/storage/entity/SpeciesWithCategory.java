package se.bth.homejungle.storage.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.Objects;

public class SpeciesWithCategory {
    @Embedded
    public Species species;

    @Relation(
            parentColumn = "category_id",
            entityColumn = "id"
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
