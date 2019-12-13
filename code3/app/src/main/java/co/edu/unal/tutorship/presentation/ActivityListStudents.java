package co.edu.unal.tutorship.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import co.edu.unal.tutorship.R;
import co.edu.unal.tutorship.model.Adaptor;


public class ActivityListStudents extends AppCompatActivity {

    ListView lista;

    String[][] datos={

            {"Andrea Archila","Ingeniería Industrial","aarchila@unal.edu.co","310 657 3455"},
            {"Valentina Bueno","Ingeniería de Sistemas","vbueno@unal.edu.co","312 456 7890"},
            {"David Pedraza","Ingenieria Agrícola","spedrazaf@unal.edu.co","312 456 783"},
            {"Camila Torres","Ingeniería Civil","ctorresw@unal.edu.co","310 345 7262"},
            {"Sebastian Basco","Ingeniería Civil","sbascod@unal.edu.co","320 836 6473"},
            {"Camilo Arias","Ingeniería Mecatrónica","cariazf@unal.edu.co","300 756 5325"}
    };

    int[] dataImg={R.drawable.womanstuden,R.drawable.womanstuden,R.drawable.manstudent,R.drawable.womanstuden,R.drawable.manstudent,R.drawable.manstudent};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_estudiantes);

        lista=(ListView) findViewById(R.id.lvLista);
        lista.setAdapter(new Adaptor(this, datos,dataImg));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}