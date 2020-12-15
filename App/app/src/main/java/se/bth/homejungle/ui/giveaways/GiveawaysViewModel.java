package se.bth.homejungle.ui.giveaways;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import se.bth.homejungle.firestore.MarketplacePlantLiveData;
import se.bth.homejungle.firestore.MarketplacePlantRepository;
import se.bth.homejungle.ui.MarketplacePlant;


public class GiveawaysViewModel extends ViewModel {
    private MarketplacePlantRepository marketplacePlantRepository;
    public MutableLiveData<List<MarketplacePlant>> marketplacePlantsList = new MutableLiveData<List<MarketplacePlant>>();
    MarketplacePlantLiveData liveData = new MarketplacePlantLiveData(null);

    public GiveawaysViewModel(){
        marketplacePlantRepository = new MarketplacePlantRepository();
    }

    public LiveData<List<MarketplacePlant>> getOwnGiveawaysLiveData(String userid){
        liveData = marketplacePlantRepository.getOwnGiveawaysLiveData(userid);
        return liveData;
    }

    public LiveData<List<MarketplacePlant>> getOwnGiveaways() {
        return liveData.marketplacePlantsList;
    }

    public void delete(String docid){
        System.out.println("docid: " + docid);
        marketplacePlantRepository.deleteGiveaway(docid);
    }

    public void insert(MarketplacePlant newItem) {
        marketplacePlantRepository.insert(newItem);
    }
}