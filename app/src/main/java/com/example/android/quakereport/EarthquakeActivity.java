/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a list of earthquakes from USGS data usando as informações
        //especificadas na QueryUtils.java.
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);


        // Cria um novo adapter que pega a lista de earthquakes como entrada
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Seta o adapter na {@link ListView}
        // para que a lista possa ser populada na interface do usuário
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Achar o terremoto atual que foi clicado
                Earthquake currentEarthquake = adapter.getItem(position);

                // Converte o URL String em um objeto URI (para passar no construtor de Intent)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Cria um novo intent para visualizar a URI do earthquake
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Envia o intent para lançar uma nova activity
                startActivity(websiteIntent);
            }
        });

    }
}
