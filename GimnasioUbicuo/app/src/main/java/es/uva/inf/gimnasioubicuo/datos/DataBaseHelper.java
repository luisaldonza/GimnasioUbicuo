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
    private MaquinaDao maquinaDao;
    private EjercicioDao ejercicioDao;
    private EjercicioEnRutinaDao ejercicioEnRutinaDao;
    private ResultadoEjercicioDao resultadoEjercicioDao;
    private RutinaDao rutinaDao;
    private SesionDao sesionDao;


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


        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public EjercicioDao getEjercicioDao() throws SQLException {
        if (ejercicioDao == null) {
            ejercicioDao = getDao(Ejercicio.class);
        }
        return ejercicioDao;
    }

    public EjercicioEnRutinaDao getEjercicioEnRutinaDao() throws SQLException {
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


    public ResultadoEjercicioDao getResultadoEjercicioDao() throws SQLException {
        if (resultadoEjercicioDao == null) {
            resultadoEjercicioDao = getDao(ResultadoEjercicio.class);
        }
        return resultadoEjercicioDao;
    }

    public RutinaDao getRutinaDao() throws SQLException {
        if (rutinaDao == null) {
            rutinaDao = getDao(Rutina.class);
        }
        return rutinaDao;
    }

    public SesionDao getSesionDao() throws SQLException {
        if (sesionDao == null) {
            sesionDao = getDao(Sesion.class);
        }
        return sesionDao;
    }




}
