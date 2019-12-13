package co.edu.unal.tutorship.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.unal.tutorship.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityInfoTutorship extends AppCompatActivity {

    Button CreateTutorshipButton,EstudentsListButton;
    private Button TVTutorInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_tutoria);

        final TextView TVTitleInfo = (TextView)findViewById(R.id.TVTitleInfo);
        final TextView TVSubjectInfo = (TextView) findViewById(R.id.TVSubjectInfo);
        final TextView TVDateInfo = (TextView) findViewById(R.id.TVDateInfo);
        final TextView TVHourInfo = (TextView)findViewById(R.id.TVHourInfo);
        final TextView TVDurationInfo = (TextView)findViewById(R.id.TVDurationInfo);
        final TextView TVBuildingInfo = (TextView)findViewById(R.id.TVBuildingInfo);
        final TextView TVRoomInfo = (TextView)findViewById(R.id.TVRoomInfo);
        final TextView TVCapacityInfo = (TextView)findViewById(R.id.TVCapacityInfo);
        TVTutorInfo = (Button)findViewById(R.id.TVTutorInfo);
        final ImageView IVProfileTutorInfo = (ImageView)findViewById(R.id.IVProfileTutorInfo);

        CreateTutorshipButton = (Button) findViewById(R.id.CreateTutorshipButton);
        EstudentsListButton = (Button) findViewById(R.id.EstudentsListButton);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b != null){
            TVTitleInfo.setText("Información Tutoria");
            TVSubjectInfo.setText(b.getString("TIT"));
            TVDateInfo.setText("Fecha: 07/07/2000");
            TVHourInfo.setText("Hora: 3:45");
            TVDurationInfo.setText("Duración: 60 minutos");
            TVBuildingInfo.setText("Edificio: Viejo de ingenieria");
            TVRoomInfo.setText("Salón: 401");
            TVCapacityInfo.setText("Capacidad: 30");

            IVProfileTutorInfo.setImageResource(R.drawable.girl);

        }

        TVTutorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tutor = TVTutorInfo.getText().toString();
                Intent i = new Intent(getApplicationContext(), ActivityInfoTutor.class);
                i.putExtra("nombre", tutor);
                startActivity(i);
            }
        });
    }



    @Override
    public void onBackPressed() {
        finish();
    }
}