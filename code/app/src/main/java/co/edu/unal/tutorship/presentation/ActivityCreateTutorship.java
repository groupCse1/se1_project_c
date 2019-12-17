package co.edu.unal.tutorship.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.edu.unal.tutorship.R;
import co.edu.unal.tutorship.model.Classroom;
import co.edu.unal.tutorship.model.ClassroomService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityCreateTutorship extends AppCompatActivity implements View.OnClickListener{

    private ClassroomService classroomService;
    ArrayList<String> salones = new ArrayList<>();
    String building;

    Button DateButton,HourButton,CreateButton;
    EditText ETfecha,EThora,ETDuration,ETCapacity;
    Spinner SubjectSpinner,BuildingSpinner,RoomSpinner;
    TextView TVTittleCreate;
    private int dia,mes,ano,hora,minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_tutoria);

        TVTittleCreate = (TextView)findViewById(R.id.TVTittleCreate);

        DateButton =  (Button)findViewById(R.id.DateButton);
        HourButton =  (Button)findViewById(R.id.HourButton);
        CreateButton = (Button)findViewById(R.id.CreateButton);

        ETfecha =  (EditText) findViewById(R.id.ETfecha);
        EThora =  (EditText) findViewById(R.id.EThora);
        ETCapacity = (EditText)findViewById(R.id.ETCapacity);
        ETDuration = (EditText)findViewById(R.id.ETDuration);

        SubjectSpinner = (Spinner)findViewById(R.id.SubjectSpinner);
        BuildingSpinner = (Spinner)findViewById(R.id.BuildingSpinner);
        RoomSpinner = (Spinner)findViewById(R.id.RoomSpinner);

        DateButton.setOnClickListener(this);
        HourButton.setOnClickListener(this);
        CreateButton.setOnClickListener(this);

        TVTittleCreate.setText("Diligencia el Formulario");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,salones);
        RoomSpinner.setAdapter(arrayAdapter);

        // Datos Spinner
        String[] Subjects = {"Ingenieria de Software","Algoritmos","Cálculo Diferencial","Señales y Sistemas"};
        String[] Buildings = {"Viejo","Aulas de Ingenieria","CyT"};
        //Integer[] Room = {101,102,103,104,105,106,201,202,203,204,205,206,301,302,303,304,305,306,401,402,403,404,405,406};

        // Conexion con vista

        ArrayAdapter<String> SubjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Subjects);
        SubjectSpinner.setAdapter(SubjectAdapter);
        ArrayAdapter<String> BuildingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Buildings);
        BuildingSpinner.setAdapter(BuildingAdapter);
        //ArrayAdapter<Integer> RoomAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,Room);
        //RoomSpinner.setAdapter(RoomAdapter);

        BuildingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                building = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        System.out.println(building);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.7:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        classroomService = retrofit.create(ClassroomService.class);
        GetClassrooms(building);
    }

    private void GetClassrooms(String building) {

        Classroom cl = new Classroom(building,4,4);

        Call<List<Classroom>> call = classroomService.GetAllClassrooms(cl);

        call.enqueue(new Callback<List<Classroom>>() {
            @Override
            public void onResponse(Call<List<Classroom>> call, Response<List<Classroom>> response) {
                if (!response.isSuccessful()){
                    System.out.println("CODE: "+response.code());
                    return;
                }
                if(response.isSuccessful()){
                    for (Classroom classroom : response.body()) {
                        salones.add(classroom.getBuilding());
                    }
                    System.out.println("======LISTA======");
                }
            }

            @Override
            public void onFailure(Call<List<Classroom>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
    @Override
    public void onClick(View v) {
        if(v == DateButton) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            String s = formatter.format(c.getTime());
            ETfecha.setText(s);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    ETfecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }
                    , ano, mes, dia);
            datePickerDialog.show();
        }
        if(v== HourButton){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    EThora.setText(hourOfDay+":"+minute);
                }
            }
                    ,hora,minutos,false);
            timePickerDialog.show();
        }
        if(v == CreateButton){

            Intent visorConfirmation = new Intent(this, ActivityConfirmation.class);
            visorConfirmation.putExtra("DSubject",SubjectSpinner.getSelectedItem().toString());
            visorConfirmation.putExtra("DDate",ETfecha.getText().toString());
            visorConfirmation.putExtra("DHour",EThora.getText().toString());
            visorConfirmation.putExtra("DDuration",ETDuration.getText().toString());
            visorConfirmation.putExtra("DBuilding",BuildingSpinner.getSelectedItem().toString());
            visorConfirmation.putExtra("DRoom",RoomSpinner.getSelectedItem().toString());
            visorConfirmation.putExtra("DCapacity",ETCapacity.getText().toString());

            startActivity(visorConfirmation);
        }
    }
}
