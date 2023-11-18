package com.example.wiki;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Results {

    @SerializedName("bindings")
    private List<Binding> bindings;

    public List<Binding> getBindings() {
        return bindings;
    }
}

