package co.edu.unal.tutorshipback.repository;

import co.edu.unal.tutorshipback.model.Tutorship;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TutorshipRepository extends JpaRepository<Tutorship, Long>{
   /* @Query(
            value = "SELECT * FROM tutorships u WHERE u.tutor = ?1",
            nativeQuery = true)
    List<Tutorship>findTutorshipByTutor(long idtutor);
    @Query(
            value = "SELECT * FROM tutorships u WHERE u.subject = ?1",
            nativeQuery = true)
    List<Tutorship>findTutorshipBySubject(long idsubject);*/
}
