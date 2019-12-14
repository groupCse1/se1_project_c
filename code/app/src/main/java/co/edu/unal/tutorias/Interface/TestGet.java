package co.edu.unal.tutorias.Interface;

import co.edu.unal.tutorias.Model.TestPost;
import retrofit2.Call;
import java.util.*;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TestGet {
    @GET("posts")
    Call<List<TestPost>> getTestPost();
}
