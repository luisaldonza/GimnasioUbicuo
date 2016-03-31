package es.uva.inf.gimnasioubicuo.modelo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Diego on 30/03/2016.
 */

@DatabaseTable(tableName = "ejercicio")
public class Ejercicio {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String nombre;

    @DatabaseField(foreign = true)
    private Maquina maquina;


    public Ejercicio() {
    }


    public Ejercicio( String nombre, Maquina maquina) {
        this.id = id;
        this.nombre = nombre;
        this.maquina = maquina;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }
}
