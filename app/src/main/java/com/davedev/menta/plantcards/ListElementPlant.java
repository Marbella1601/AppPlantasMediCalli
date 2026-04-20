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

import java.io.Serializable;

public class ListElementPlant implements Serializable {
    public int imagePlant;
    public String namePlant;
    public String descriptionPlant;
    public String caresPlant;
    public String heightPlant;
    public String humidityPlant;
    public String temperaturePlant;
    public String modoPreparacionPlant;


    public ListElementPlant(int imagePlant, String namePlant, String descriptionPlant, String caresPlant, String heightPlant, String humidityPlant, String temperaturePlant,String modoPreparacionPlant) {
        this.imagePlant = imagePlant;
        this.namePlant = namePlant;
        this.descriptionPlant = descriptionPlant;
        this.caresPlant = caresPlant;
        this.heightPlant = heightPlant;
        this.humidityPlant = humidityPlant;
        this.temperaturePlant = temperaturePlant;
        this.modoPreparacionPlant = modoPreparacionPlant;
    }

    public void setImagePlant(int imagePlant) {
        this.imagePlant = imagePlant;
    }

    public void setNamePlant(String namePlant) {
        this.namePlant = namePlant;
    }

    public void setDescriptionPlant(String descriptionPlant) { this.descriptionPlant = descriptionPlant; }

    public void setCaresPlant(String caresPlant) {
        this.caresPlant = caresPlant;
    }

    public void setHeightPlant(String heightPlant) {
        this.heightPlant = heightPlant;
    }

    public void setHumidityPlant(String humidityPlant) {
        this.humidityPlant = humidityPlant;
    }

    public void setTemperaturePlant(String temperaturePlant) { this.temperaturePlant = temperaturePlant; }

    public void setModoPreparacionPlant(String modoPreparacionPlant) { this.modoPreparacionPlant = modoPreparacionPlant; }

    public int getImagePlant() {return  imagePlant;}

    public String getNamePlant() {
        return namePlant;
    }

    public String getDescriptionPlant() {
        return descriptionPlant;
    }

    public String getCaresPlant() {
        return caresPlant;
    }

    public String getHeightPlant() {
        return heightPlant;
    }

    public String getHumidityPlant() {
        return humidityPlant;
    }

    public String getTemperaturePlant() {
        return temperaturePlant;
    }

    public String getModoPreparacionPlant() {
        return modoPreparacionPlant;
    }


}