package co.edu.unal.tutorship.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.unal.tutorship.R;

public class ControllerListaGeneral extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    String[][] datos;
    int[] datosImg;

    public ControllerListaGeneral(Context context, String[][] datos, int[] datosImg) {
        this.context = context;
        this.datos = datos;
        this.datosImg = datosImg;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        TextView titulo = (TextView) vista.findViewById(R.id.TvNombreTutoria);
        TextView tutor = (TextView) vista.findViewById(R.id.TvTutor);
        TextView carrera = (TextView) vista.findViewById(R.id.TvCarrera);

        ImageView imagen = (ImageView) vista.findViewById(R.id.RelatedImage);

        titulo.setText(datos[i][0]);
        tutor.setText(datos[i][1]);
        carrera.setText("Carrera: " + datos[i][2]);
        imagen.setImageResource(datosImg[i]);

        /*
        imagen.setTag(i);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visorImagen = new Intent(context, VisorImagen.class);
                visorImagen.putExtra("IMG", datosImg[(Integer)v.getTag()]);
                context.startActivity(visorImagen);
            }
        });
        */
        return vista;
    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
