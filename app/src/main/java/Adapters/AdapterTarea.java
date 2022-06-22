package Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ObjetoTarea;
import com.example.myapplication.R;
import com.example.myapplication.Tareas;

import java.util.List;




public class AdapterTarea extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;
    private List<ObjetoTarea> objetos;

    public AdapterTarea(Context contexto, List<ObjetoTarea> tareas) {
        this.contexto = contexto;
        this.inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objetos = tareas;
    }

    @Override
    public int getCount() {
        return objetos.size();
    }

    @Override
    public Object getItem(int i) {
        return objetos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.item,null);
        TextView tvNombre= (TextView) view.findViewById(R.id.nombreTarea);
        TextView tvId=(TextView) view.findViewById(R.id.idTarea);
        TextView tvDesc= (TextView) view.findViewById(R.id.descTarea);
        tvNombre.setText(objetos.get(i).getNombre());
        tvId.setText(String.valueOf(objetos.get(i).getID()));
        tvDesc.setText(objetos.get(i).getDesc());
        return view;
    }
}