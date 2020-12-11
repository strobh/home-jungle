package se.bth.homejungle.ui.giveaways.add_giveaway;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import se.bth.homejungle.R;
import se.bth.homejungle.ui.giveaways.GiveawaysViewModel;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class AddGiveawayFragment extends Fragment {
    private static final String USERNAME_KEY = "username";
    private static final String CONTACT_KEY = "contact";
    private static final String SPECIESNAME_KEY = "speciesname";
    private static final String USERID = "userid";
    EditText username;
    EditText contact;
    TextView speciesName;
    ImageButton addImageButton;
    Button addButton;
    GiveawaysViewModel giveawaysViewModel;
    View root;
    String speciesNameString;
    String userid;

    ImageView imageView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        giveawaysViewModel = new ViewModelProvider(this).get(GiveawaysViewModel.class);
        speciesNameString = AddGiveawayFragmentArgs.fromBundle(getArguments()).getSpeciesname();

        root = inflater.inflate(R.layout.fragment_add_giveaway, container, false);
        username = root.findViewById(R.id.edittext_username);
        contact = root.findViewById(R.id.edittext_contact);
        speciesName = root.findViewById(R.id.species_name);
        speciesName.setText(speciesNameString);

        addButton = root.findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveData();
                //TODO: create new giveaway with usernameInput, contactInput and Image
            }
        });
        addImageButton = root.findViewById(R.id.add_image_btn);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImage();
            }
        });

        imageView = root.findViewById(R.id.imageView);
        return root;
    }

    private void addImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.add_image_pop_up);
        builder.setItems(R.array.imageSources, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(i){
                    case 0:
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                     //   Intent takePicture = getCameraIntent();
                        startActivityForResult(takePicture , 1);
                        //Source = Take photo
                        break;
                    case 1:
                        //Source = Photo from Gallery
                        Intent pickImage = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickImage , 0);
                        break;
                    case 2:
                        //source = default image
                        //TODO: get default image
                        break;
                }
            }
        });
        builder.show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri image = null;

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) {
                image = imageReturnedIntent.getData();
                imageView.setImageURI(image);
            }
            if (requestCode == 1) {
                //    if (imageReturnedIntent != null) {
                Bitmap captureImage = (Bitmap) imageReturnedIntent.getExtras().get("data");
                imageView.setImageBitmap(captureImage);
                //  image = imageReturnedIntent.getData();
                // imageView.setImageURI(image);
                // imageView.setVisibility(View.VISIBLE);
            }/*
                if (image == null && mCameraFileName != null) {
                    image = Uri.fromFile(new File(mCameraFileName));
                    imageView.setImageURI(image);
                    imageView.setVisibility(View.VISIBLE);
                }
               */
        }
    }

    public void saveData(){
        SharedPreferences sp = getActivity().getSharedPreferences("userdata", MODE_PRIVATE);
        Map<String, Object> userData = new HashMap<String, Object>();

        //for test purpose: if new to app
        //sp.edit().remove("userid").commit();

        if(sp.contains("userid")){
            userid = sp.getString("userid", null);
            save();
        } else {
            db.collection("user")
                    .add(userData)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            userid = documentReference.getId();
                            sp.edit().putString("userid", userid).commit();
                            save();
                        }
                    });
        }
    }

    public void save(){
        Map<String, Object> giveawayData = new HashMap<String, Object>();
        giveawayData.put(USERNAME_KEY, username.getText().toString());
        giveawayData.put(CONTACT_KEY, contact.getText().toString());
        giveawayData.put(SPECIESNAME_KEY, speciesNameString);
        giveawayData.put(USERID, userid);

        // Add a new document with a generated ID
        db.collection("giveaway")
                .add(giveawayData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast toast = Toast.makeText(getContext(), R.string.toast_add_giveaway, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        NavDirections action = AddGiveawayFragmentDirections.giveawayAdded();
                        Navigation.findNavController(root).navigate(action);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

}