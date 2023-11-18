package com.example.wiki;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SparqlRequestTask.Listener {

    private static final String SPARQL_ENDPOINT = "https://query.wikidata.org/sparql?query=SELECT%20%3FcityLabel%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20WHERE%20%7B%20%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Fcity%20wdt%3AP31%2Fwdt%3AP279*%20wd%3AQ515.%20%20%20%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%3Fcity%20wdt%3AP17%20wd%3AQ159.%20%20%20%20%20%20%20%20%20%20%20%20%20%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20SERVICE%20wikibase%3Alabel%20%7B%20bd%3AserviceParam%20wikibase%3Alanguage%22%5BAUTO_LANGUAGE%5D%2Cru%22.%20%7D%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%7D%20%0A%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20ORDER%20BY%20%3FcityLabel&format=json";

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        Button queryButton = findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SparqlRequestTask(SPARQL_ENDPOINT, MainActivity.this).execute();
            }
        });
    }

    @Override
    public void onQueryResult(QueryResult result) {
        if (result != null && result.getResults() != null && result.getResults().getBindings() != null
                && !result.getResults().getBindings().isEmpty()) {

            StringBuilder cityLabels = new StringBuilder();

            for (Binding binding : result.getResults().getBindings()) {
                if (binding.getCityLabel() != null) {
                    String cityLabel = binding.getCityLabel().getValue();
                    cityLabels.append(cityLabel).append("\n");
                }
            }

            if (cityLabels.length() > 0) {
                resultTextView.setText(cityLabels.toString());
            } else {
                resultTextView.setText("Невозможно получить данные из ответа");
            }
        } else {
            resultTextView.setText("Ошибка при выполнении SPARQL-запроса");
        }
    }

}
