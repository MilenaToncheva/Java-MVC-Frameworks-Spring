package softuni.heroes.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.heroes.data.models.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username=:username")
    User findByUsername(@Param(value = "username") String username);


    Optional<User> findByUsernameAndPassword(String username, String password);
}
