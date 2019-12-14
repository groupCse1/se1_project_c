package co.edu.unal.tutorias.Interface;
import co.edu.unal.tutorias.Model.Enrollment;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface enrollmentService {
	
	@GET("/enrollment/list/{id}")
	Call<List<Enrollment>> enrollmentList(@Path("id") Long tutorshipID);
	
	
}
