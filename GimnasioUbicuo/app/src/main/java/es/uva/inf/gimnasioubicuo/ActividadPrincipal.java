package es.uva.inf.gimnasioubicuo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
    }

    public void comenzarRutina(View view){
        setContentView(R.layout.comenzar_rutina);
    }

    public void camaraQR (View view){
        setContentView(R.layout.capturar_codigo);
    }

    public void comienzoEjercicio (View view){
        setContentView(R.layout.pantalla_ejercicio);
    }

    public void ejercicioCompleto (View view){
        setContentView(R.layout.comenzar_rutina);
    }

    public void reinicio (View view){
        setContentView(R.layout.actividad_principal);
    }


}
