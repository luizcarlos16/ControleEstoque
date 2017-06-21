package com.example.labdesenvolvimento.controleestoque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Luiz e Thais
 */

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDado(Estoque estoque){
        ContentValues valores;
        long resultado;


        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME_PRODUTO, estoque.getNome_produto());
        valores.put(CriaBanco.QUANTIDADE, estoque.getQuantidade());
        valores.put(CriaBanco.PRECO_UNITARIO, estoque.getPreco_unitario());


        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME_PRODUTO};
        db = banco.getReadableDatabase();

        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);


        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME_PRODUTO,banco.QUANTIDADE,banco.PRECO_UNITARIO};
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(Estoque estoque){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID + "=" + estoque.get_ID();

        valores = new ContentValues();
        valores.put(CriaBanco.NOME_PRODUTO, estoque.getNome_produto());
        valores.put(CriaBanco.QUANTIDADE, estoque.getQuantidade());
        valores.put(CriaBanco.PRECO_UNITARIO, estoque.getPreco_unitario());


        db.update(CriaBanco.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(CriaBanco.TABELA,where,null);
        db.close();
    }
}