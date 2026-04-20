package com.davedev.menta.content.plant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davedev.menta.R;
import com.davedev.menta.menu.DashboardActivity;
import com.davedev.menta.plantcards.DetailsPlantsActivity;
import com.davedev.menta.plantcards.ListAdapterPlant;
import com.davedev.menta.plantcards.ListElementPlant;

import java.util.ArrayList;
import java.util.List;

public class Dolor_Cabeza extends AppCompatActivity {

    List<ListElementPlant> elementPlants;
    SearchView searchView;
    ListAdapterPlant listAdapterPlant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasonal_plants);

        searchView = findViewById(R.id.searchViewSeasonal);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        init();

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Dolor_Cabeza.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void init(){
        elementPlants = new ArrayList<>();

        elementPlants.add(new ListElementPlant(
                R.drawable.valeriana_min,
                "Valeriana (Valeriana officinalis)",
                "Es una planta medicinal conocida por sus efectos relajantes. Ayuda a reducir el estrés y la tensión, siendo útil para aliviar dolores de cabeza causados por nervios o ansiedad.",
                "Prefiere semisombra y suelos húmedos pero bien drenados. Se recomienda sembrarla en primavera.",
                "50cm - 150cm",
                "50% - 70%",
                "10°C - 20°C",
                "Hervir una taza de agua, agregar 1 cucharadita de raíz seca. Tapar y dejar reposar de 5 a 10 minutos."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.tila_min,
                "Flor de Tila (Tilia spp.)",
                "Es ideal para aliviar dolores de cabeza relacionados con el estrés, además de relajar el sistema nervioso y mejorar el descanso.",
                "Prefiere climas templados, con luz indirecta o semisombra y suelo húmedo.",
                "10m - 30m",
                "60% - 80%",
                "15°C - 25°C",
                "Hervir una taza de agua, agregar 1 cucharadita de flores secas. Tapar y reposar 5 a 10 minutos."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.lavanda_min,
                "Lavanda (Lavandula angustifolia)",
                "Ayuda a aliviar dolores de cabeza y migrañas gracias a su efecto relajante. Su aroma también contribuye a reducir la tensión.",
                "Necesita luz solar directa y suelo bien drenado. Requiere riego moderado.",
                "30cm - 90cm",
                "40% - 60%",
                "15°C - 30°C",
                "Hervir una taza de agua, agregar 1 cucharadita de flores secas o inhalar su aroma para mejores resultados."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.ruda_min,
                "Ruda (Ruta graveolens)",
                "Se utiliza tradicionalmente para aliviar dolores de cabeza, especialmente los causados por tensión o problemas digestivos. Debe usarse con precaución.",
                "Prefiere sol directo y suelos secos con buen drenaje.",
                "50cm - 100cm",
                "30% - 50%",
                "15°C - 30°C",
                "Hervir una taza de agua, agregar pocas hojas. Dejar reposar 5 minutos y consumir con moderación."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.menta_min,
                "Menta (Mentha piperita)",
                "Es muy efectiva para aliviar dolores de cabeza, especialmente al aplicarla o consumirla, ya que proporciona una sensación refrescante y relajante.",
                "Prefiere semisombra y suelo húmedo. Requiere riego constante sin encharcar.",
                "30cm - 60cm",
                "50% - 70%",
                "15°C - 25°C",
                "Hervir una taza de agua, agregar 5 a 7 hojas frescas. Tapar y reposar 5 a 10 minutos."
        ));



        listAdapterPlant = new ListAdapterPlant(elementPlants, this, this::moveToDescription);

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager( this));
        recyclerView.setAdapter(listAdapterPlant);
    }

    public void moveToDescription(ListElementPlant item){
        Intent intent = new Intent(Dolor_Cabeza.this, DetailsPlantsActivity.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
        finish();
    }

    public void filterList(String text) {
        List<ListElementPlant> filteredList = new ArrayList<>();
        for (ListElementPlant listElementPlant : elementPlants){
            if (listElementPlant.getNamePlant().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(listElementPlant);
            }

            if (filteredList.isEmpty()){
                Log.d("tag", "No info found");
            } else {
                listAdapterPlant.setFilteredList(filteredList);
            }
        }
    }

}