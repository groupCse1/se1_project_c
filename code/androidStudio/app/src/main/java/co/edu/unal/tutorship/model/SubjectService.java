package co.edu.unal.tutorship.model;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SubjectService {

    @GET("subject/{id}")
    Call<Subject> getSubjects(@Path("id") long subjectId);
    @GET("api/subject/{id}")
    Call<Subject> getSubjectInfo (@Path("id") long subjectID);
    @GET("api/subject")
    Call<List<Subject>> getSubject ();

}
