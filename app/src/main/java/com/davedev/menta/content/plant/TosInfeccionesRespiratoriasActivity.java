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

public class TosInfeccionesRespiratoriasActivity extends AppCompatActivity {

    List<ListElementPlant> elementPlants;
    SearchView searchView;
    ListAdapterPlant listAdapterPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exterior_plants);

        searchView = findViewById(R.id.searchViewExterior);
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
        Intent intent = new Intent(TosInfeccionesRespiratoriasActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void init() {

        elementPlants = new ArrayList<>();

        elementPlants.add(new ListElementPlant(
                R.drawable.eucalipto_min,
                "Eucalipto (Eucalyptus globulus)",
                "Planta utilizada para aliviar la tos, congestión nasal y problemas respiratorios.",
                "Necesita sol directo y riego moderado. No tolera exceso de humedad.",
                "5mts - 30mts",
                "40% - 60%",
                "10°C - 25°C",
                "Hervir hojas en agua e inhalar el vapor durante 10 minutos."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.tomillo_min,
                "Tomillo (Thymus vulgaris)",
                "Ayuda a aliviar tos, bronquitis y dolor de garganta.",
                "Prefiere sol directo y suelo seco con buen drenaje.",
                "20cm - 40cm",
                "30% - 50%",
                "15°C - 30°C",
                "Agregar una cucharada en agua caliente, reposar 10 minutos y beber."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.gordolobo_min,
                "Gordolobo (Verbascum thapsus)",
                "Excelente para tratar tos seca y problemas pulmonares.",
                "Crece en lugares soleados y requiere poco riego.",
                "1mt - 2mts",
                "30% - 50%",
                "10°C - 30°C",
                "Hervir flores secas en agua, colar y tomar tibio."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.jengibre_min,
                "Jengibre (Zingiber officinale)",
                "Ayuda contra la gripe, tos y fortalece el sistema inmunológico.",
                "Requiere clima cálido y riego moderado.",
                "60cm - 1mt",
                "50% - 70%",
                "20°C - 30°C",
                "Hervir rodajas en agua por 10 minutos y tomar como té."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.hierbabuena_min,
                "Hierbabuena (Mentha spicata)",
                "Alivia tos, dolor de garganta y congestión.",
                "Prefiere semisombra y riego frecuente.",
                "30cm - 60cm",
                "40% - 70%",
                "15°C - 25°C",
                "Preparar infusión con hojas frescas en agua caliente."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.oregano_min,
                "Orégano (Origanum vulgare)",
                "Ayuda a aliviar infecciones respiratorias y tos.",
                "Necesita sol directo y poco riego.",
                "30cm - 80cm",
                "30% - 50%",
                "18°C - 30°C",
                "Hervir hojas en agua durante 5 minutos y beber tibio."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.malva_min,
                "Malva (Malva sylvestris)",
                "Alivia irritación de garganta y tos seca.",
                "Prefiere sol o semisombra y riego moderado.",
                "60cm - 1mt",
                "40% - 60%",
                "15°C - 25°C",
                "Preparar infusión con flores y hojas."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.ajo_min,
                "Ajo (Allium sativum)",
                "Potente antibacteriano natural para gripe y tos.",
                "Necesita sol directo y suelo bien drenado.",
                "30cm - 60cm",
                "30% - 50%",
                "10°C - 25°C",
                "Machacar un diente y mezclar con miel."
        ));

        listAdapterPlant = new ListAdapterPlant(elementPlants, this, this::moveToDescription);

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapterPlant);
    }

    public void moveToDescription(ListElementPlant item) {
        Intent intent = new Intent(TosInfeccionesRespiratoriasActivity.this, DetailsPlantsActivity.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
        finish();
    }

    public void filterList(String text) {
        List<ListElementPlant> filteredList = new ArrayList<>();

        for (ListElementPlant plant : elementPlants) {
            if (plant.getNamePlant().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(plant);
            }
        }

        if (filteredList.isEmpty()) {
            Log.d("tag", "No info found");
        } else {
            listAdapterPlant.setFilteredList(filteredList);
        }
    }
}