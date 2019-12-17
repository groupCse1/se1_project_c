package co.edu.unal.tutorship.model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SubjectService {

    @GET("subject/{id}")
    Call<Subject> getSubjects(@Path("id") long subjectId);

}
