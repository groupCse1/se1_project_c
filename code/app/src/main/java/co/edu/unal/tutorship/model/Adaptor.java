package co.edu.unal.tutorship.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.unal.tutorship.R;

public class Adaptor extends BaseAdapter{

    private static LayoutInflater inflater=null;

    Context contexto;
    String[][] datos;
    int[] datosImg;

    public Adaptor(Context contexto, String[][] datos, int[] datosImg) {
        this.contexto = contexto;
        this.datos = datos;
        this.datosImg = datosImg;
        inflater=(LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final View vista = inflater.inflate(R.layout.elemento_lista_estudiantes, null);

        TextView nombre = (TextView) vista.findViewById(R.id.tvNombre);
        TextView carrera = (TextView) vista.findViewById(R.id.tvCarrera);
        TextView correo = (TextView) vista.findViewById(R.id.tvCorreo);
        TextView numero = (TextView) vista.findViewById(R.id.tvNumero);

        ImageView imagen = (ImageView) vista.findViewById(R.id.ivImagen);

        nombre.setText(datos[i][0]);
        carrera.setText(datos[i][1]);
        correo.setText(datos[i][2]);
        numero.setText(datos[i][3]);

        imagen.setImageResource(datosImg[i]);


        return vista;
    }


    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
