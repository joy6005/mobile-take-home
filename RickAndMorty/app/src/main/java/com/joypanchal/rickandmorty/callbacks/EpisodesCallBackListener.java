package com.joypanchal.rickandmorty.callbacks;

import com.joypanchal.rickandmorty.models.Episode;

import java.util.List;

public interface EpisodesCallBackListener {
    public void onSuccess(List<Episode> episodes);

    public void onError(String error);
}
