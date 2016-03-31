package es.uva.inf.gimnasioubicuo.modelo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Diego on 30/03/2016.
 */
@DatabaseTable(tableName = "sesion")
public class Sesion {

    private static final String RUTINA_ID_FIELD_NAME = "rutina_id";
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private Date fecha;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = RUTINA_ID_FIELD_NAME)
    private Rutina rutina;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<ResultadoEjercicio> resultadosEjercicios;


    public Sesion() {
    }

    public Sesion(Date fecha, Rutina rutina) {
        this.fecha = fecha;
        this.rutina = rutina;
    }



    public Long getId() {
        return id;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public ForeignCollection<ResultadoEjercicio> getResultadosEjercicios() {
        return resultadosEjercicios;
    }
}
