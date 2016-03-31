package es.uva.inf.gimnasioubicuo.modelo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Diego on 30/03/2016.
 */
@DatabaseTable(tableName = "rutina")
public class Rutina {


    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String nombre;

    @DatabaseField
    private String descripcion;


    @ForeignCollectionField(eager = true)
    private ForeignCollection<EjercicioEnRutina> ejerciciosEnRutina;


    public Rutina() {
    }

    public Rutina(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public ForeignCollection<EjercicioEnRutina> getEjerciciosEnRutina() {
        return ejerciciosEnRutina;
    }

    public void setEjerciciosEnRutina(ForeignCollection<EjercicioEnRutina> ejerciciosEnRutina) {
        this.ejerciciosEnRutina = ejerciciosEnRutina;
    }
}



