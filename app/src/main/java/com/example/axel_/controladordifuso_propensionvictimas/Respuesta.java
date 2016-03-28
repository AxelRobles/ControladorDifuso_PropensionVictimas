package com.example.axel_.controladordifuso_propensionvictimas;

/**
 * Created by axel_ on 24/03/2016.
 */
public class Respuesta {
    private static int cont_id=0;
    private int id_respuesta;
    private String respuesta;
    private int id_Encuesta;

    public Respuesta() {
    }

    public Respuesta(int id_Encuesta, int id_respuesta, String respuesta) {
        cont_id +=1;
        this.id_Encuesta = id_Encuesta ;
       // this.id_respuesta = id_respuesta;
        this.respuesta = respuesta;
    }

    public int getId_respuesta() {
        return id_respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setId_respuesta(int id_respuesta) {
        this.id_respuesta = id_respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getId_Encuesta() {
        return id_Encuesta;
    }

    public void setId_Encuesta(int id_Encuesta) {
        this.id_Encuesta = id_Encuesta;
    }

}
