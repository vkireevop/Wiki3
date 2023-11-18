package com.example.wiki;

import com.google.gson.annotations.SerializedName;

public class QueryResult {

    @SerializedName("head")
    private Head head;

    @SerializedName("results")
    private Results results;

    public Head getHead() {
        return head;
    }

    public Results getResults() {
        return results;
    }
}


