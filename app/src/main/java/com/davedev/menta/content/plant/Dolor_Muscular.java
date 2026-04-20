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

public class Dolor_Muscular extends AppCompatActivity {

    List<ListElementPlant> elementPlants;
    SearchView searchView;
    ListAdapterPlant listAdapterPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowers);

        searchView = findViewById(R.id.searchView2);
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
        Intent intent = new Intent(Dolor_Muscular.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void init(){
        elementPlants = new ArrayList<>();

        elementPlants.add(new ListElementPlant(
                R.drawable.romero_min,
                "Romero (Rosmarinus officinalis)",
                "Es una planta medicinal muy utilizada para aliviar dolores musculares, mejorar la circulación y reducir la inflamación. Ideal para masajes y baños relajantes.",
                "Necesita luz solar directa y suelo bien drenado. Requiere poco riego.",
                "50cm - 150cm",
                "30% - 50%",
                "15°C - 30°C",
                "Hervir una taza de agua, agregar 1 cucharadita de hojas secas. También puede usarse en aceite para masajes."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.lavanda_min,
                "Lavanda (Lavandula angustifolia)",
                "Ayuda a relajar los músculos, reducir la tensión y aliviar dolores musculares. También tiene efectos calmantes.",
                "Requiere sol directo y suelo seco con buen drenaje. Riego moderado.",
                "30cm - 90cm",
                "40% - 60%",
                "15°C - 30°C",
                "Hervir una taza de agua, agregar flores secas o usar su aceite para masajes en la zona afectada."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.calendula_min,
                "Caléndula (Calendula officinalis)",
                "Tiene propiedades antiinflamatorias y cicatrizantes. Es útil para aliviar dolores musculares y problemas en la piel.",
                "Prefiere sol directo o semisombra y suelo bien drenado.",
                "30cm - 60cm",
                "40% - 60%",
                "15°C - 25°C",
                "Hervir una taza de agua con flores secas o usar en pomadas para aplicar directamente sobre el área afectada."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.arnica_min,
                "Árnica (Arnica montana)",
                "Es una de las plantas más conocidas para aliviar dolores musculares, golpes e inflamación. Se usa principalmente de forma externa.",
                "Prefiere climas frescos y suelo bien drenado. Necesita luz solar indirecta.",
                "20cm - 60cm",
                "40% - 60%",
                "10°C - 20°C",
                "Aplicar en gel o pomada directamente sobre la zona afectada. No se recomienda su consumo en infusión."
        ));


        listAdapterPlant = new ListAdapterPlant(elementPlants, this, this::moveToDescription);

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager( this));
        recyclerView.setAdapter(listAdapterPlant);
    }

    public void moveToDescription(ListElementPlant item){
        Intent intent = new Intent(Dolor_Muscular.this, DetailsPlantsActivity.class);
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