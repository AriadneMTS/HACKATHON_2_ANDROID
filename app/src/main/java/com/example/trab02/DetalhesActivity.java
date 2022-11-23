package com.example.trab02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trab02.datasources.BaixarImagem;

public class DetalhesActivity extends AppCompatActivity {

    TextView name, status, gender, species;
    ImageView image;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detalhes);

        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        gender = findViewById(R.id.gender);
        species = findViewById(R.id.species);
        image = findViewById(R.id.image);

        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos != null) {
            Bundle params = dadosRecebidos.getExtras();
            if (params != null) {
                name.setText(params.getString("name"));
                status.setText(params.getString("status"));
                gender.setText(params.getString("gender"));
                species.setText(params.getString("species"));
                new BaixarImagem(image).execute(params.getString("image"));
            }
        }
    }
}
