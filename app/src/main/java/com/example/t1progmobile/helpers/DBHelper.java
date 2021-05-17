package com.example.t1progmobile.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.t1progmobile.entities.Pessoa;
import com.example.t1progmobile.entities.Vaga;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "jobs.db";
    private static final String VAGA_TABLE_NAME = "vaga";
    private static final String COLUMM_VAGA_ID = "vagaId";
    private static final String COLUMM_DESCRICAO = "descricao";
    private static final String COLUMM_HORASSEMANA = "horasSemana";
    private static final String COLUMM_REMUNERACAO = "remuneracao";

    private static final String PESSOA_TABLE_NAME = "pessoa";
    private static final String COLUMM_PESSOA_ID = "pessoaId";
    private static final String COLUMM_CANDIDATO_VAGA_ID = "vagaId";
    private static final String COLUMM_NOME = "nome";
    private static final String COLUMM_CPF = "cpf";
    private static final String COLUMM_EMAIL = "email";
    private static final String COLUMM_TELEFONE = "telefone";
    private static final String COLUMM_SENHA = "senha";


    private static final String TABLE_CREATE_VAGA =
            "CREATE TABLE " + VAGA_TABLE_NAME + " ("
            + COLUMM_VAGA_ID + " integer primary key autoincrement,"
            + COLUMM_DESCRICAO + " text not null,"
            + COLUMM_HORASSEMANA + " integer not null,"
            + COLUMM_REMUNERACAO + " real not null"
            + ")";


    private static final String TABLE_CREATE_PESSOA =
            "CREATE TABLE " + PESSOA_TABLE_NAME + " ("
                    + COLUMM_PESSOA_ID + " integer primary key autoincrement,"
                    + COLUMM_CANDIDATO_VAGA_ID + " integer,"
                    + COLUMM_NOME + " text not null,"
                    + COLUMM_CPF + " text not null,"
                    + COLUMM_EMAIL + " text UNIQUE not null,"
                    + COLUMM_TELEFONE + " text not null,"
                    + COLUMM_SENHA + " integer not null,"
                    + "FOREIGN KEY(" + COLUMM_VAGA_ID +") REFERENCES " + VAGA_TABLE_NAME + " (" + COLUMM_CANDIDATO_VAGA_ID + ")"
                    + ")";

    SQLiteDatabase db;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_PESSOA);
        db.execSQL(TABLE_CREATE_VAGA);
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

    public int buscarPessoaIDPorEmail(String email){

        db = this.getReadableDatabase();

        String query = "SELECT "
                + COLUMM_PESSOA_ID
                + " FROM " + PESSOA_TABLE_NAME
                + " WHERE " + COLUMM_EMAIL
                + " LIKE '" + email + "'";

        Cursor cursor = db.rawQuery(query, null);

        int id = -1;

        if( cursor.moveToFirst() ){
            id = cursor.getInt(0);
        }
        return id;

    }

    public int buscarVagaIDPorDescricao(String titulo){

        db = this.getReadableDatabase();

        String query = "SELECT "
                + COLUMM_VAGA_ID
                + " FROM " + VAGA_TABLE_NAME
                + " WHERE " + COLUMM_DESCRICAO
                + " LIKE '" + titulo + "'";

        Cursor cursor = db.rawQuery(query, null);

        int id = -1;

        if( cursor.moveToFirst() ){
            id = cursor.getInt(0);
        }
        return id;

    }

    public String vincularCandidatoVaga(int vagaID, int pessoaID){
        db = this.getWritableDatabase();
        String queryVagasDoCandidato = "SELECT " + COLUMM_CANDIDATO_VAGA_ID + " FROM " + TABLE_CREATE_PESSOA + " WHERE " + COLUMM_PESSOA_ID + " = " + pessoaID;
        Cursor cursor = db.rawQuery(queryVagasDoCandidato, null);
        String resultado = "";
        if (cursor.moveToFirst()){
            resultado = "Já vinculado a uma vaga";
        }else{
            String updateVinculoPessoaVaga = "UPDATE "+ TABLE_CREATE_PESSOA + " SET " + COLUMM_CANDIDATO_VAGA_ID + " = " + vagaID + " WHERE " + COLUMM_PESSOA_ID + " = " + pessoaID;
        }
        return resultado;
    }

    public void cadastrarPessoa(Pessoa pessoa){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMM_NOME, pessoa.getNome());
        values.put(COLUMM_CPF, pessoa.getCpf());
        values.put(COLUMM_EMAIL, pessoa.getEmail());
        values.put(COLUMM_TELEFONE, pessoa.getTelefone());
        values.put(COLUMM_SENHA, pessoa.getSenha());


        db.insert(PESSOA_TABLE_NAME, null, values);
        db.close();
    }

    public boolean buscarEmailCadastrado(String email){
        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + PESSOA_TABLE_NAME + " WHERE "+ COLUMM_EMAIL+ " LIKE '" + email + "'";

        Cursor cursor = db.rawQuery(query, null);

        boolean jaCadastrado = false;

        if(cursor.moveToFirst()){
            jaCadastrado = true;
        }

        return  jaCadastrado;
    }

    public Pessoa buscarPessoaCadastrada(String email, int senha){
        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + PESSOA_TABLE_NAME + " WHERE "+ COLUMM_EMAIL+ " LIKE '" + email + "' AND " + COLUMM_SENHA + " = " + senha;

        Cursor cursor = db.rawQuery(query, null);

        Pessoa pessoa = new Pessoa();

        if(cursor.moveToFirst()){
            String nome = cursor.getString(2);
            String cpf = cursor.getString(3);
            String emailDB = cursor.getString(4);
            String telefone = cursor.getString(5);
            int senhaDB = cursor.getInt(6);

            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setEmail(emailDB);
            pessoa.setTelefone(telefone);
            pessoa.setSenha(senhaDB);
        }

        return pessoa;
    }
}
