package com.davedev.menta.menu;

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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.davedev.menta.R;
import com.davedev.menta.content.plant.TosInfeccionesRespiratoriasActivity;
import com.davedev.menta.content.plant.Dolor_Muscular;
import com.davedev.menta.content.plant.Dolor_EstomacalActivity;
import com.davedev.menta.content.plant.Dolor_Cabeza;
import com.davedev.menta.content.blog.BlogExteriorPlantsActivity;
import com.davedev.menta.content.blog.BlogFlowersActivity;
import com.davedev.menta.content.blog.BlogDolorEstomacalActivity;
import com.davedev.menta.content.blog.BlogSeasonalPlantsActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashboardActivity extends AppCompatActivity {
    Button btnInterior, btnExterior, btnSeason, btnFlower, btnBlogInterior, btnBlogExterior, btnBlogSeason, btnBlogFlower;
    FloatingActionButton fabLogo;
    TextView titleTextView;
    ScrollView scrollCards;
    MaterialCardView interiorPlants, exteriorPlants, seasonPlants, cardFlowers;
    Animation downMove, upMove, fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnBlogInterior = findViewById(R.id.btnBlogInterior);
        btnBlogExterior = findViewById(R.id.btnBlogExterior);
        btnBlogSeason = findViewById(R.id.btnBlogSeason);
        btnBlogFlower = findViewById(R.id.btnBlogFlower);
        btnInterior = findViewById(R.id.btnInterior);
        btnExterior = findViewById(R.id.btnExterior);
        btnSeason = findViewById(R.id.btnSeason);
        btnFlower = findViewById(R.id.btnFlower);
        fabLogo = findViewById(R.id.fabLogo);
        titleTextView = findViewById(R.id.titleTextView);
        scrollCards = findViewById(R.id.scrollCards);
        interiorPlants = findViewById(R.id.cardInterior);
        exteriorPlants = findViewById(R.id.cardExterior);
        seasonPlants = findViewById(R.id.cardSeason);
        cardFlowers = findViewById(R.id.cardFlowers);

        downMove = AnimationUtils.loadAnimation(DashboardActivity.this, R.anim.down_move);
        upMove = AnimationUtils.loadAnimation(DashboardActivity.this, R.anim.up_move);
        fadeIn = AnimationUtils.loadAnimation(DashboardActivity.this, R.anim.fade_in);

        fabLogo.setAnimation(upMove);
        titleTextView.setAnimation(upMove);
        scrollCards.setAnimation(fadeIn);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        fabLogo.setOnClickListener(v -> {
            if (isDarkModeOn) {
                // if dark mode is on it
                // will turn it off
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                // it will set isDarkModeOn
                // boolean to false
                editor.putBoolean("isDarkModeOn", false);
                editor.apply();
            }
            else {
                // if dark mode is off
                // it will turn it on
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                // it will set isDarkModeOn
                // boolean to true
                editor.putBoolean("isDarkModeOn", true);
                editor.apply();
            }
        });

        btnInterior.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, Dolor_EstomacalActivity.class);
            startActivity(intent);
            finish();
        });


        btnBlogInterior.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, BlogDolorEstomacalActivity.class);
            startActivity(intent);
            finish();
        });

        btnExterior.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, TosInfeccionesRespiratoriasActivity.class);
            startActivity(intent);
            finish();
        });

        btnBlogExterior.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, BlogExteriorPlantsActivity.class);
            startActivity(intent);
            finish();
        });

        btnSeason.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, Dolor_Cabeza.class);
            startActivity(intent);
            finish();
        });

        btnBlogSeason.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, BlogSeasonalPlantsActivity.class);
            startActivity(intent);
            finish();
        });

        btnFlower.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, Dolor_Muscular.class);
            startActivity(intent);
            finish();
        });

        btnBlogFlower.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, BlogFlowersActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}