package se.bth.homejungle.ui.database.categories;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.SpeciesCategory;
import se.bth.homejungle.storage.entity.SpeciesWithCategory;
import se.bth.homejungle.storage.repository.CategoryRepository;
import se.bth.homejungle.storage.repository.SpeciesRepository;

public class DatabaseViewModel extends AndroidViewModel {

    private final CategoryRepository categoryRepository;
    private final LiveData<List<SpeciesCategory>> speciesCategories;

    public DatabaseViewModel(Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
        speciesCategories = categoryRepository.getCategories();
    }

    public LiveData<List<SpeciesCategory>> getSpeciesCategories() {
        return speciesCategories;
    }

}