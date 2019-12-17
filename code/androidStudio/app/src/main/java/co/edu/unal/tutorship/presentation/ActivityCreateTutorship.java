package co.edu.unal.tutorship.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Text;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import co.edu.unal.tutorship.R;
import co.edu.unal.tutorship.model.Classroom;
import co.edu.unal.tutorship.model.ClassroomService;
import co.edu.unal.tutorship.model.Subject;
import co.edu.unal.tutorship.model.SubjectService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityCreateTutorship extends AppCompatActivity implements View.OnClickListener{

    Button DateButton,HourButton,CreateButton;
    EditText ETfecha,EThora,ETDuration,ETCapacity;
    Spinner SubjectSpinner,BuildingSpinner,RoomSpinner;
    TextView TVTittleCreate;
    private TextView mJasonTxtView;
    private int dia,mes,ano,hora,minutos;
    String tutor="bmguzmang@unal.edu.co";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_tutoria);

        TVTittleCreate = (TextView) findViewById(R.id.TVTittleCreate);

        DateButton = (Button) findViewById(R.id.DateButton);
        HourButton = (Button) findViewById(R.id.HourButton);
        CreateButton = (Button) findViewById(R.id.CreateButton);

        ETfecha = (EditText) findViewById(R.id.ETfecha);
        EThora = (EditText) findViewById(R.id.EThora);
        ETCapacity = (EditText) findViewById(R.id.ETCapacity);
        ETDuration = (EditText) findViewById(R.id.ETDuration);

        SubjectSpinner = (Spinner) findViewById(R.id.SubjectSpinner);
        BuildingSpinner = (Spinner) findViewById(R.id.BuildingSpinner);
        RoomSpinner = (Spinner) findViewById(R.id.RoomSpinner);

        DateButton.setOnClickListener(this);
        HourButton.setOnClickListener(this);
        CreateButton.setOnClickListener(this);

        TVTittleCreate.setText("Diligencia el Formulario");

        // Subjects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.4:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final List subjects = new ArrayList<>();
        final SubjectService subjectService = retrofit.create(SubjectService.class);
        Call<List<Subject>> callSubject = subjectService.getSubject();
        callSubject.enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                for (Subject s : response.body()) {
                    subjects.add(s);
                }
                getSubjectBar(subjects);
            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t) {
                //mJasonTxtView.setText(t.getMessage());
            }
        });

        //Buindings
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://192.168.0.4:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClassroomService classroomserv = retrofit2.create(ClassroomService.class);
        Call<List<Classroom>> callClassroom =classroomserv.getClassroom();
        callClassroom.enqueue(new Callback<List<Classroom>>() {

            @Override
            public void onResponse(Call<List<Classroom>> call, Response<List<Classroom>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<Classroom> classroomList = response.body();
                getBuildingBar(classroomList);
            }

            @Override
            public void onFailure(Call<List<Classroom>> call, Throwable t) {
                //mJasonTxtView.setText(t.getMessage());
            }

        });


        //tutor = getIntent().getExtras().getString("correo");
    }

    public void getSubjectBar(final List<Subject> curSubject){
        String[] Subjects = new String[curSubject.size()];
        for(int i=0; i<curSubject.size(); ++i)  Subjects[i] = curSubject.get(i).getSubject_name();
        String[] Buildings = {"Viejo de Ingenieria","Aulas de Ingenieria","Ciencia y Tecnologia","FEM","Instituto de Extensión e Investigación"};
        Integer[] Room = {101,102,103,104,105,106,201,202,203,204,205,206,301,302,303,304,305,306,401,402,403,404,405,406};

        System.out.println(curSubject.size());
        ArrayAdapter<String> SubjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Subjects);
        SubjectSpinner.setAdapter(SubjectAdapter);
    }

    public void getBuildingBar(List<Classroom> classroomList){
        String[] classnames = new String[classroomList.size()];
        Integer[] numbuild = new Integer[classroomList.size()];

        for(int c=0;c<classroomList.size();c++){
            classnames[c]=classroomList.get(c).getBuilding();
            numbuild[c]=classroomList.get(c).getNumber();
        }

        ArrayAdapter<String> BuildingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,classnames);
        BuildingSpinner.setAdapter(BuildingAdapter);
        ArrayAdapter<Integer> RoomAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,numbuild);
        RoomSpinner.setAdapter(RoomAdapter);

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
            Format formatter = new SimpleDateFormat("dd-MM-yyyy");
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
            visorConfirmation.putExtra("correo", tutor);

            System.out.println(SubjectSpinner.getSelectedItem().toString());
            System.out.println(ETfecha.getText().toString());
            System.out.println(EThora.getText().toString());
            System.out.println(ETDuration.getText().toString());
            System.out.println(BuildingSpinner.getSelectedItem().toString());
            System.out.println(RoomSpinner.getSelectedItem().toString());
            System.out.println(ETCapacity.getText().toString());

            startActivity(visorConfirmation);
        }
    }
}
