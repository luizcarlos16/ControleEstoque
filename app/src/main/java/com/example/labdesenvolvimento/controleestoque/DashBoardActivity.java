package com.example.labdesenvolvimento.controleestoque;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

public class DashBoardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
    }

    public void inserir(View v) {
        Intent intent = new Intent(DashBoardActivity.this, InsereActivity.class);
        startActivity(intent);
    }

    public void listar(View v) {
        Intent intent = new Intent(DashBoardActivity.this, ConsultaActivity.class);
        startActivity(intent);
    }
    public void selectMainOpetion(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnListar:
                intent = new Intent(this, ListProdutoActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}