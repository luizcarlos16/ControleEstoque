package com.example.labdesenvolvimento.controleestoque;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AlterarActivity extends Activity {

    EditText estoque;
    EditText quantidade;
    EditText preco_unitario;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        estoque = (EditText)findViewById(R.id.editText4);
        quantidade = (EditText)findViewById(R.id.editText5);
        preco_unitario = (EditText)findViewById(R.id.editText6);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        estoque.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME_PRODUTO)));
        quantidade.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.QUANTIDADE)));
        preco_unitario.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.PRECO_UNITARIO)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estoque estoqueObj = new Estoque();
                estoqueObj.set_ID(Integer.parseInt(codigo));
                estoqueObj.setNome_produto(estoque.getText().toString());
                estoqueObj.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
                estoqueObj.setPreco_unitario(Integer.parseInt(preco_unitario.getText().toString()));
                crud.alteraRegistro(estoqueObj);
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}