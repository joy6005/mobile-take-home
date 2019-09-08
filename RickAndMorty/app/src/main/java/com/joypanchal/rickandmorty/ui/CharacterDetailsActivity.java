package com.joypanchal.rickandmorty.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.joypanchal.rickandmorty.R;
import com.joypanchal.rickandmorty.models.EpisodeCharacter;

public class CharacterDetailsActivity extends AppCompatActivity {

    private ImageView imgCharacter;
    private TextView txtCharacterName;

    private EpisodeCharacter mEpisodeCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detailes);

        imgCharacter = findViewById(R.id.imgDisplayPicture);
        txtCharacterName = findViewById(R.id.txtName);

        mEpisodeCharacter = (EpisodeCharacter) getIntent().getParcelableExtra("character");

        if (mEpisodeCharacter != null) {
            txtCharacterName.setText(mEpisodeCharacter.getName());
            setTitle(mEpisodeCharacter.getName());

        }
    }


}
