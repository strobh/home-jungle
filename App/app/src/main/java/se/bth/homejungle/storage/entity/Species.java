package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Species {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="category")
    public String category;

    @ColumnInfo(name="description")
    public String description;

    @ColumnInfo(name="how_to_start")
    public String howToStart;

    @ColumnInfo(name="water")
    public int water;

    @ColumnInfo(name="sun")
    public int sun;
}
