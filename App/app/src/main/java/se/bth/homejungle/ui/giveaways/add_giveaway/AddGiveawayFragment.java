package se.bth.homejungle.ui.giveaways.add_giveaway;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.ui.giveaways.GiveawaysViewModel;
import se.bth.homejungle.ui.location.LocationFragment;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class AddGiveawayFragment extends LocationFragment implements LocationFragment.LocationCallback {
    private static final String USERNAME_KEY = "username";
    private static final String CONTACT_KEY = "contact";
    private static final String SPECIESNAME_KEY = "speciesname";
    private static final String USERID = "userid";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    EditText username;
    EditText contact;
    TextView speciesName;
    ImageButton addImageButton;
    Button addButton;
    GiveawaysViewModel giveawaysViewModel;
    View root;
    String speciesNameString;
    String speciesImageString;
    String userid;

    TextView tv_username;
    TextView tv_contact;

    ProgressBar progressBar;
    TextView errorMessage;

    Location location;

    ImageView imageView;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        giveawaysViewModel = new ViewModelProvider(this).get(GiveawaysViewModel.class);
        speciesNameString = AddGiveawayFragmentArgs.fromBundle(getArguments()).getSpeciesName();
        speciesImageString = AddGiveawayFragmentArgs.fromBundle(getArguments()).getSpeciesImage();
        root = inflater.inflate(R.layout.fragment_add_giveaway, container, false);

        progressBar = root.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);

        errorMessage = root.findViewById(R.id.errorMessage2);
        errorMessage.setVisibility(View.INVISIBLE);

        checkLocationPermission(this);

        username = root.findViewById(R.id.edittext_username);
        contact = root.findViewById(R.id.edittext_contact);
        speciesName = root.findViewById(R.id.species_name);
        speciesName.setText(speciesNameString);
        tv_username = root.findViewById(R.id.tv_username);
        tv_contact = root.findViewById(R.id.tv_contact);

        addButton = root.findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
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
        imageView.setImageURI(AppDatabase.getUriForFileName(speciesImageString));
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
                        imageView.setImageURI(AppDatabase.getUriForFileName(speciesImageString));
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
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Map<String, Object> giveawayData = new HashMap<String, Object>();
        giveawayData.put(USERNAME_KEY, username.getText().toString());
        giveawayData.put(CONTACT_KEY, contact.getText().toString());
        giveawayData.put(SPECIESNAME_KEY, speciesNameString);
        giveawayData.put(USERID, userid);
        giveawayData.put(LATITUDE, latitude);
        giveawayData.put(LONGITUDE, longitude);

        // Add a new document with a generated ID
        db.collection("giveaway")
                .add(giveawayData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        saveImage(documentReference.getId());


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });


    }

    public void saveImage(String docId){
        StorageReference imageRef = storage.getReference().child("images/" + docId + ".jpg");
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos =  new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("Add Giveaway", "Image wasn't added");
            }
        });
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast toast = Toast.makeText(getContext(), R.string.toast_add_giveaway, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                NavDirections action = AddGiveawayFragmentDirections.giveawayAdded();
                Navigation.findNavController(root).navigate(action);
            }
        });
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            requestLocation(true, this);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            errorMessage.setText("Home Jungle needs your location in order to offer the give-away in your neighbourhood. Make sure that Home Jungle can access your location.");
            errorMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLocationResult(LocationResult locationResult, Location location) {
        if (locationResult == LocationResult.SUCCESS) {
            Log.v("AddGiveawayFragment", "Got location");
            Log.v("AddGiveawayFragment", "Lat: " + location.getLatitude());
            Log.v("AddGiveawayFragment", "Lon: " + location.getLongitude());
            this.location = location;
            progressBar.setVisibility(View.INVISIBLE);

            imageView.setVisibility(View.VISIBLE);
            speciesName.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
            contact.setVisibility(View.VISIBLE);
            addImageButton.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.VISIBLE);
            tv_username.setVisibility(View.VISIBLE);
            tv_contact.setVisibility(View.VISIBLE);
        }
        else {
            errorMessage.setText("Home Jungle needs your location in order to find give-aways in your neighbourhood. Make sure that Home Jungle can access your location.");
            errorMessage.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
