package com.joypanchal.rickandmorty.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.joypanchal.rickandmorty.R;
import com.joypanchal.rickandmorty.models.EpisodeCharacter;

import java.io.InputStream;

public class CharacterDetailsActivity extends AppCompatActivity {

    private ImageView imgCharacter;
    private TextView txtCharacterName, txtStatus, txtSpecies, txtType, txtGender, txtCreated;

    private EpisodeCharacter mEpisodeCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detailes);

        imgCharacter = findViewById(R.id.imgDisplayPicture);
        txtCharacterName = findViewById(R.id.txtName);
        txtStatus = findViewById(R.id.txtStatus);
        txtSpecies = findViewById(R.id.txtSpecies);
        txtType = findViewById(R.id.txtType);
        txtGender = findViewById(R.id.txtGender);
        txtCreated = findViewById(R.id.txtCreated);

        mEpisodeCharacter = (EpisodeCharacter) getIntent().getParcelableExtra("character");


        if (mEpisodeCharacter != null) {
            txtCharacterName.setText(mEpisodeCharacter.getName());
            setTitle(mEpisodeCharacter.getName());

            txtStatus.setText(mEpisodeCharacter.getStatus());
            txtSpecies.setText(mEpisodeCharacter.getSpecies());
            txtType.setText(mEpisodeCharacter.getType());
            txtGender.setText(mEpisodeCharacter.getGender());
            txtCreated.setText(mEpisodeCharacter.getCreated());


            new DownloadImageTask(imgCharacter).execute(mEpisodeCharacter.getImage());

        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
