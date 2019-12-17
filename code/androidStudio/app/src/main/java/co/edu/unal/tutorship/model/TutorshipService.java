package co.edu.unal.tutorship.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface TutorshipService {

    @GET("tutorship")
    Call<List<Tutorship>> getTutorships();

}
