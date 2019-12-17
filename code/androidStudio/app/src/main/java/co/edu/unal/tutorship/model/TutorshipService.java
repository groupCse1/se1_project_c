package co.edu.unal.tutorship.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface TutorshipService {

    @GET("tutorship")
    Call<List<Tutorship>> getTutorships();
    @GET("api/tutorship/{id}")
    Call<Tutorship> getTutorshipInfo (@Path("id") long tutorshipID);
    @POST("api/tutorship")
    Call<Tutorship> postTutorship(@Body Tutorship tutorship);

}
