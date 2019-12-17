
package co.edu.unal.tutorshipback.repository;

import co.edu.unal.tutorshipback.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends JpaRepository <Subject, Long>{
   @Query(

            value = "SELECT * FROM subject u WHERE u.subject_name = ?1",

            nativeQuery = true)

    Subject findSubjectByName(String name);
}
