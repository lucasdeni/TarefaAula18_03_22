package com.example.ohata_18_03_22;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {
   Context context;

   //
    public GPStracker(Context c){
        context = c;
    }

    //Busca localização com a permissão
    public Location getLocation(){
        //Se a permissão for negada, retorne vazio
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
        //Texto de permissão negada
            Toast.makeText(context, "Não foi permitir", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //Se o gps ta habilitado, solicita a localização
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
        //Texto de gps ligado
            Toast.makeText(context, "Por favor, habitar o GPS!", Toast.LENGTH_LONG).show();
        }
        return null;
    }
    //Método para o provedor desativado
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    //Método caso mude a lozalização
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    //
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }
}

