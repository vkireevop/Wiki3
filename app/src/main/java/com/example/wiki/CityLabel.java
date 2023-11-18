package com.example.wiki;

import com.google.gson.annotations.SerializedName;

public class CityLabel {

    @SerializedName("xml:lang")
    private String lang;

    @SerializedName("type")
    private String type;

    @SerializedName("value")
    private String value;

    public String getLang() {
        return lang;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}

