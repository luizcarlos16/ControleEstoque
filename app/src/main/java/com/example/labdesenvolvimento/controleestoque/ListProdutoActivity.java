package com.example.labdesenvolvimento.controleestoque;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by LT on 15/06/2017.
 */
public class ListProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadClubes();
        }
        public void loadClubes(){
            if(isConnected())
                new DownloadFromMyAPI().execute();
            else
                Toast.makeText(this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
        }

        private boolean isConnected(){
            ConnectivityManager cm =
                    (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }

        private class DownloadFromMyAPI extends AsyncTask<Void, Void, String> {
            ProgressDialog progress;
            @Override
            protected void onPreExecute(){

                progress = new ProgressDialog(ListProdutoActivity.this);
                progress.setMessage("Aguarde o Download dos Dados");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setProgress(0);
                progress.show();

            }

            @Override
            protected String doInBackground(Void... params) {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL("http://maisaula.net.br/selectAll.php");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setReadTimeout(10000);
                    urlConnection.setConnectTimeout(15000);
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);

                    int test = urlConnection.getResponseCode();

                    String result = Util.webToString(urlConnection.getInputStream());

                    return result;
                } catch (Exception e) {
                    Log.e("Error", "Error ", e);
                    return null;
                } finally{
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                List<Clube> clubes = Util.convertJSONtoClube(s);
                if(clubes != null){
                    ArrayAdapter<Clube> clubeAdapter = new ClubeAdapter(ListProdutoActivity.this,R.layout.clube_item,clubes);
                    ListView listaClube = (ListView) findViewById(R.id.listClubes);
                    listaClube.setAdapter(clubeAdapter);
                }
                progress.dismiss();
            }
        }
}
