package com.example.t1progmobile.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.t1progmobile.entities.Pessoa;

public class PessoaHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "jobs.db";
    private static final String PESSOA_TABLE_NAME = "pessoa";
    private static final String COLUMM_ID = "pessoaId";
    private static final String COLUMM_VAGAID = "vagaId";
    private static final String COLUMM_NOME = "nome";
    private static final String COLUMM_CPF = "cpf";
    private static final String COLUMM_EMAIL = "email";
    private static final String COLUMM_TELEFONE = "telefone";
    private static final String COLUMM_SENHA = "senha";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + PESSOA_TABLE_NAME + " ("
                    + COLUMM_ID + " integer primary key autoincrement,"
                    + COLUMM_VAGAID + " integer,"
                    + COLUMM_NOME + " text not null,"
                    + COLUMM_CPF + " text not null,"
                    + COLUMM_EMAIL + " text not null,"
                    + COLUMM_TELEFONE + " text not null,"
                    + COLUMM_SENHA + " integer not null"
                    + ")";

    SQLiteDatabase db;

    public PessoaHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + PESSOA_TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
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
        String query = "SELECT * FROM " + PESSOA_TABLE_NAME + " WHERE "+ COLUMM_EMAIL+ " LIKE " + email;

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
