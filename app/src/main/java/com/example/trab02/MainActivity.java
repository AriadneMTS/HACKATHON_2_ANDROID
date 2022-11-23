package com.example.trab02;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.example.trab02.datasources.BuscaDados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ListActivity {

    private String id = "1";
    private Integer idCount;
    private ArrayList<HashMap<String, String>> listaDados;
    Button nextPage, prevPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextPage = findViewById(R.id.nextButton);
        prevPage = findViewById(R.id.prevButton);

        Intent dadosRecebidos = getIntent();

        if(dadosRecebidos != null) {
            Bundle params = dadosRecebidos.getExtras();

            if(params != null) {
                id = params.getString("id");
            }
        }

        try {
            listaDados = new BuscaDados().execute(Config.urlApi + id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                this, listaDados, R.layout.listview_modelo, new String[] {"name"}, new int[] {R.id.txtNome}
        );

        setListAdapter(adapter);

        nextPage.setOnClickListener(new View.OnClickListener () {
            public void onClick(View v) {

                Intent telaMain = new Intent(MainActivity.this, MainActivity.class);

                Bundle params = new Bundle();

                idCount = Integer.parseInt(id);

                idCount++;

                params.putString("id", Integer.toString(idCount));

                telaMain.putExtras(params);
                startActivity(telaMain);
            }
        });

        prevPage.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent telaMain = new Intent(MainActivity.this, MainActivity.class);

                Bundle params = new Bundle();

                idCount = Integer.parseInt(id);

                idCount--;

                if(idCount <= 0) {
                    idCount = 1;
                }

                params.putString("id", Integer.toString(idCount));

                telaMain.putExtras(params);
                startActivity(telaMain);

            }
        });

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