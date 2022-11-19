package com.example.trab02.datasources;

import android.os.AsyncTask;

import com.example.trab02.Config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;


public class BuscaDados extends
        AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {

    @Override
    protected  ArrayList<HashMap<String, String>> doInBackground(String... string) {
        ArrayList<HashMap<String, String>> listaDados = new ArrayList<>();

        try {
            String link = string[0];

            URL url = new URL(link);

            URLConnection connection = url.openConnection();

            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());

            BufferedReader reader = new BufferedReader(inputStream);

            String dados = "";
            String linha;

            while ((linha = reader.readLine()) != null) {
                dados += linha;
            }

            JSONObject json = new JSONObject(dados);

            JSONArray lista = new JSONArray(json.getString("results"));

            for (int i=0; i< lista.length(); i++) {
                JSONObject item = (JSONObject)lista.get(i);
                HashMap<String, String> mapaDados = new HashMap<>();
                mapaDados.put("name", item.getString("name"));
                mapaDados.put("image",  item.getString("image"));
                mapaDados.put("status", item.getString("status"));
                mapaDados.put("gender",  item.getString("gender"));
                mapaDados.put("species",  item.getString("species"));

                listaDados.add(mapaDados);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDados;
    }
}
