
package com.joypanchal.rickandmorty.models;

import java.util.List;

public class AllRickAndMorty {

    private Info info;
    private List<Episode> episodes = null;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

}
