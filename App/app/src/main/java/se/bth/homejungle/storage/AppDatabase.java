package se.bth.homejungle.storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import se.bth.homejungle.storage.dao.FuturePlantManager;
import se.bth.homejungle.storage.dao.PlantManager;
import se.bth.homejungle.storage.dao.SpeciesManager;
import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.Species;

@Database(entities = {Plant.class, FuturePlant.class, Species.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract PlantManager getPlantManager();
    public abstract FuturePlantManager getFuturePlantManager();
    public abstract SpeciesManager getSpeciesManager();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "home_jungle")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
