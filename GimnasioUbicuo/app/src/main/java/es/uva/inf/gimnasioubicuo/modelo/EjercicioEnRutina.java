package es.uva.inf.gimnasioubicuo.modelo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Diego on 30/03/2016.
 */
@DatabaseTable(tableName = "ejercicio_en_rutina")
public class EjercicioEnRutina {


    private static final String RUTINA_ID_FIELD_NAME = "rutina_id";
    private static final String EJERCICIO_ID_FIELD_NAME = "ejercicio_id";
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private int peso;

    @DatabaseField
    private int repeticiones;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = EJERCICIO_ID_FIELD_NAME)
    private Ejercicio ejercicio;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = RUTINA_ID_FIELD_NAME)
    private Rutina rutina;


    public EjercicioEnRutina() {
    }


    public EjercicioEnRutina(int peso, int repeticiones, Ejercicio ejercicio, Rutina rutina) {
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.ejercicio = ejercicio;
        this.rutina = rutina;
    }

    public EjercicioEnRutina(Ejercicio ejercicio, int peso, int repeticiones) {
        this.ejercicio = ejercicio;
        this.peso = peso;
        this.repeticiones = repeticiones;
    }

    public Long getId() {
        return id;
    }


    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }
}

