package co.edu.unal.tutorship.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ClassroomService {

    @GET("api/classroom/{id}")
    Call<Classroom> getClassroombyid (@Path("id") long classroomID);
    @GET("api/classroom")
    Call<List<Classroom>> getClassroom ();

}
