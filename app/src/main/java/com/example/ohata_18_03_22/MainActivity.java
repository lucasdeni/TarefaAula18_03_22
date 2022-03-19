package com.example.ohata_18_03_22;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.Manifest;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        //Criando as variáveis para imagem e botões
        private ImageView imageViewFoto;
        private Button btnGeo;

        //Vizualiza o app
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

                    //Com l vazia, ele pegará os dados de lat e lon e exibirá para o usuário
                    if(l != null) {
                        double lat = l.getLatitude();
                        double lon = l.getLongitude();
                        Toast.makeText(getApplicationContext(), "LATITUDE: " + lat +"\n LONGITUDE: " + lon, Toast.LENGTH_LONG).show();
                    }
                }
            });
            //Superclasse checa a permissão para a utilização da câmera
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 0);
            }

            //Guarda a foto ao tirar ela
            imageViewFoto = (ImageView) findViewById(R.id.Foto);
            findViewById(R.id.btn_ft).setOnClickListener(new View.OnClickListener() {
                //Novo método sendo utilizado
                @Override
                public void onClick(View view) {
                    tirarFoto();
                }
            });
        }

        //Permissão para armazenar a foto e exibir ela pro usuário
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    //Criando método de SuperClasse
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Ao tirar uma foto com sucesso, mostre ela
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}