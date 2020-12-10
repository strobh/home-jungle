package se.bth.homejungle.firestore;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import se.bth.homejungle.ui.MarketplacePlant;

public class MarketplacePlantRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private MarketplacePlant currentPlant;

    public MarketplacePlantLiveData getFirestoreLiveData() {
        CollectionReference collectionReference = db.collection("user");

        return new MarketplacePlantLiveData(collectionReference);
    }
/*public LiveData<MarketplacePlant> getSinglePlant(String docName){
        DocumentReference documentReference = db.collection("user").document(docName);


        db.collection("user").document(docName)
    }

    public void setPlantByDocName(String docName) {
        DocumentReference documentReference = db.collection("user").document(docName);

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        currentPlant = document.toObject(MarketplacePlant.class);
                    }
                }
            }
        });
    }

    public MarketplacePlant getPlantByDocName(){
        return currentPlant;
    }*/
}
