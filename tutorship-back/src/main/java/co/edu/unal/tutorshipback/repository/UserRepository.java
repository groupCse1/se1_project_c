 package co.edu.unal.tutorshipback.repository;

import co.edu.unal.tutorshipback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query(
            value = "SELECT * FROM users u WHERE u.user = ?1",
            nativeQuery = true)
    User findUserByUserName(String name);
}
