package es.uva.inf.gimnasioubicuo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.uva.inf.gimnasioubicuo.datos.DataBaseHelper;
import es.uva.inf.gimnasioubicuo.modelo.Ejercicio;
import es.uva.inf.gimnasioubicuo.modelo.Maquina;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // prueba e utilización del DAO

        try {

            testOutOrmLiteDatabase();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testOutOrmLiteDatabase() throws SQLException {


        DataBaseHelper databaseHelper = OpenHelperManager.getHelper(this,
                DataBaseHelper.class);

        Dao<Maquina, Long> maquinaDao = databaseHelper.getMaquinaDao();

        Date currDateTime = new Date(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currDateTime);
        calendar.add(Calendar.DATE, 14);

        Date dueDate = calendar.getTime();

        maquinaDao.create(new Maquina("Poleas1", "12aasdae234"));
        maquinaDao.create(new Maquina( "Poleas2", "12aasdae235"));

        List<Maquina> maquinas = maquinaDao.queryForAll();

        Log.w("Maquinas", Arrays.toString(maquinas.toArray()));

        // creamos los ejercicicios
        Dao<Ejercicio, Long> ejercicioDao = databaseHelper.getEjercicioDao();
        ejercicioDao.create(new Ejercicio("Press banca",maquinaDao.queryForId(new Long(1))));

        List<Ejercicio> ejercicios = ejercicioDao.queryForAll();

        Log.w("Ejercicios", Arrays.toString(ejercicios.toArray()));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
