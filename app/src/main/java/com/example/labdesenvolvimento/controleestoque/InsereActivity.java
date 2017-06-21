package com.example.labdesenvolvimento.controleestoque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsereActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere);

        Button botao = (Button)findViewById(R.id.button);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText nome_produto = (EditText)findViewById(R.id.editText);
                EditText quantidade = (EditText)findViewById((R.id.editText2));
                EditText preco_unitario = (EditText)findViewById(R.id.editText3);
                String nome_produtoString = nome_produto.getText().toString();
                int quantidadeInt = Integer.parseInt(quantidade.getText().toString());
                int preco_unitarioInt =  Integer.parseInt(preco_unitario.getText().toString());
                String resultado;

                Estoque estoque = new Estoque(nome_produtoString,quantidadeInt,preco_unitarioInt);

                resultado = crud.insereDado(estoque);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(InsereActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
