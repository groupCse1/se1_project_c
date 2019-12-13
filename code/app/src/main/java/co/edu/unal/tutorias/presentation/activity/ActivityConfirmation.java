package co.edu.unal.tutorias.presentation.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.unal.tutorias.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityConfirmation extends AppCompatActivity implements View.OnClickListener {

    private TextView TVSubject,TVDate,TVHour,TVDuration,TVBuilding,TVRoom,TVCapacity,TVTittle;
    private Button BackButton,ConfirmationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_crear_tutoria);

        // Declaracion de Variables

        TVTittle = (TextView)findViewById(R.id.TVTittle);
        TVSubject = (TextView)findViewById(R.id.TVSubject);
        TVDate = (TextView)findViewById(R.id.TVDate);
        TVHour = (TextView)findViewById(R.id.TVHour);
        TVDuration = (TextView)findViewById(R.id.TVDuration);
        TVBuilding = (TextView)findViewById(R.id.TVBuilding);
        TVRoom = (TextView)findViewById(R.id.TVRoom);
        TVCapacity = (TextView)findViewById(R.id.TVCapacity);

        BackButton = (Button)findViewById(R.id.BackButton);
        ConfirmationButton = (Button)findViewById(R.id.ConfirmationButton);

        BackButton.setOnClickListener(this);
        ConfirmationButton.setOnClickListener(this);
        // Traer el dato de la anterior activity

        String DSubject = getIntent().getStringExtra("DSubject");
        String DDate = getIntent().getStringExtra("DDate");
        String DHour = getIntent().getStringExtra("DHour");
        String DDuration = getIntent().getStringExtra("DDuration");
        String DBuilding = getIntent().getStringExtra("DBuilding");
        String DRoom = getIntent().getStringExtra("DRoom");
        String DCapacity = getIntent().getStringExtra("DCapacity");

        // Ponerlo en el textview
        TVTittle.setText("CONFIRMACIÓN");
        TVSubject.setText("Materia: " + DSubject);
        TVDate.setText("Fecha: " + DDate);
        TVHour.setText("Hora: " + DHour);
        TVDuration.setText("Duración: " + DDuration);
        TVBuilding.setText("Edificio: " + DBuilding);
        TVRoom.setText("Salón: " + DRoom);
        TVCapacity.setText("Capacidad: " + DCapacity);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
    @Override
    public void onClick(View v) {
        if(v == BackButton){
            finish();
        }
        if(v == ConfirmationButton){
            Toast ConfirmationEnrollement = Toast.makeText(getApplicationContext(),"Tutoria Creada Exitosamente",Toast.LENGTH_LONG);
            ConfirmationEnrollement.show();

            // Codigo unión base de datos y envia a pagina principal

        }
    }
}

