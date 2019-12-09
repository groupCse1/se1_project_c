package co.edu.unal.tutorshipback.repository;

import co.edu.unal.tutorshipback.model.Enrollment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EnrollmentRepository extends  JpaRepository <Enrollment, Long>{
    @Query(
            value = "SELECT * FROM enrollment u WHERE u.idTutorship = ?1",
            nativeQuery = true)
    List<Enrollment> findEnrollmentByIdTutorship(long id);
    /*@Query(
            value = "SELECT * FROM enrollment u WHERE u.idAssisst = ?1",
            nativeQuery = true)
    List<Enrollment> findEnrollmentByIdAssisst(long id);*/

}
