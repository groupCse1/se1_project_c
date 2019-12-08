package co.edu.unal.tutorias;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import co.edu.unal.tutorias.Interface.TestGet;
import co.edu.unal.tutorias.Model.TestPost;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJasonTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJasonTxtView = findViewById(R.id.jsonText);
        getPost();
    }

    private void getPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestGet testGet = retrofit.create(TestGet.class);
        Call<List<TestPost>> call = testGet.getTestPost();
        call.enqueue(new Callback<List<TestPost>>() {
            @Override
            public void onResponse(Call<List<TestPost>> call, Response<List<TestPost>> response) {
                if(!response.isSuccessful()){
                    mJasonTxtView.setText("Code: " + response.code());
                    return;
                }
                List<TestPost> postsList = response.body();
                for(TestPost post : postsList){
                    String content = "";
                    content += "user id : " + post.getUserId() + "\n";
                    content += " id : " + post.getId() + "\n";
                    content += "title: " + post.getTitle() + "\n";
                    content += "body: " + post.getBody() + "\n\n";
                    mJasonTxtView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<TestPost>> call, Throwable t) {
                mJasonTxtView.setText(t.getMessage());
            }
        });

    }
}
