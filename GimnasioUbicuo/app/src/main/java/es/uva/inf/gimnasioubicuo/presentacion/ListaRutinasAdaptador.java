package es.uva.inf.gimnasioubicuo.presentacion;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import es.uva.inf.gimnasioubicuo.R;
import es.uva.inf.gimnasioubicuo.modelo.Rutina;

/**
 * Created by Diego on 02/04/2016.
 */
public class ListaRutinasAdaptador extends BaseAdapter
{

    private Activity activity;
    private List<Rutina> listaRutinas;

    public ListaRutinasAdaptador(Activity activity, List<Rutina> listaRutinas) {
        this.activity = activity;
        this.listaRutinas = listaRutinas;
    }

    @Override
    public int getCount() {
        return listaRutinas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaRutinas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaRutinas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.rutina_element_list,null,true);

        // nombre de la rutina
        TextView nombreRutinaTextView = (TextView) view.findViewById(R.id.textView_nombre_ejercicio_comenzar_rutina);
        nombreRutinaTextView.setText(listaRutinas.get(position).getNombre());

        // descripcion de la rutina
        TextView descripcionRutinaTextView = (TextView) view.findViewById(R.id.textView16);
        descripcionRutinaTextView.setText(listaRutinas.get(position).getDescripcion());

        return view;
    }
}
