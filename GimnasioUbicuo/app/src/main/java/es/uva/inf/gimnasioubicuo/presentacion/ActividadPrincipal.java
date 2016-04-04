package es.uva.inf.gimnasioubicuo.presentacion;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uva.inf.gimnasioubicuo.R;
import es.uva.inf.gimnasioubicuo.datos.DataBaseHelper;
import es.uva.inf.gimnasioubicuo.modelo.Rutina;
import es.uva.inf.gimnasioubicuo.presentacion.ListaRutinasAdaptador;

public class ActividadPrincipal extends ListActivity {

    public static final String RUTINA_ID = "rutina_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);


        try {

            inicializarListView();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void inicializarListView() throws SQLException {

        // TODO hay que crear las tres capas de la aplicacion

        // TODO Lo ideal sería ir recuperando datos según se vayan necesitando, por ello se podría crear un servicio de negocio encargado de ello.

        List<Rutina> listaRutinas = getRutinasDefinidas();
        setListAdapter(new ListaRutinasAdaptador(this,listaRutinas));

     }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Rutina rutinaSeleccionada = (Rutina) getListAdapter().getItem(position);

        // iniciar rutina, cambio de actividad.
        Intent intent = new Intent(this,ComenzarRutina.class);
        intent.putExtra(RUTINA_ID,rutinaSeleccionada.getId());
        startActivity(intent);

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



    private List<Rutina> getRutinasDefinidas() throws SQLException {


        DataBaseHelper databaseHelper = OpenHelperManager.getHelper(this,
                DataBaseHelper.class);
        Dao<Rutina,Long> rutinaDao = databaseHelper.getRutinaDao();

        List<Rutina> rutinas = rutinaDao.queryForAll();
        return rutinas;
    }
}
