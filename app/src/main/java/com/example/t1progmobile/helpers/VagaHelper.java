package com.example.t1progmobile.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;

import com.example.t1progmobile.entities.Vaga;

import java.util.ArrayList;

public class VagaHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "jobs.db";
    private static final String VAGA_TABLE_NAME = "vaga";
    private static final String COLUMM_ID = "vagaId";
    private static final String COLUMM_DESCRICAO = "descricao";
    private static final String COLUMM_HORASSEMANA = "horasSemana";
    private static final String COLUMM_REMUNERACAO = "remuneracao";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + VAGA_TABLE_NAME + " ("
            + COLUMM_ID + " integer primary key autoincrement,"
            + COLUMM_DESCRICAO + " text not null,"
            + COLUMM_HORASSEMANA + " integer not null,"
            + COLUMM_REMUNERACAO + " real not null"
            + ")";

    SQLiteDatabase db;

    public VagaHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + VAGA_TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void criarEmprego(Vaga vaga){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMM_DESCRICAO, vaga.getDescricao());
        values.put(COLUMM_HORASSEMANA, vaga.getHorasSemana());
        values.put(COLUMM_REMUNERACAO, vaga.getValor());

        db.insert(VAGA_TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Vaga> consultarTodasVagas(){
        db = this.getReadableDatabase();
        String query = "SELECT "
                + COLUMM_DESCRICAO + ","
                + COLUMM_HORASSEMANA + ","
                + COLUMM_REMUNERACAO
                + " FROM "+ VAGA_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Vaga> vagasCadastradas = new ArrayList<>();

        if( cursor.moveToFirst() ){
            do{

                String auxDescricaoCursor = cursor.getString(0);
                int auxHorasCursor = Integer.parseInt(cursor.getString(1));
                double auxRemuneracaoCursor = Double.parseDouble(cursor.getString(2));
                Vaga vagaAux = new Vaga(auxDescricaoCursor, auxHorasCursor, auxRemuneracaoCursor);
                vagasCadastradas.add(vagaAux);

            } while (cursor.moveToNext());
        }
         return vagasCadastradas;
    }
}
