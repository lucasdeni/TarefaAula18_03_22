package com.example.ohata_18_03_22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Application;
import android.location.Location;
import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//Criando classe para rastrear no gps
class GPStacker {
    public GPStacker(Application application) {
    }

    public Location getLocation() {
        return null;
    }

    public class MainActivity extends AppCompatActivity {

        //Criando as variáveis para imagem e botões
        private ImageView imageViewFoto;
        private Button btnGeo;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Variável buscando id do botão xml e solicitando permissão da localização
            btnGeo = (Button) findViewById(R.id.btn_gps);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

            //Clicando no botão
            btnGeo.setOnClickListener(new View.OnClickListener() {

                //GPS buscando localização e utilização de outra classe
                @Override
                public void onClick(View view) {
                    GPStracker g = new GPStracker(getApplication());
                    Location l = g.getLocation();
                }
            });
        }
    }
}