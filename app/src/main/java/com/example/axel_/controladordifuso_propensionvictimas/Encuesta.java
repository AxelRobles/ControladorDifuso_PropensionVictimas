package com.example.axel_.controladordifuso_propensionvictimas;

/**
 * Created by axel_ on 24/03/2016.
 */
public class Encuesta {
    private static int idCont=0;
    private int id_encuesta;
    private String name;
    public Encuesta() {
        idCont+=1;
       // id_encuesta=idCont;
    }
    public int getId_encuesta() {
        return id_encuesta;
    }
    public void setId_encuesta(int id_encuesta) {
        this.id_encuesta = id_encuesta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
