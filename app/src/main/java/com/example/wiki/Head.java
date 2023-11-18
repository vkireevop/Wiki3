package com.example.wiki;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Head {

    @SerializedName("vars")
    private List<String> vars;

    public List<String> getVars() {
        return vars;
    }
}
