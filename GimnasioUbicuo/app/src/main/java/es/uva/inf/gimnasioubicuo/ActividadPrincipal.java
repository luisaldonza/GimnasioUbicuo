package es.uva.inf.gimnasioubicuo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import es.uva.inf.gimnasioubicuo.datos.DataBaseHelper;
import es.uva.inf.gimnasioubicuo.modelo.Rutina;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        // prueba e utilización del DAO

        try {

            testOutOrmLiteDatabase();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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



    private void testOutOrmLiteDatabase() throws SQLException {


        DataBaseHelper databaseHelper = OpenHelperManager.getHelper(this,
                DataBaseHelper.class);
        Dao<Rutina,Long> rutinaDao = databaseHelper.getRutinaDao();

        List<Rutina> rutinas = rutinaDao.queryForAll();


        Log.w("Desc:", rutinas.get(0).getDescripcion());
        Log.w("Repes:", String.valueOf(rutinas.get(0).getEjerciciosEnRutina().iterator().next().getRepeticiones()));






    }
}
