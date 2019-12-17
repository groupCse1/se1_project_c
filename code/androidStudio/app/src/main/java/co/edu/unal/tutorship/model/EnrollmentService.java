package co.edu.unal.tutorship.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface EnrollmentService {
    @POST("api/enrollment")
    Call<Enrollment> postEnrollment(@Body Enrollment enrollment);
}
