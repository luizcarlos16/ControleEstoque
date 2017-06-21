package com.example.labdesenvolvimento.controleestoque;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "estoque";
    public static final String ID = "_id";
    public static final String NOME_PRODUTO = "nome_produto";
    public static final String QUANTIDADE = "quantidade";
    public static final String PRECO_UNITARIO = "preco_unitario";
    public static final int VERSAO = 2;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NOME_PRODUTO + " text,"
                + QUANTIDADE + " integer,"
                + PRECO_UNITARIO + " integer"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}