package es.uva.inf.gimnasioubicuo.datos;

import android.content.Context;
import java.sql.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.logger.Log;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import es.uva.inf.gimnasioubicuo.R;
import es.uva.inf.gimnasioubicuo.modelo.Ejercicio;
import es.uva.inf.gimnasioubicuo.modelo.EjercicioEnRutina;
import es.uva.inf.gimnasioubicuo.modelo.Maquina;
import es.uva.inf.gimnasioubicuo.modelo.ResultadoEjercicio;
import es.uva.inf.gimnasioubicuo.modelo.Rutina;
import es.uva.inf.gimnasioubicuo.modelo.Sesion;

/**
 * Created by Diego on 29/03/2016.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "gimnasioUbicuo.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * The data access object used to interact with the Sqlite database to do C.R.U.D operations.
     */
    private Dao<Maquina,Long> maquinaDao;
    private Dao<Ejercicio,Long> ejercicioDao;
    private Dao<EjercicioEnRutina,Long> ejercicioEnRutinaDao;
    private Dao<ResultadoEjercicio,Long> resultadoEjercicioDao;
    private Dao<Rutina,Long> rutinaDao;
    private Dao<Sesion,Long> sesionDao;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,R.raw.ormlite_config);

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            // creamos la bd gimnasioUbicuo
            TableUtils.createTable(connectionSource, Maquina.class);
            TableUtils.createTable(connectionSource, Ejercicio.class);
            TableUtils.createTable(connectionSource, Rutina.class);
            TableUtils.createTable(connectionSource, EjercicioEnRutina.class);
            TableUtils.createTable(connectionSource, ResultadoEjercicio.class);
            TableUtils.createTable(connectionSource, Sesion.class);

            // carga inicial de datos
            cargarDatosInicialesBD();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Realiza la carga inicial de los datos de la app
     * @throws SQLException
     */
    private void cargarDatosInicialesBD() throws SQLException {

        // creamos máquinas
        getMaquinaDao().create(new Maquina("Banco de press banca","codigoQr1")); //TODO extraer estos strings.
        getMaquinaDao().create(new Maquina("Mancuernas","codigoQrMancuernas"));
        getMaquinaDao().create(new Maquina("Polea alta","codigoQrPoleaAlta"));

         // creamos ejercicios
        getEjercicioDao().create(new Ejercicio("Press de banca",getMaquinaDao().queryForId(new Long(1))));
        getEjercicioDao().create(new Ejercicio("Concentradas",getMaquinaDao().queryForId(new Long(2))));
        getEjercicioDao().create(new Ejercicio("Jalones al pecho",getMaquinaDao().queryForId(new Long(3))));

        // creamos rutina
        getRutinaDao().create(new Rutina("Rutina de volumen","Rutina de volumen"));

        // creamos ejercicios en rutina
        getEjercicioEnRutinaDao().create(
                new EjercicioEnRutina(60,15,getEjercicioDao().queryForId(new Long(1)),getRutinaDao().queryForId(new Long(1))));
        getEjercicioEnRutinaDao().create(
                new EjercicioEnRutina(15,20,getEjercicioDao().queryForId(new Long(2)),getRutinaDao().queryForId(new Long(2))));
        getEjercicioEnRutinaDao().create(
                new EjercicioEnRutina(55,15,getEjercicioDao().queryForId(new Long(3)),getRutinaDao().queryForId(new Long(3))));

    }


    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            /**
             * Recreates the database when onUpgrade is called by the framework
             */
            TableUtils.dropTable(connectionSource, Maquina.class, false);
            TableUtils.dropTable(connectionSource, Ejercicio.class, false);
            TableUtils.dropTable(connectionSource, Rutina.class, false);
            TableUtils.dropTable(connectionSource, EjercicioEnRutina.class, false);
            TableUtils.dropTable(connectionSource, ResultadoEjercicio.class, false);
            TableUtils.dropTable(connectionSource, Sesion.class, false);

            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Ejercicio,Long> getEjercicioDao() throws SQLException {
        if (ejercicioDao == null) {
            ejercicioDao = getDao(Ejercicio.class);
        }
        return ejercicioDao;
    }

    public Dao<EjercicioEnRutina,Long> getEjercicioEnRutinaDao() throws SQLException {
        if (ejercicioEnRutinaDao == null) {
            ejercicioEnRutinaDao = getDao(EjercicioEnRutina.class);
        }
        return ejercicioEnRutinaDao;
    }


    public Dao<Maquina, Long> getMaquinaDao() throws SQLException {
        if (maquinaDao == null) {
            maquinaDao = getDao(Maquina.class);
        }
        return maquinaDao;
    }


    public Dao<ResultadoEjercicio,Long> getResultadoEjercicioDao() throws SQLException {
        if (resultadoEjercicioDao == null) {
            resultadoEjercicioDao = getDao(ResultadoEjercicio.class);
        }
        return resultadoEjercicioDao;
    }

    public Dao<Rutina,Long> getRutinaDao() throws SQLException {
        if (rutinaDao == null) {
            rutinaDao = getDao(Rutina.class);
        }
        return rutinaDao;
    }

    public Dao<Sesion, Long> getSesionDao() throws SQLException {
        if (sesionDao == null) {
            sesionDao = getDao(Sesion.class);
        }
        return sesionDao;
    }




}
