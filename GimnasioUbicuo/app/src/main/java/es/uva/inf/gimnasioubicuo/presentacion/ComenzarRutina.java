package es.uva.inf.gimnasioubicuo.presentacion;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Calendar;

import es.uva.inf.gimnasioubicuo.R;
import es.uva.inf.gimnasioubicuo.datos.DataBaseHelper;
import es.uva.inf.gimnasioubicuo.modelo.Rutina;
import es.uva.inf.gimnasioubicuo.modelo.Sesion;

public class ComenzarRutina extends ListActivity {

    private Long rutinaId;

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
        setListAdapter(new ListaEjerciciosAdaptador(this, rutinaId));
    }

    public void onClickIniciarRutina(View view) {
        System.out.println("iniciarRutina");

        // crear objeto nueva sesion con la fecha de hoy. // TODO sacar a capa de negocio
        DataBaseHelper databaseHelper = OpenHelperManager.getHelper(this,
                DataBaseHelper.class);


        try {
            Dao<Sesion, Long> sesionDao = databaseHelper.getSesionDao();
            Dao<Rutina, Long> rutinaDao = databaseHelper.getRutinaDao();

            Sesion sesion = new Sesion();

            sesion.setFecha(Calendar.getInstance().getTime());
            sesion.setRutina(rutinaDao.queryForId(rutinaId));
            sesionDao.create(sesion);
            sesionDao.refresh(sesion);

            // TODO refactor de esta clase y de este metodo


        } catch (SQLException e) {
            e.printStackTrace();
        }


        // desactivar boton iniciar rutina y activar botones

        Button botonComenzarSesion = (Button) findViewById(R.id.button_comenzar_sesion);
        Button botonFinalizarSesion = (Button) findViewById(R.id.button_finalizar_sesion);
        Button botonNuevoEjercicio = (Button) findViewById(R.id.button_nuevo_ejercicio);

        botonComenzarSesion.setEnabled(false);
        botonFinalizarSesion.setEnabled(true);
        botonNuevoEjercicio.setEnabled(true);

    }

    public void onClickIniciarEjercicio(View view) {
        System.out.println("iniciarEjercicio");


        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);

        } catch (Exception e) {

            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            startActivity(marketIntent);

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");

                Log.w("SCAN_RESULT:",contents);
            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
            }
        }
    }

    public void onClickFinalizarRutina(View view) {
        System.out.println("finalizarRutina");
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
