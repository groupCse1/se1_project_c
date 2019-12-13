package co.edu.unal.tutorship.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface UserService {

    String API_ROUTE = "api/user";

    @GET("api/init")
    Call<User> GetUserInfo(
            @Query("user") String user
    );

    @GET("api/init2")
    Call<User> GetTutorInfo(
            @Query("name") String name
    );

    @POST("api/inicio")
    Call<Boolean> VerifyIn(@Body User user);


}
