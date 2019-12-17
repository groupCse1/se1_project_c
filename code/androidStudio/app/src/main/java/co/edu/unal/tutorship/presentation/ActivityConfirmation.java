package co.edu.unal.tutorship.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import co.edu.unal.tutorship.R;

import co.edu.unal.tutorship.model.Classroom;
import co.edu.unal.tutorship.model.ClassroomService;
import co.edu.unal.tutorship.model.Subject;
import co.edu.unal.tutorship.model.SubjectService;
import co.edu.unal.tutorship.model.Tutorship;
import co.edu.unal.tutorship.model.TutorshipService;
import co.edu.unal.tutorship.model.User;
import co.edu.unal.tutorship.model.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityConfirmation extends AppCompatActivity implements View.OnClickListener {

    private TextView TVSubject,TVDate,TVHour,TVDuration,TVBuilding,TVRoom,TVCapacity,TVTittle;
    private Button BackButton,ConfirmationButton;
    Tutorship target;
    String DSubject = new String();
    String DDate = new String();
    String DHour = new String();
    String DBuilding = new String();
    String DRoom = new String();
    String DCapacity = new String();
    String DDuration = new String();
    String tutor;

    long idSubject;
    Long idClassroom;
    long idTutor = -1;


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

        DSubject = getIntent().getStringExtra("DSubject");
        DDate = getIntent().getStringExtra("DDate");
        //target.setDate(DDate);
        DHour = getIntent().getStringExtra("DHour");
        //target.setInit_Hour(DHour);
        DDuration = getIntent().getStringExtra("DDuration");
        //target.setLength(Integer.parseInt(DDuration));
        DBuilding = getIntent().getStringExtra("DBuilding");
        DRoom = getIntent().getStringExtra("DRoom");
        DCapacity = getIntent().getStringExtra("DCapacity");
        //target.setLimit_Number(Integer.parseInt(DCapacity));

        // Ponerlo en el textview
        TVTittle.setText("CONFIRMACIÓN");
        TVSubject.setText("Materia: " + DSubject);
        TVDate.setText("Fecha: " + DDate);
        TVHour.setText("Hora: " + DHour);
        TVDuration.setText("Duración: " + DDuration);
        TVBuilding.setText("Edificio: " + DBuilding);
        TVRoom.setText("Salón: " + DRoom);
        TVCapacity.setText("Capacidad: " + DCapacity);

        tutor = getIntent().getExtras().getString("correo");

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
            final Tutorship newTutorship = new Tutorship();

            System.out.println(DSubject);
            //Subjects
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.6:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final List subjects = new ArrayList<>();
            final SubjectService subjectService = retrofit.create(SubjectService.class);
            /*Call<List<Subject>> callSubject = subjectService.getSubject();
            callSubject.enqueue(new Callback<List<Subject>>() {
                @Override
                public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                    for (Subject s : response.body()) {
                        if(s.getSubject_name().equals(DSubject)){
                            newTutorship.setSubject(s.getCode());
                            System.out.println(s.getCode() + " " + s.getSubject_name());
                            break;
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Subject>> call, Throwable t) {
                    //mJasonTxtView.setText(t.getMessage());
                }
            });

            //Classroom
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.6:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ClassroomService clasrrom = retrofit.create(ClassroomService.class);
            Call<List<Classroom>> callClasroom = clasrrom.getClassroom();
            callClasroom.enqueue(new Callback<List<Classroom>>() {
                @Override
                public void onResponse(Call<List<Classroom>> call, Response<List<Classroom>> response) {
                    if(!response.isSuccessful()){
                        return;
                    }
                    for(Classroom s : response.body()){
                        if(s.getBuilding().equals(DBuilding) && s.getNumber() == Integer.parseInt(DRoom)){
                            idClassroom = s.getId();
                            System.out.println(s.getId() + " " + s.getBuilding() + " " + s.getNumber());
                            newTutorship.setClassroom(idClassroom);
                            break;
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Classroom>> call, Throwable t) {

                }
            });

            //Tutor
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.6:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService user = retrofit.create(UserService.class);
            Call<List<User>> callUser = user.getUserList();
            callUser.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    for(User i : response.body()){
                        if(i.getUser().equals(tutor)){
                            idTutor = i.getId();
                            newTutorship.setTutor(idTutor);
                            System.out.println(i.getId() + " " + i.getUser());
                            break;
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });


            //Post
            newTutorship.setLength(Integer.parseInt(DDuration));
            newTutorship.setLimit_Number(Integer.parseInt(DCapacity));

            DHour+= ":00";
            newTutorship.setInit_Hour(DHour);
            String newDate = "";
            for(int i=0; i<DDate.length(); ++i){
                if(DDate.charAt(i) == '/') newDate += '-';
                else newDate += DDate.charAt(i);
            }

            newTutorship.setDate(newDate);

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.6:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            TutorshipService postTutorship = retrofit.create(TutorshipService.class);
            Call<Tutorship> callTutorship = postTutorship.postTutorship(newTutorship);
            callTutorship.enqueue(new Callback<Tutorship>() {
                @Override
                public void onResponse(Call<Tutorship> call, Response<Tutorship> response) {
                    System.out.println("**********IN***************");
                    System.out.println(newTutorship.getTutor());
                    System.out.println(newTutorship.getDate());
                    System.out.println(newTutorship.getClassroom());
                    System.out.println(newTutorship.getInit_Hour());
                    System.out.println(newTutorship.getLength());
                    System.out.println(newTutorship.getLimit_Number());
                    System.out.println(newTutorship.getSubject());
                }

                @Override
                public void onFailure(Call<Tutorship> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });*/

        }
    }

}
