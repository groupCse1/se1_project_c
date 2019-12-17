 package co.edu.unal.tutorshipback.repository;

import co.edu.unal.tutorshipback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select u from user u where u.user = ?1")
  User getuserbyUsername(String username);

}
