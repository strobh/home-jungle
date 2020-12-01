package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.CategoryManager;
import se.bth.homejungle.storage.entity.SpeciesCategory;

public class CategoryRepository {

    private CategoryManager categoryManager;

    public CategoryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.categoryManager = db.getCategoryManager();
    }

    public LiveData<List<SpeciesCategory>> getCategories() {
        return categoryManager.getCategories();
    }

    public LiveData<SpeciesCategory> findById(long id) {
        return categoryManager.findById(id);
    }

    public void insert(SpeciesCategory speciesCategory) {
        AppDatabase.databaseWriteExecutor.execute(() -> categoryManager.insert(speciesCategory));
    }

    public void delete(SpeciesCategory speciesCategory) {
        AppDatabase.databaseWriteExecutor.execute(() -> categoryManager.delete(speciesCategory));
    }
}
