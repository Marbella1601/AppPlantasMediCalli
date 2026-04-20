package com.davedev.menta.plantcards;

/*
 * Nombre del Proyecto: Menta
 * Desarrollador: David Butrón
 * Perfil de Github: https://github.com/DaveDeveloper117/
 * Diseñador UI/UX: Valam Matías https://github.com/OmniSk8
 * Diseñador UI/UX: Marco Malagon https://github.com/SpartanTerra69
 * Licencia: https://github.com/DaveDeveloper117/Menta/blob/master/LICENSE
 * URL del Repositorio: https://github.com/DaveDeveloper117/Menta.git
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.davedev.menta.R;
import com.davedev.menta.content.plant.Dolor_EstomacalActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailsPlantsActivity extends AppCompatActivity {
    TextView descNamePlant, descInfoPlant, descCarePlant, descInfoHeight, descInfoHumidity, descInfoTemperature, titleHeight, titleHumidity, titleTemperature;
    ImageView descImagePlant;
    LinearLayout linearLayoutFabs;
    Animation downMove, upMove, leftMove, rightMove;
    FloatingActionButton fabHeight, fabHumidity, fabTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_plants);

        ListElementPlant elementPlant = (ListElementPlant) getIntent().getSerializableExtra("ListElement");

        fabHeight = findViewById(R.id.fabHeight);
        fabHumidity = findViewById(R.id.fabHumidity);
        fabTemperature = findViewById(R.id.fabTemperature);
        titleHeight = findViewById(R.id.titleHeight);
        titleHumidity = findViewById(R.id.titleHumidity);
        titleTemperature = findViewById(R.id.titleTemperature);
        descNamePlant = findViewById(R.id.descNamePlant);
        descInfoPlant = findViewById(R.id.descInfoPlant);
        descCarePlant = findViewById(R.id.descCarePlant);
        descInfoHeight = findViewById(R.id.descInfoHeight);
        descInfoHumidity = findViewById(R.id.descInfoHumidity);
        descInfoTemperature = findViewById(R.id.descInfoTemperature);
        descImagePlant = findViewById(R.id.descImagePlant);
        linearLayoutFabs = findViewById(R.id.linearLayoutFabs);

        downMove = AnimationUtils.loadAnimation(DetailsPlantsActivity.this, R.anim.down_move);
        upMove = AnimationUtils.loadAnimation(DetailsPlantsActivity.this, R.anim.up_move);
        leftMove = AnimationUtils.loadAnimation(DetailsPlantsActivity.this, R.anim.left_move);
        rightMove = AnimationUtils.loadAnimation(DetailsPlantsActivity.this, R.anim.right_move);

        descNamePlant.setAnimation(upMove);
        descImagePlant.setAnimation(upMove);
        descInfoPlant.setAnimation(leftMove);
        descCarePlant.setAnimation(rightMove);
        linearLayoutFabs.setAnimation(upMove);

        descNamePlant.setText(elementPlant.getNamePlant());
        descInfoPlant.setText(elementPlant.getDescriptionPlant());
        descCarePlant.setText(elementPlant.getCaresPlant());
        descInfoHeight.setText(elementPlant.getHeightPlant());
        descInfoHumidity.setText(elementPlant.getHumidityPlant());
        descInfoTemperature.setText(elementPlant.getTemperaturePlant());
        descImagePlant.setImageResource(elementPlant.getImagePlant());

        fabHeight.setOnClickListener(v -> {
            titleHeight.setVisibility(View.VISIBLE);
            descInfoHeight.setVisibility(View.VISIBLE);
        });

        fabHumidity.setOnClickListener(v -> {
            titleHumidity.setVisibility(View.VISIBLE);
            descInfoHumidity.setVisibility(View.VISIBLE);
        });

        fabTemperature.setOnClickListener(v -> {
            titleTemperature.setVisibility(View.VISIBLE);
            descInfoTemperature.setVisibility(View.VISIBLE);
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetailsPlantsActivity.this, Dolor_EstomacalActivity.class);
        startActivity(intent);
        finish();
    }
}