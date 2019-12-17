package co.edu.unal.tutorship.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


import co.edu.unal.tutorship.R;
import co.edu.unal.tutorship.model.User;
import co.edu.unal.tutorship.model.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityLogIn extends AppCompatActivity {

    private UserService userService;
    private Boolean in=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        final TextInputEditText EmailIn = findViewById(R.id.anwender);
        final TextInputEditText PasswordIn = findViewById(R.id.passwort);

        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.0.4:8080/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        userService = retrofit.create(UserService.class);

        System.out.println("==========INICIO==========");

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in=false;


                String Email = EmailIn.getText().toString();
                String Password = PasswordIn.getText().toString();



                verifyIn(Email,Password, v);
            }
        });

        System.out.println("==========FIN==========");

    }

    private void verifyIn (final String email, String password, final View v){
        User user = new User(email,password,"pruebaName", "pruebaFac", "pruebaCar");

        Call<Boolean> call = userService.VerifyIn(user);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (!response.isSuccessful()){
                    System.out.println("CODE: "+response.code());
                    return;
                }

                System.out.println("RESPUESTA DE INICIO: "+response.body());
                in=response.body();

                if (in==true){
                    Intent i=new Intent(ActivityLogIn.this, ActivityLoggedUser.class);
                    System.out.println("======ENTRO======");
                    i.putExtra("correo", email);
                    startActivity(i);
                    finish();
                }else{
                    Snackbar.make(v,"No se pudo iniciar sesión, verifique usuario y/o contraseña", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    System.out.println("NO SE PUDO INICIAR SESIÓN");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
