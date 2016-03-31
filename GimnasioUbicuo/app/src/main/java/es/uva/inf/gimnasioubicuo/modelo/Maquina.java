package es.uva.inf.gimnasioubicuo.modelo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Diego on 29/03/2016.
 */
@DatabaseTable(tableName = "maquina")
public class Maquina {
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String nombre;

    @DatabaseField(columnName = "codigo_qr")
    private String codigoQR;

    public Long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public Maquina( String nombre, String codigoQR) {
        this.nombre = nombre;
        this.codigoQR = codigoQR;
    }

    public Maquina() {
    }
}
