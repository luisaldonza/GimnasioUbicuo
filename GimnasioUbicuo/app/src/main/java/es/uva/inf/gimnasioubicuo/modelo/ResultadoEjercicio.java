package es.uva.inf.gimnasioubicuo.modelo;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by Diego on 30/03/2016.
 */
public class ResultadoEjercicio {



    private static final String SESION_ID_FIELD_NAME = "sesion_id";
    private static final String EJERCICIO_EN_RTUNA_ID_FIELD_NAME = "ejercicio_en_rutina_id";
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(columnName = "repeticiones_realizadas")
    private int repeticionesRealizadas;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = SESION_ID_FIELD_NAME)
    private Sesion sesion;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = EJERCICIO_EN_RTUNA_ID_FIELD_NAME)
    private EjercicioEnRutina ejercicioEnRutina;


    public ResultadoEjercicio() {
    }

    public ResultadoEjercicio(int repeticionesRealizadas, Sesion sesion, EjercicioEnRutina ejercicioEnRutina) {
        this.repeticionesRealizadas = repeticionesRealizadas;
        this.sesion = sesion;
        this.ejercicioEnRutina = ejercicioEnRutina;
    }
}
