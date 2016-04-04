package es.uva.inf.gimnasioubicuo.presentacion;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import es.uva.inf.gimnasioubicuo.R;
import es.uva.inf.gimnasioubicuo.modelo.EjercicioEnRutina;
import es.uva.inf.gimnasioubicuo.modelo.Rutina;

public class ComenzarRutina extends ListActivity {

    private Long  rutinaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // indicamos layout
        setContentView(R.layout.comenzar_rutina);

        // recogemos id de rutina seleccionada
        rutinaId = getIntent().getExtras().getLong(ActividadPrincipal.RUTINA_ID);

        // incializamos botones
        inicializarPanelBotones();
        
        // incializamos lista ejercicios
        inicializarListaEjercicios();
    }

    private void inicializarListaEjercicios() {
        setListAdapter(new ListaEjerciciosAdaptador(this,rutinaId));
    }

    private void inicializarPanelBotones() {

        Button botonComenzarSesion = (Button) findViewById(R.id.button_comenzar_sesion);
        Button botonFinalizarSesion = (Button) findViewById(R.id.button_finalizar_sesion);
        Button botonNuevoEjercicio = (Button) findViewById(R.id.button_nuevo_ejercicio);

        botonComenzarSesion.setEnabled(true);
        botonFinalizarSesion.setEnabled(false);
        botonNuevoEjercicio.setEnabled(false);

    }
}
