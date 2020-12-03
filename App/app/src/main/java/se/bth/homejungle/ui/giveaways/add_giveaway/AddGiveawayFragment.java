package se.bth.homejungle.ui.giveaways.add_giveaway;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import se.bth.homejungle.R;
import se.bth.homejungle.ui.giveaways.GiveawaysViewModel;

public class AddGiveawayFragment extends Fragment {
    EditText username;
    EditText contact;
    ImageButton addImageButton;
    Button addButton;
    GiveawaysViewModel giveawaysViewModel;

    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        giveawaysViewModel = new ViewModelProvider(this).get(GiveawaysViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_giveaway, container, false);
        username = root.findViewById(R.id.edittext_username);
        contact = root.findViewById(R.id.edittext_contact);
        addButton = root.findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameInput = username.getText().toString();
                String contactInput = contact.getText().toString();
                //TODO: create new giveaway with usernameInput, contactInput and Image
                //TODO: call insert on giveawaysviewmodel
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

}