package co.edu.unal.tutorshipback.repository;

import co.edu.unal.tutorshipback.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long>{

}
