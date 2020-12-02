package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * The SpeciesCategory entity class encapsulates a category for plant species.
 *
 * In order to make the list of plant species easier to understand by the user,
 * we split it up into several categories. For each category we only store its name.
 *
 * The Android Room Persistence Library automatically creates a database table for this entity.
 */
@Entity(tableName = "species_category")
public class SpeciesCategory {

    /**
     * The id of the category.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    public long id;

    /**
     * The name of the category.
     */
    @ColumnInfo(name="name")
    public String name;

    public SpeciesCategory(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpeciesCategory that = (SpeciesCategory) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
