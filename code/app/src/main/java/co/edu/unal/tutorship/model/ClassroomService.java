package co.edu.unal.tutorship.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ClassroomService {



    @POST("api/classroomB")
    Call<List<Classroom>> GetAllClassrooms(@Body Classroom classroom);



}
