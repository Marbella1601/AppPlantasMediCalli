package com.davedev.menta.onboarding;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.davedev.menta.R;
import com.davedev.menta.menu.DashboardActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    MaterialButton btnNext, btnStart;
    int position = 0;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        tabIndicator = findViewById(R.id.tab_indicator);
        btnNext = findViewById(R.id.btn_next);
        btnStart = findViewById(R.id.btn_start);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.up_move);
        btnStart.setVisibility(View.GONE);

        List<ScreenItem> mList = new ArrayList<>();

        mList.add(new ScreenItem(
                "Aprende a cuidar plantas medicinales",
                "Las plantas medicinales requieren cuidados especiales. Te enseñamos cómo regarlas, dónde colocarlas y cómo mantenerlas saludables para aprovechar todos sus beneficios.",
                R.raw.plants
        ));

        mList.add(new ScreenItem(
                "Descubre plantas para tu salud",
                "¿No sabes qué planta medicinal usar? Aquí encontrarás opciones según tus necesidades, como plantas para la tos, el estrés o problemas digestivos.",
                R.raw.plant_potted
        ));

        mList.add(new ScreenItem(
                "Conoce sus propiedades y beneficios",
                "Cada planta medicinal tiene propiedades únicas. Aprende para qué sirve, cómo usarla correctamente y qué beneficios puede aportar a tu bienestar.",
                R.raw.phone_plant
        ));
        screenPager = findViewById(R.id.screenPager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);

        btnNext.setOnClickListener(view -> {
            position = screenPager.getCurrentItem();
            if (position < mList.size()){
                position++;
                screenPager.setCurrentItem(position);
            }
            if (position == mList.size()-1){
                loadLastScreen();
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(IntroActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void loadLastScreen(){

        btnNext.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
        //btnStart.setAnimation(btnAnim);

    }
}