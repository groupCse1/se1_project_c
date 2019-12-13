package co.edu.unal.tutorship.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unal.tutorship.R;
import co.edu.unal.tutorship.model.ControllerListaGeneral;

public class ActivityListToGive extends AppCompatActivity {

    ListView lista;
    String[][] datos = {
            {"Ingenieria de Software","Juan David Lopez","Ing.de Sistemas","En esta sesión resolveré dudas sobre métodologias de diseño de software."},
            {"Cálculo diferencial","Estefania Lara Diaz","Facultad Ciencias Exactas","Estaré disponible a cualquier duda sobre el curso."},
            {"Estructuras de datos","Jose Miguel Gomez","Ing. Sistemas y Mecatrónica","Esta tutoria sera especializada en temas de arboles, traer sus dudas previamente."},
            {"Señales y Sistemas","Juana Rodriguez Rodriguez","Facultad ingenieria","En esta sesión resolvere todas sus dudas, por favor traiganlas previamente."},
    };
    int[] datosImg = {R.drawable.brain,R.drawable.notepad,R.drawable.thinking,R.drawable.physics};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_general);

        lista = (ListView) findViewById(R.id.ListaTutorias);
        lista.setAdapter(new ControllerListaGeneral(this,datos,datosImg));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent visorDetalles = new Intent(view.getContext(), ActivityInfoTutorship.class);
                visorDetalles.putExtra("TIT", datos[position][0]);
                startActivity(visorDetalles);
            }
        });



    }
    @Override
    public void onBackPressed() {
        finish();
    }

}