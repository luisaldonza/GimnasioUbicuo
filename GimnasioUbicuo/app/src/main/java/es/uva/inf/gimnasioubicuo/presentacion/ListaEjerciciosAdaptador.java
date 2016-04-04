package es.uva.inf.gimnasioubicuo.presentacion;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uva.inf.gimnasioubicuo.R;
import es.uva.inf.gimnasioubicuo.datos.DataBaseHelper;
import es.uva.inf.gimnasioubicuo.modelo.EjercicioEnRutina;
import es.uva.inf.gimnasioubicuo.modelo.Rutina;

/**
 * Created by Diego on 02/04/2016.
 */
public class ListaEjerciciosAdaptador extends BaseAdapter {

    private Activity activity;
    private Long rutinaId;
    private List<EjercicioEnRutina> listaEjercicioEnRutina;

    public ListaEjerciciosAdaptador(Activity activity, Long rutinaId) {
        this.activity = activity;
        this.rutinaId = rutinaId;

        // obtenemos los ejercicios que tiene esta rutina.
        listaEjercicioEnRutina = getEjerciciosEnRutina();
    }

    @Override
    public int getCount() {
        return listaEjercicioEnRutina.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEjercicioEnRutina.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaEjercicioEnRutina.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.ejercicio_element_list, null, true);

        // nombre de la rutina
        TextView nombreEjerTextView = (TextView) view.findViewById(R.id.textView_nombre_ejercicio_comenzar_rutina);
        nombreEjerTextView.setText(listaEjercicioEnRutina.get(position).getEjercicio().getNombre());

        // maquina de la rutina
        TextView descripcionEjerTextView = (TextView) view.findViewById(R.id.textview_nombreMaquina);
        descripcionEjerTextView.setText(listaEjercicioEnRutina.get(position).getEjercicio().getMaquina().getNombre());

        // repeticiones
        TextView repeticionesEjerTextView = (TextView) view.findViewById(R.id.text_view_numero_repeticiones);
        repeticionesEjerTextView.setText(String.valueOf(listaEjercicioEnRutina.get(position).getRepeticiones()));

        //peso
        TextView pesoEjerTextView = (TextView) view.findViewById(R.id.textview_peso);
        pesoEjerTextView.setText(String.valueOf(listaEjercicioEnRutina.get(position).getPeso()));


        return view;
    }

    private List<EjercicioEnRutina> getEjerciciosEnRutina() {


        List<EjercicioEnRutina> listEjercicioEnRutina = null;

        DataBaseHelper databaseHelper = OpenHelperManager.getHelper(activity,
                DataBaseHelper.class);
        Dao<Rutina, Long> rutinaDao = null;
        try {
            rutinaDao = databaseHelper.getRutinaDao();


            Rutina rutina = rutinaDao.queryForId(rutinaId);
            ForeignCollection<EjercicioEnRutina> ejerciciosEnRutina = rutina.getEjerciciosEnRutina();

            EjercicioEnRutina[] arrayEjercicioEnRutina = new EjercicioEnRutina[ejerciciosEnRutina.size()];

            arrayEjercicioEnRutina = ejerciciosEnRutina.toArray(arrayEjercicioEnRutina);
            listEjercicioEnRutina = new ArrayList<EjercicioEnRutina>();
            listEjercicioEnRutina.addAll(ejerciciosEnRutina);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEjercicioEnRutina;
    }
}
