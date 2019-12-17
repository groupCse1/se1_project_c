package co.edu.unal.tutorship.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import co.edu.unal.tutorship.R;
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

public class ActivityListGeneral extends AppCompatActivity {

    TextView titulo,carrera,tutor;
    int[] datosImg = {R.drawable.brain,R.drawable.notepad,R.drawable.thinking,R.drawable.physics,R.drawable.desklamp,R.drawable.geography};

    static class RetrofitClientInstance{


        private static Retrofit retrofit;
        private static final String BASE_URL = "http://192.168.0.4:8080/api/";

        public static Retrofit getRetrofitInstance() {
            if(retrofit == null){
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

    class ListViewAdapter extends BaseAdapter {

        private List<Tutorship> tutorships;
        private User user;
        private Subject subject;
        private Context context;

        public ListViewAdapter(List<Tutorship> tutorships, Context context) {
            this.tutorships = tutorships;
            this.context = context;
            this.user = user;
            this.subject = subject;
        }
        public ListViewAdapter(User user, Context context) {
            this.user = user;
            this.context = context;
        }
        public ListViewAdapter(Subject subject, Context context) {
            this.subject = subject;
            this.context = context;
        }




        @Override
        public int getCount() {
            return tutorships.size();
        }

        @Override
        public Object getItem(int position) {
            return tutorships.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            UserService userService = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
            SubjectService subjectService = RetrofitClientInstance.getRetrofitInstance().create(SubjectService.class);

            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.elemento_lista,viewGroup,false);
            }
            titulo = (TextView) view.findViewById(R.id.TvNombreTutoria);
            tutor = (TextView) view.findViewById(R.id.TvTutor);
            carrera = (TextView) view.findViewById(R.id.TvCarrera);

            ImageView imagen = (ImageView) view.findViewById(R.id.RelatedImage);



            final Tutorship thisTutorship = tutorships.get(position);
            System.out.println("-------------------Tutoria numero: " +  position + "   --------------------------");


            Call<User> callU = userService.getUsers(thisTutorship.getTutor());
            final Call<Subject> callS = subjectService.getSubjects(thisTutorship.getSubject());



            callU.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    user = response.body();
                    tutor.setText(user.getName());
                    System.out.println("------------------- "+ user.getName() +" --------------------------");

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
            callS.enqueue(new Callback<Subject>() {
                @Override
                public void onResponse(Call<Subject> call, Response<Subject> response) {

                    subject = response.body();

                    titulo.setText(subject.getSubject_name());
                    carrera.setText(subject.getSubject_faculty());
                    System.out.println("------------------- "+ subject.getSubject_name() + " ------ " +  subject.getSubject_faculty() +" --------------------------");
                }

                @Override
                public void onFailure(Call<Subject> call, Throwable t) {

                }
            });

            titulo.setText("Subject: "  + thisTutorship.getSubject());
            carrera.setText("Carrera: " + thisTutorship.getSubject());
            tutor.setText("tutor: " + thisTutorship.getTutor());



            imagen.setImageResource(datosImg[position%datosImg.length]);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent visorDetalles = new Intent(v.getContext(), ActivityInfoTutorship.class);
                    st = getIntent().getExtras().getString("correo");
                    visorDetalles.putExtra("correo",st);
                    visorDetalles.putExtra("TUTORIAID", thisTutorship.getIdtutorship());
                    visorDetalles.putExtra("ClASSID",thisTutorship.getClassroom());
                    visorDetalles.putExtra("TIT", thisTutorship.getSubject());
                    visorDetalles.putExtra("DATE", thisTutorship.getDate());
                    visorDetalles.putExtra("HOUR", thisTutorship.getInit_Hour());
                    visorDetalles.putExtra("CAPACITY", thisTutorship.getLimit_Number());
                    visorDetalles.putExtra("DURATION", thisTutorship.getLength());
                    visorDetalles.putExtra("TUTOR",thisTutorship.getTutor());
                    startActivity(visorDetalles);
                }
            });
            return view;
        }
    }

    private ListViewAdapter adapter;
    private ListView mListView;
    String st;
    private void populateListView(List<Tutorship> tutorshipList){
        mListView = findViewById(R.id.ListaTutorias);
        adapter = new ListViewAdapter(tutorshipList , this  );
        mListView.setAdapter(adapter);
    }
    private void populateListView(User user){

    }
    private void populateListView(Subject subject){
        mListView = findViewById(R.id.ListaTutorias);
        adapter = new ListViewAdapter(subject , this  );
        mListView.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_general);

        TutorshipService tutorshipService = RetrofitClientInstance.getRetrofitInstance().create(TutorshipService.class);


        Call<List<Tutorship>> callT = tutorshipService.getTutorships();

        callT.enqueue(new Callback<List<Tutorship>>() {
            @Override
            public void onResponse(Call<List<Tutorship>> call, Response<List<Tutorship>> response) {

                populateListView(response.body());


            }

            @Override
            public void onFailure(Call<List<Tutorship>> call, Throwable t) {
                Toast.makeText(ActivityListGeneral.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}