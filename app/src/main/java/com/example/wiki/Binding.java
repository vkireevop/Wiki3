package com.example.wiki;

import com.google.gson.annotations.SerializedName;

public class Binding {

    @SerializedName("cityLabel")
    private CityLabel cityLabel;

    public CityLabel getCityLabel() {
        return cityLabel;
    }
}


