package se.bth.homejungle.storage.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import se.bth.homejungle.storage.converter.DateConverter;

@Entity(tableName="future_plant")
@TypeConverters(DateConverter.class)
public class FuturePlant {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="description")
    public String description;

    @ColumnInfo(name="plant_day")
    public Date plantDay;

    public FuturePlant(String name, String description, Date plantDay) {
        this.name = name;
        this.description = description;
        this.plantDay = plantDay;
    }
}
