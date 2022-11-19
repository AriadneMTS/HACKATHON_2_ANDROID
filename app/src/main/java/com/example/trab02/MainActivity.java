package com.example.trab02;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.example.trab02.datasources.BuscaDados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ListActivity {

    private ArrayList<HashMap<String, String>> listaDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listaDados = new BuscaDados().execute(Config.urlApi).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String texto = listaDados.get(0).get("name");
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();

        ListAdapter adapter = new SimpleAdapter(
                this, listaDados, R.layout.listview_modelo, new String[] {"name"}, new int[] {R.id.txtNome}
        );

        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        HashMap<String, String> image = listaDados.get(position);

        Intent telaDetalhes = new Intent(MainActivity.this, DetalhesActivity.class);

        Bundle params = new Bundle();
        params.putString("name", image.get("name"));
        params.putString("image", image.get("image"));
        params.putString("status", image.get("status"));
        params.putString("gender", image.get("gender"));
        params.putString("species", image.get("species"));
        telaDetalhes.putExtras(params);
        startActivity(telaDetalhes);
    }
}