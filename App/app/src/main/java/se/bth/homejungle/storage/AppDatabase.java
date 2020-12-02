package se.bth.homejungle.storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import se.bth.homejungle.storage.dao.CalendarManager;
import se.bth.homejungle.storage.dao.CategoryManager;
import se.bth.homejungle.storage.dao.FuturePlantManager;
import se.bth.homejungle.storage.dao.PlantManager;
import se.bth.homejungle.storage.dao.SpeciesManager;
import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.SpeciesCategory;

@Database(entities = {Plant.class, FuturePlant.class, Species.class, SpeciesCategory.class}, version = 7, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Singleton instance of the database
     */
    private static volatile AppDatabase INSTANCE;

    public abstract PlantManager getPlantManager();
    public abstract FuturePlantManager getFuturePlantManager();
    public abstract SpeciesManager getSpeciesManager();
    public abstract CategoryManager getCategoryManager();
    public abstract CalendarManager getCalendarManager();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "home-jungle")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
