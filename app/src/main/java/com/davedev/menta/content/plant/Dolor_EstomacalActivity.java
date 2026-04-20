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

public class Dolor_EstomacalActivity extends AppCompatActivity {

    List<ListElementPlant> elementPlants;
    SearchView searchView;
    ListAdapterPlant listAdapterPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interior_plants);

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
        Intent intent = new Intent(Dolor_EstomacalActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void init() {

        elementPlants = new ArrayList<>();

        elementPlants.add(new ListElementPlant(
                R.drawable.manzanilla_min,
                "Manzanilla (Matricaria chamomilla)",
                "Es una planta medicinal ideal para aliviar malestares estomacales, cólicos menstruales, ansiedad, insomnio y pequeñas irritaciones oculares. Su uso es común en infusiones y aplicaciones externas gracias a sus efectos suaves y naturales, siendo apta para personas de todas las edades.",
                "Necesita luz natural sin sol directo. Durante la germinación debe mantenerse en semisombra. Se puede cultivar en interior todo el año y en exterior es ideal sembrarla en primavera. Se recomienda sembrar de 8 a 10 semillas.",
                "30cm - 60cm",
                "40% - 60%",
                "18°C - 22°C",
                "Hervir una taza de agua, agregar una o dos cucharaditas de flores secas de manzanilla, tapar y dejar reposar entre 5 y 10 minutos. Colar antes de tomar."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.oregano_min,"Orégano (Origanum vulgare)",
                "Es una planta medicinal y culinaria con propiedades antioxidantes, antibacterianas y antiinflamatorias. Es útil para aliviar problemas respiratorios leves, dolores menstruales y digestión pesada.",
                "Necesita luz solar directa o abundante luz natural. Requiere riego moderado y suelo bien drenado.",
                "30cm - 80cm",
                "40% - 60%",
                "18°C - 25°C",
                "Hervir una taza de agua, agregar 1 cucharadita de hojas secas. Tapar y dejar reposar de 5 a 10 minutos."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.diente_leon_min,
                "Diente de León (Taraxacum officinale)",
                "Ayuda a mejorar la digestión, favorece la desintoxicación del hígado y la eliminación de líquidos retenidos.",
                "Prefiere tierra suelta y bien drenada. Necesita sol directo o semisombra.",
                "20cm - 40cm",
                "40% - 60%",
                "10°C - 25°C",
                "Hervir una taza de agua y verter sobre 1 cucharadita de hojas secas. Reposar 5 a 10 minutos."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.hierbabuena_min,
                "Hierbabuena (Mentha spicata)",
                "Ayuda a aliviar malestares estomacales, gases, náuseas y dolores de cabeza.",
                "Prefiere luz indirecta o sombra parcial. Necesita tierra húmeda y buen drenaje.",
                "30cm - 60cm",
                "50% - 70%",
                "15°C - 25°C",
                "Hervir una taza de agua y agregar 5 a 7 hojas frescas. Reposar 5 a 10 minutos."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.ruda_min,
                "Ruda (Ruta graveolens)",
                "Utilizada para aliviar dolor de estómago, gastritis y cólicos. No debe consumirse durante el embarazo.",
                "Prefiere luz solar indirecta. Requiere riego moderado y suelo con buen drenaje.",
                "50cm - 100cm",
                "30% - 50%",
                "15°C - 30°C",
                "Agregar 5 hojas a 1 litro de agua hirviendo, reposar y colar antes de beber."
        ));

        elementPlants.add(new ListElementPlant(
                R.drawable.albahaca_min,
                "Albahaca (Ocimum basilicum)",
                "Ayuda a aliviar gases, indigestión y náuseas. También tiene propiedades antibacterianas.",
                "Prefiere clima cálido y sol directo al menos 6 horas al día.",
                "30cm - 60cm",
                "40% - 60%",
                "20°C - 30°C",
                "Hervir una taza de agua, añadir 10 hojas frescas y dejar reposar 5 a 7 minutos."
        ));

        listAdapterPlant = new ListAdapterPlant(elementPlants, this, this::moveToDescription);

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapterPlant);
    }

    public void moveToDescription(ListElementPlant item) {
        Intent intent = new Intent(Dolor_EstomacalActivity.this, DetailsPlantsActivity.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
    }

    public void filterList(String text) {

        List<ListElementPlant> filteredList = new ArrayList<>();

        for (ListElementPlant plant : elementPlants) {
            if (plant.getNamePlant().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(plant);
            }
        }

        if (filteredList.isEmpty()) {
            Log.d("Search", "No se encontró información");
        } else {
            listAdapterPlant.setFilteredList(filteredList);
        }
    }
}