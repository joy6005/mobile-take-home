package com.joypanchal.rickandmorty.callbacks;

import com.joypanchal.rickandmorty.models.EpisodeCharacter;

import java.util.List;

public interface CharactersCallBackListener {
    public void onSuccess(List<EpisodeCharacter> characterList);

    public void onError(String error);
}
