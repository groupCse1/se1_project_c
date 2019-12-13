package co.edu.unal.tutorship.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unal.tutorship.R;
import co.edu.unal.tutorship.model.User;
import co.edu.unal.tutorship.model.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityInfoTutor extends AppCompatActivity {

    private UserService userService;

    Button dictated;
    Button toDictate;


    private TextView facultyTV;
    private TextView nameTV;
    private TextView careerTV;
    private TextView phoneTV;
    private TextView emailTV;

    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_tutor);


        nameTV = (TextView) findViewById(R.id.namet);
        facultyTV = (TextView) findViewById(R.id.fakultätt);
        careerTV = (TextView) findViewById(R.id.studiumt);
        emailTV = (TextView) findViewById(R.id.emailt);
        phoneTV = (TextView) findViewById(R.id.telefonnumert);

        st = getIntent().getExtras().getString("nombre");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.7:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);

        System.out.println("==========INICIO==========");

        GetTutorInfo(st);

        System.out.println("==========FIN==========");

        dictated = findViewById(R.id.gegeben);

        toDictate = findViewById(R.id.zugeben);



    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

    private void GetTutorInfo(String st) {

        Call<User> call = userService.GetTutorInfo(st);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (!response.isSuccessful()){
                    System.out.println("CODE: "+response.code());
                    return;
                }

                if (response.isSuccessful()) {
                    if (nameTV.getVisibility() == View.GONE){
                        nameTV.setVisibility(View.VISIBLE);
                    }
                    nameTV.setText(response.body().getName());

                    if (emailTV.getVisibility() == View.GONE){
                        emailTV.setVisibility(View.VISIBLE);
                    }
                    emailTV.setText(response.body().getUser());


                    if (facultyTV.getVisibility() == View.GONE){
                        facultyTV.setVisibility(View.VISIBLE);
                    }
                    facultyTV.setText(response.body().getFaculty());

                    if (careerTV.getVisibility() == View.GONE){
                        careerTV.setVisibility(View.VISIBLE);
                    }
                    careerTV.setText(response.body().getCareer());

                    if (phoneTV.getVisibility() == View.GONE){
                        phoneTV.setVisibility(View.VISIBLE);
                    }
                    phoneTV.setText(Long.toString(response.body().getCellphone()));

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


}