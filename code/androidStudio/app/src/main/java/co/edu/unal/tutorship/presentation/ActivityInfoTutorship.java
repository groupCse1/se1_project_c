package co.edu.unal.tutorship.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import co.edu.unal.tutorship.R;

import co.edu.unal.tutorship.model.Classroom;
import co.edu.unal.tutorship.model.ClassroomService;
import co.edu.unal.tutorship.model.Enrollment;
import co.edu.unal.tutorship.model.EnrollmentService;
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

public class ActivityInfoTutorship extends AppCompatActivity{

    Button CreateTutorshipButton,StudentsListButton;
    private Button TVTutorInfo;
    Tutorship arg;
    Subject sub;
    User tutor;
    Classroom classroom;
    String assist = getIntent().getExtras().getString("correo");
    long idAssist;
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

        //CreateTutorshipButton = (Button) findViewById(R.id.CreateTutorshipButton);


        StudentsListButton = (Button) findViewById(R.id.EstudentsListButton);
        StudentsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ActivityInfoTutorship.this, ActivityListStudents.class);
                startActivity(i);
            }
        });

        //falta resivir el numero de id de la tutoria desde el listado
        Intent intent = getIntent();
        String st = intent.getExtras().getString("correo");
        long id = intent.getExtras().getLong("TUTORIAID");
        long calssroomid = intent.getExtras().getLong("CLASSID");
        String idmateria = intent.getExtras().getString("TIT");
        String fecha = intent.getExtras().getString("DATE");
        String hora = intent.getExtras().getString("HOUR");
        int capacity = intent.getExtras().getInt("CAPACITY");
        int duration = intent.getExtras().getInt("DURATION");
        String nombretutor = intent.getExtras().getString("TUTOR");

        arg = new Tutorship();//cambiar al numero de id de la tutoria
        getPost(id);
        classroom = new Classroom();
        getclassroom(arg.getClassroom());

        TVTitleInfo.setText("Información Tutoria");
        TVSubjectInfo.setText("materia "+idmateria);
        TVDateInfo.setText("fecha "+fecha);
        TVHourInfo.setText("hora "+hora);
        TVDurationInfo.setText("duracion "+duration);
        TVBuildingInfo.setText("edificio "+classroom.getBuilding());
        TVRoomInfo.setText("salon "+classroom.getNumber());
        TVCapacityInfo.setText("capacidad "+capacity);

        IVProfileTutorInfo.setImageResource(R.drawable.girl);



        final Enrollment asist=new Enrollment();

        asist.setIdTutorship(id);

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://192.168.0.4:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService user = retrofit2.create(UserService.class);
        Call<List<User>> callUser = user.getUserList();
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for(User i : response.body()){
                    if(i.getUser().equals(assist)){
                        idAssist = i.getId();
                        asist.setIdAssisst(idAssist);
                        break;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
        CreateTutorshipButton = (Button) findViewById(R.id.CreateTutorshipButton);
        CreateTutorshipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.4:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                EnrollmentService classroomserv = retrofit.create(EnrollmentService.class);

                Call<Enrollment> callClassroom = (Call<Enrollment>) classroomserv.postEnrollment(asist);
                callClassroom.enqueue(new Callback<Enrollment>() {
                    @Override
                    public void onResponse(Call<Enrollment> call, Response<Enrollment> response) {
                        System.out.println("**********IN***************");
                        System.out.println(asist.getIdAssisst());
                        System.out.println(asist.getIdTutorship());
                    }

                    @Override
                    public void onFailure(Call<Enrollment> call, Throwable t) {

                    }
                });
            }
        });






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


    private void getclassroom(Long id) {
        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://192.168.0.4:8080/")

                .addConverterFactory(GsonConverterFactory.create())

                .build();
        ClassroomService classroomserv = retrofit.create(ClassroomService.class);

        Call<Classroom> callClassroom = (Call<Classroom>) classroomserv.getClassroombyid(id);

        callClassroom.enqueue(new Callback<Classroom>() {

            @Override

            public void onResponse(Call<Classroom> call, Response<Classroom> response) {

                if (!response.isSuccessful()) {

                    //mJasonTxtView.setText("Code: " + response.code());

                    return;

                }

                Classroom resp = response.body();

                classroom.setBuilding(resp.getBuilding());
                classroom.setCapacity(resp.getCapacity());
                classroom.setNumber(resp.getNumber());
                //for(User i : userList){

                //mJasonTxtView.append(content);

                //}

            }


            @Override

            public void onFailure(Call<Classroom> call, Throwable t) {

                //mJasonTxtView.setText(t.getMessage());

            }

        });
    }

    private void getUs(Long id) {
        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://192.168.0.4:8080/")

                .addConverterFactory(GsonConverterFactory.create())

                .build();
        UserService userserv = retrofit.create(UserService.class);

        Call<User> callUser = (Call<User>) userserv.getUserbyid(id);

        callUser.enqueue(new Callback<User>() {

            @Override

            public void onResponse(Call<User> call, Response<User> response) {

                if (!response.isSuccessful()) {

                    //mJasonTxtView.setText("Code: " + response.code());

                    return;

                }

                User resp = response.body();
                tutor.setName(resp.getName());

                //for(User i : userList){

                //mJasonTxtView.append(content);

                //}

            }


            @Override

            public void onFailure(Call<User> call, Throwable t) {

                //mJasonTxtView.setText(t.getMessage());

            }

        });
    }

    private void getSub(Long id) {
        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://192.168.0.4:8080/")

                .addConverterFactory(GsonConverterFactory.create())

                .build();
        SubjectService subjectserv = retrofit.create(SubjectService.class);


        Call<Subject> callSubject = (Call<Subject>) subjectserv.getSubjectInfo(id);

        callSubject.enqueue(new Callback<Subject>() {

            @Override

            public void onResponse(Call<Subject> call, Response<Subject> response) {

                if (!response.isSuccessful()) {

                    //mJasonTxtView.setText("Code: " + response.code());

                    return;

                }

                Subject resp = response.body();

                sub.setCode(resp.getCode());

                sub.setSubject_name(resp.getSubject_name());

                sub.setSubject_faculty(resp.getSubject_faculty());
                //for(User i : userList){

                //mJasonTxtView.append(content);

                //}

            }


            @Override

            public void onFailure(Call<Subject> call, Throwable t) {

                //mJasonTxtView.setText(t.getMessage());

            }

        });

    }


    @Override
    public void onBackPressed() {
        finish();
    }


    private void getPost(long id) {
        Gson gson= new GsonBuilder()
                .setDateFormat("dd-mm-yyyy")
                .create();
        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://192.168.0.4:8080/")

                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();


        TutorshipService tutorshipserv = retrofit.create(TutorshipService.class);

        //TestGet testGet = retrofit.create(TestGet.class);

        Call<Tutorship> callTutorship = (Call<Tutorship>) tutorshipserv.getTutorshipInfo(id);

        // Call<List<TestPost>> call = testGet.getTestPost();

        callTutorship.enqueue(new Callback<Tutorship>() {

            @Override

            public void onResponse(Call<Tutorship> call, Response<Tutorship> response) {

                if (!response.isSuccessful()) {

                    //mJasonTxtView.setText("Code: " + response.code());

                    return;

                }

                Tutorship resp = response.body();

                //for(User i : userList){

                arg.setDate(resp.getDate());

                arg.setInit_Hour(resp.getInit_Hour());

                arg.setLength(resp.getLength());

                arg.setLimit_Number(resp.getLimit_Number());

                arg.setViability(resp.getViability());

                arg.setTutor(resp.getTutor());

                arg.setClassroom(resp.getClassroom());

                arg.setIdtutorship(resp.getIdtutorship());

                arg.setSubject(resp.getSubject());
                //mJasonTxtView.append(content);

                //}

            }


            @Override

            public void onFailure(Call<Tutorship> call, Throwable t) {

                //mJasonTxtView.setText(t.getMessage());

            }

        });
    }
}