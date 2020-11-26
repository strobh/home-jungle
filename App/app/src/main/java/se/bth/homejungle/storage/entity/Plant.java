package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import se.bth.homejungle.storage.converter.DateConverter;

@Entity
@TypeConverters(DateConverter.class)
public class Plant {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="description")
    public String description;

    @ColumnInfo(name="last_watered")
    public Date lastWatered;

    public Plant(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
