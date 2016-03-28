package com.example.axel_.controladordifuso_propensionvictimas;

/**
 * Created by axel_ on 24/03/2016.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class DB_Handler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=4;
    private static final String DATABASE_NAME="enucestas.db";
    //nombre de Tablas y sus atributos
    //tabla encuesta
    private static final String TABLA_ENCUESTA="encuesta";
    private static final String ID_ENCUESTA = "id_encuesta";
    private static  final String NOMBRE_ENCUESTA="nombre_encuesta";
    //tabla respuesta
    private static final String TABLA_RESPUESTAS="respuesta";

    private static final String ID_RESPUESTA="id_respuesta";
    private static final String RESPUESTA="respuesta";

    public DB_Handler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_table_respuesta=
                "CREATE TABLE " + TABLA_RESPUESTAS + " ( " +
                ID_RESPUESTA+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RESPUESTA + " TEXT  NOT NULL, "+
                ID_ENCUESTA + " TEXT NOT NULL, " +
                        " FOREIGN KEY("+ID_ENCUESTA+" ) REFERENCES "+TABLA_ENCUESTA+" ( "+ID_ENCUESTA+" ) "+
                        " ON DELETE CASCADE "+
                        " );";

        String query_table_encuesta = "CREATE TABLE " + TABLA_ENCUESTA + "(" +
                ID_ENCUESTA+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOMBRE_ENCUESTA + " TEXT"+
                ");";
        db.execSQL(query_table_encuesta);
        db.execSQL(query_table_respuesta);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLA_ENCUESTA);
        db.execSQL("DROP TABLE IF EXISTS "+TABLA_RESPUESTAS);
        onCreate(db);
    }

    ///metodos para agregar registros en las tablas

    public void addRespuesta(Respuesta respuesta){
        ContentValues values = new ContentValues();
      //  values.put(ID_RESPUESTA,respuesta.getId_respuesta());
        values.put(RESPUESTA,respuesta.getRespuesta());
        values.put(ID_ENCUESTA,respuesta.getId_Encuesta());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLA_RESPUESTAS,null,values);
        db.close();
    }
    public void addEncuesta(Encuesta encuesta){
        ContentValues values = new ContentValues();
        values.put(NOMBRE_ENCUESTA,encuesta.getName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLA_ENCUESTA, null, values);
        db.close();
    }

    //metodos para eliminar registros de las tablas
    public void deleteEncuesta(int idEncuesta){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM"+TABLA_ENCUESTA+"WHERE"+ID_ENCUESTA+"=\""+
                idEncuesta + "\";");
    }

    //metodo para recuperar respuestas de entrevistas pasadas
    //opcional
    //metoodo para imprimir patalla

    public String dataBaseToString(int idEncuesta){
        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query ="SELECT"+ RESPUESTA + "FROM" +TABLA_RESPUESTAS+
                "WHERE" + ID_ENCUESTA + "=" + idEncuesta;
        //Resultado del query
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
            while (c.getString(c.getColumnIndex(RESPUESTA))!= null){
                dbString += c.getString(c.getColumnIndex(RESPUESTA));
                dbString += "\n";
            }
        return dbString;
    }

}
