/*
 * Author : pritesh.patel
 * Copyright (c) 2019. All right reserved.
 */

package com.joypanchal.rickandmorty.models;

/**
 * Created by pritesh.patel on 2019-09-04, 14:47.
 * Toronto, Canada
 */

import android.os.Parcel;
import android.os.Parcelable;

public class EpisodeCharacter implements Parcelable {

    public final static Creator<EpisodeCharacter> CREATOR = new Creator<EpisodeCharacter>() {


        @SuppressWarnings({
                "unchecked"
        })
        public EpisodeCharacter createFromParcel(Parcel in) {
            return new EpisodeCharacter(in);
        }

        public EpisodeCharacter[] newArray(int size) {
            return (new EpisodeCharacter[size]);
        }

    };
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;
    private String created;

    protected EpisodeCharacter(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.species = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.created = ((String) in.readValue((String.class.getClassLoader())));
    }

    public EpisodeCharacter() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(status);
        dest.writeValue(species);
        dest.writeValue(type);
        dest.writeValue(gender);
        dest.writeValue(image);
        dest.writeValue(url);
        dest.writeValue(created);
    }

}
