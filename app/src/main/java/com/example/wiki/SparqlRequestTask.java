package com.example.wiki;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SparqlRequestTask extends AsyncTask<Void, Void, QueryResult> {

    private static final String TAG = "SparqlRequestTask";

    private final String sparqlEndpoint;
    private final Listener listener;

    public interface Listener {
        void onQueryResult(QueryResult result);
    }

    public SparqlRequestTask(String sparqlEndpoint, Listener listener) {
        this.sparqlEndpoint = sparqlEndpoint;
        this.listener = listener;
    }

    @Override
    protected QueryResult doInBackground(Void... voids) {
        try {
            URL url = new URL(sparqlEndpoint);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line).append('\n');
            }

            reader.close();

            Gson gson = new Gson();
            return gson.fromJson(response.toString(), QueryResult.class);
        } catch (Exception e) {
            Log.e(TAG, "Error during SPARQL request", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(QueryResult result) {
        if (listener != null) {
            listener.onQueryResult(result);
        }
    }
}
